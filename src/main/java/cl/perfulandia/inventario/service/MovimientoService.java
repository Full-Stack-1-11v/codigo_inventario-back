package cl.perfulandia.inventario.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import cl.perfulandia.inventario.modelo.Movimiento;
import cl.perfulandia.inventario.repository.MovimientoRepository;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class MovimientoService {
    private final MovimientoRepository movimientoRepository;
    

    public List<Movimiento> obtenerPorProducto(Long productoId) {
        return movimientoRepository.findByProductoId(productoId);
    }

    public List<Movimiento> obtenerPorRangoFechas(LocalDateTime desde, LocalDateTime hasta) {
        return movimientoRepository.findByFechaHoraBetween(desde, hasta);
    }
}
