package cl.perfulandia.inventario.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.perfulandia.inventario.dto.SucursalDto;
import cl.perfulandia.inventario.feign.SucursalClient;
import cl.perfulandia.inventario.modelo.Movimiento;
import cl.perfulandia.inventario.repository.MovimientoRepository;
@RestController
@RequestMapping("/api/inventario")
public class ControllerSucuInve {

    private final MovimientoRepository movimientoRepository;
    private final SucursalClient sucursalClient;
    
    public ControllerSucuInve(MovimientoRepository movimientoRepository, SucursalClient sucursalClient) {
        this.movimientoRepository = movimientoRepository;
        this.sucursalClient = sucursalClient;
    }
    @GetMapping("/sincronizar-stock")
    public String sincronizarStock() {
        List<SucursalDto> sucursales = sucursalClient.obtenerTodasLasSucursales();

        for (SucursalDto sucursal : sucursales) {
            Long sucursalId = sucursal.getSucursalId();
            // Aquí llamas a una función para obtener stock por sucursal
            Integer stock = movimientoRepository.obtenerStockTotalPorSucursal(sucursalId);
            System.out.println("Stock en sucursal " + sucursal.getNombre() + ": " + stock);
        }
        return "Stock sincronizado para todas las sucursales.";
    }

    @PostMapping("/movimientos")
    public Movimiento registrarMovimiento(@RequestBody Movimiento movimiento) {
        movimiento.setFecha(LocalDateTime.now());
        return movimientoRepository.save(movimiento);
    }

    @GetMapping("/alerta-reposicion")
    public List<String> alertasReposicion() {
        List<SucursalDto> sucursales = sucursalClient.obtenerTodasLasSucursales();
        List<String> alertas = new ArrayList<>();

        int umbralReposicion = 5;

        for (SucursalDto sucursal : sucursales) {
            Long sucursalId = sucursal.getSucursalId();
            List<Long> productosIds = movimientoRepository.obtenerProductosPorSucursal(sucursalId);
            for (Long productoId : productosIds) {
                Integer stock = movimientoRepository.obtenerStockActual(sucursalId, productoId);
                if (stock != null && stock < umbralReposicion) {
                    alertas.add("Stock bajo para producto " + productoId + " en sucursal " + sucursal.getNombre() + ": " + stock);
                }
            }
        }
        return alertas;
}


}
