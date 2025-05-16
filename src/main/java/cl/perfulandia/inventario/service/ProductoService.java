package cl.perfulandia.inventario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cl.perfulandia.inventario.modelo.Producto;
import cl.perfulandia.inventario.repository.ProductoRepository;
import jakarta.transaction.Transactional;

import java.util.List;
@Service
@Transactional
public class ProductoService {
    @Autowired
    ProductoRepository ProductoRepository;

    public List<Producto> listarProductos() {
        return ProductoRepository.findAll();
    }

    public Producto guardarProductos(Producto producto) {
        return ProductoRepository.save(producto);
    }

    //public Producto buscarPorCod(String cod) {
        //return ProductoRepository.buscarPorCod(cod).orElse(null);
    //}

    public Producto buscarPorId(long id) {
        return ProductoRepository.findById(id).get();
    }

    public void eliminarProducto(long id) {
        ProductoRepository.deleteById(id);
    }

    
}