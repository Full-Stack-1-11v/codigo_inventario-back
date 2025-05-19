package cl.perfulandia.inventario.service;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import cl.perfulandia.inventario.modelo.Producto;
import cl.perfulandia.inventario.repository.ProductoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class ProductoService {
    private final ProductoRepository productoRepository;

    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    public Optional<Producto> obtenerPorId(Long id) {
        return productoRepository.findById(id);
    }

    public Producto guardar(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto actualizar(Long id, Producto nuevoProducto) {
        return productoRepository.findById(id).map(productoExistente -> {
            productoExistente.setProductoId(nuevoProducto.getProductoId());
            productoExistente.setCod(nuevoProducto.getCod());
            productoExistente.setNombreProducto(nuevoProducto.getNombreProducto());
            productoExistente.setMarca(nuevoProducto.getMarca());
            productoExistente.setPrecioProducto(nuevoProducto.getPrecioProducto());
            return productoRepository.save(productoExistente);
        }).orElseThrow(() -> new EntityNotFoundException("Producto no encontrado con ID: " + id));
    }

    public void eliminar(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new EntityNotFoundException("Producto no encontrado con ID: " + id);
        }
        productoRepository.deleteById(id);
    }

    
}
