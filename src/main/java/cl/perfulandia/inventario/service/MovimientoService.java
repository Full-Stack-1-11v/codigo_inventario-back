package cl.perfulandia.inventario.service;
import java.util.List;
import org.springframework.stereotype.Service;
import cl.perfulandia.inventario.modelo.Movimiento;
import cl.perfulandia.inventario.repository.MovimientoRepository;

@Service
public class MovimientoService {
    private final MovimientoRepository repository;

    public MovimientoService(MovimientoRepository repository) {
        this.repository = repository; 
    }

    public List<Movimiento> listar() {
        return repository.findAll(); 
    }

    public Movimiento guardar(Movimiento movimiento) {
        return repository.save(movimiento); 
    }

    public Movimiento buscar(long id){
        return repository.findById(id).get();
    }

    public void eliminar (long id){
        repository.deleteById(id);
    }
}
