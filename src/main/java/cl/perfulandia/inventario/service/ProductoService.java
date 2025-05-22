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
    //obtengo el producto por id
    public Producto obtenerProductoPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }
    
    //Obtengo los productos que estan activos
    public List<Producto> listarProductosActivos() {
        return repository.buscarProductosActivos();
    }
    
    //Lissto todos
    public List<Producto> listar() {
        return repository.findAll(); 
    }
    
    //Busco por el nombre
    public List<Producto> buscarProductosPorNombre(String nombre) {
        return repository.buscarPorNombre(nombre);
    }
    
    //Asctualizo producto
    public Producto actualizarProducto(Long id, Producto productoActualizado) {
        Producto producto = obtenerProductoPorId(id);

        producto.setNombreProducto(productoActualizado.getNombreProducto());
        producto.setDescripcion(productoActualizado.getDescripcion());
        producto.setPrecioProducto(productoActualizado.getPrecioProducto());
        producto.setActivo(productoActualizado.isActivo());

        return repository.save(producto);
    }

    //Agrego producto
    public Producto guardar(Producto producto) {
        return repository.save(producto); 
    }

    //Elimino producto
    public void eliminar (long id){
        repository.deleteById(id);
    }
}
