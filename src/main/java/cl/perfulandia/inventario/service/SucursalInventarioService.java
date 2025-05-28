package cl.perfulandia.inventario.service;
import cl.perfulandia.inventario.dto.SucursalDto;
import cl.perfulandia.inventario.feign.SucursalClient;
import cl.perfulandia.inventario.modelo.Movimiento;
import cl.perfulandia.inventario.repository.MovimientoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SucursalInventarioService {

    private final MovimientoRepository movimientoRepository;
    private final SucursalClient sucursalClient;

    public SucursalInventarioService(MovimientoRepository movimientoRepository, SucursalClient sucursalClient) {
        this.movimientoRepository = movimientoRepository;
        this.sucursalClient = sucursalClient;
    }

    // 1. Sincronización básica de stock en todas las sucursales
    public String sincronizarStock() {
        List<SucursalDto> sucursales = sucursalClient.obtenerTodasLasSucursales();

        for (SucursalDto sucursal : sucursales) {
            Long sucursalId = sucursal.getSucursalId();
            Integer stock = movimientoRepository.obtenerStockTotalPorSucursal(sucursalId);
            if (stock == null) stock = 0;

            System.out.println("Stock en sucursal " + sucursal.getNombre() + ": " + stock);
        }

        return "Stock sincronizado para todas las sucursales.";
    }

    // 2. Registro de movimientos (ingreso o salida)
    public Movimiento registrarMovimiento(Movimiento movimiento) {
        movimiento.setFecha(LocalDateTime.now());
        return movimientoRepository.save(movimiento);
    }

    // 3. Alertas para reposición de inventario
    public List<String> obtenerAlertasReposicion() {
        List<SucursalDto> sucursales = sucursalClient.obtenerTodasLasSucursales();
        List<String> alertas = new ArrayList<>();

        int umbralReposicion = 5; // Cantidad mínima para alertar

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