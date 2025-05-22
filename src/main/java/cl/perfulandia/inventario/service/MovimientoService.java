package cl.perfulandia.inventario.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.perfulandia.inventario.feign.clienteSucursal;
import cl.perfulandia.inventario.feign.productoSucursal;
import cl.perfulandia.inventario.modelo.Movimiento;

import cl.perfulandia.inventario.repository.MovimientoRepository;
import cl.perfulandia.inventario.repository.StockRepository;
import feign.FeignException; 

@Service
public class MovimientoService {

    private final MovimientoRepository movimientoRepository;

    private final StockRepository stockRepository; 

    @Autowired
    private productoSucursal productoSucursal;
    @Autowired
    private clienteSucursal clienteSucursal;
    public MovimientoService(MovimientoRepository movimientoRepository, StockRepository stockRepository) {
        this.movimientoRepository = movimientoRepository;
        this.stockRepository = stockRepository;
    }

    //creo movimiento
    public Movimiento crearMovimiento(Movimiento movimiento) {
    // Validar producto
    try {
        clienteSucursal.obtenerProducto(movimiento.getProducto().getProductoId());
    } catch (FeignException.NotFound e) {
        throw new RuntimeException("Producto no encontrado");
    }

    // Validar sucursal
    try {
        productoSucursal.obtenerSucursalPorId(movimiento.getSucursalId());
    } catch (FeignException.NotFound e) {
        throw new RuntimeException("Sucursal no encontrada");
    }

    movimiento.setFecha(LocalDateTime.now());
    return movimientoRepository.save(movimiento);
    }

    //Listo Movimiento
    public List<Movimiento> listar() {
        return movimientoRepository.findAll();
    }

    //Obtengo por mov por id
    public Movimiento obtenerMovimientoPorId(Long id) {
        return movimientoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movimiento no encontrado"));
    }

    //Busco por id
    public Movimiento buscar(long id) {
        return movimientoRepository.findById(id).orElse(null);
    }

    //Elimino por id
    public void eliminar(long id) {
        movimientoRepository.deleteById(id);
    }

    //Listo por sucursalyproducto
    public List<Movimiento> findBySucursalIdAndProductoProductoId(Long sucursalId, Long productoId) {
        return movimientoRepository.findBySucursalAndProducto(sucursalId, productoId);
    }

    //Listo entre fechas
    public List<Movimiento> listarMovimientosEntreFechas(LocalDateTime inicio, LocalDateTime fin) {
        return movimientoRepository.findByFechaBetween(inicio, fin);
    }

}
