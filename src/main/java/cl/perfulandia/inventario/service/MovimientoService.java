package cl.perfulandia.inventario.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import cl.perfulandia.inventario.modelo.Movimiento;
import cl.perfulandia.inventario.modelo.Producto;
import cl.perfulandia.inventario.repository.MovimientoRepository;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class MovimientoService {
    private final MovimientoRepository movimientoRepository;
    

    public List<Movimiento> obtenerPorProducto(Producto producto) {
        return movimientoRepository.findByProducto(producto);
    }

  

    public Movimiento agregarMovimiento(Movimiento movimiento) {
        return movimientoRepository.save(movimiento);
    }


    public List<Movimiento> obtenerTodos(Producto productoId) {
        return movimientoRepository.findByProducto(productoId);
    }
}
