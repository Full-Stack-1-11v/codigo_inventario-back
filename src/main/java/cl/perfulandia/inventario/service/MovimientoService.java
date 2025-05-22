package cl.perfulandia.inventario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.perfulandia.inventario.dto.StockDto;
import cl.perfulandia.inventario.feign.productoSucursal;
import cl.perfulandia.inventario.modelo.Movimiento;
import cl.perfulandia.inventario.modelo.Stock;
import cl.perfulandia.inventario.repository.MovimientoRepository;
import cl.perfulandia.inventario.repository.StockRepository; 

@Service
public class MovimientoService {

    private final MovimientoRepository movimientoRepository;

    private final StockRepository stockRepository; 

    @Autowired
    private productoSucursal productoSucursal;

    public MovimientoService(MovimientoRepository movimientoRepository, StockRepository stockRepository) {
        this.movimientoRepository = movimientoRepository;
        this.stockRepository = stockRepository;
    }

    public List<Movimiento> listar() {
        return movimientoRepository.findAll();
    }

    public Movimiento guardar(Movimiento movimiento){
        return movimientoRepository.save(movimiento);
    } 
    public void registrarMovimiento(Movimiento movimiento) {

        // Obtener el ID del producto relacionado
        Long productoId = movimiento.getProducto().getProductoId();

        // Usar stockRepository correctamente
        Stock stock = stockRepository.findByProductoProductoId(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        int cantidadActual = stock.getCantidad();
        int nuevaCantidad;

        if (movimiento.getTipo().equalsIgnoreCase("ENTRADA")) {
            nuevaCantidad = cantidadActual + movimiento.getCantidad();
        } else if (movimiento.getTipo().equalsIgnoreCase("SALIDA")) {
            nuevaCantidad = cantidadActual - movimiento.getCantidad();
        } else {
            throw new RuntimeException("Tipo de movimiento inválido");
        }

        stock.setCantidad(nuevaCantidad);
        stockRepository.save(stock);

        // Notificar a la sucursal
        StockDto dto = new StockDto();
        dto.setProductoId(productoId);
        dto.setNuevaCantidad(nuevaCantidad);

        productoSucursal.actualizarStockSucursal(movimiento.getSucursal().getSucursalId(), dto);
    }

    public Movimiento buscar(long id) {
        return movimientoRepository.findById(id).orElse(null);
    }

    public void eliminar(long id) {
        movimientoRepository.deleteById(id);
    }
}
