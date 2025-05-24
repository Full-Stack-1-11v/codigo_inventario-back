package cl.perfulandia.inventario.service;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import cl.perfulandia.inventario.modelo.Movimiento;
import cl.perfulandia.inventario.repository.MovimientoRepository;

@Service
public class MovimientoService {

    private final MovimientoRepository movimientoRepository;

    
    public MovimientoService(MovimientoRepository movimientoRepository) {
        this.movimientoRepository = movimientoRepository;
    }
    
    public List<Movimiento> buscarPorSucursalId(Long sucursalId) {
        return movimientoRepository.findBySucursalId(sucursalId);
    }

    public List<Movimiento> buscarPorSucursalYProducto(Long sucursalId, Long productoId) {
        return movimientoRepository.findBySucursalAndProducto(sucursalId, productoId);
    }

    public List<Movimiento> buscarPorFechas(LocalDateTime inicio, LocalDateTime fin) {
        return movimientoRepository.findByFechaBetween(inicio, fin);
    }

    public Integer totalEntradasPorProducto(Long productoId) {
        Integer total = movimientoRepository.totalEntradasPorProducto(productoId);
        return total != null ? total : 0;
    }

}