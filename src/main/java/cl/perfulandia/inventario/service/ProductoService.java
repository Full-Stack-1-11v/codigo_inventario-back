package cl.perfulandia.inventario.service;
import java.util.List;

import org.springframework.stereotype.Service;
import cl.perfulandia.inventario.modelo.Producto;
import cl.perfulandia.inventario.repository.ProductoRepository;

@Service
public class ProductoService {
    private final ProductoRepository repository;

    public ProductoService(ProductoRepository repository) {
        this.repository = repository; 
    }

    public List<Producto> listar() {
        return repository.findAll(); 
    }

    public Producto guardar(Producto producto) {
        return repository.save(producto); 
    }

    public Producto buscar(long id){
        return repository.findById(id).get();
    }

    public void eliminar (long id){
        repository.deleteById(id);
    }
}
