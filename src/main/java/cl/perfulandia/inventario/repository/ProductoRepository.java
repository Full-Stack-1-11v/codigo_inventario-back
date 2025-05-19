package cl.perfulandia.inventario.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import cl.perfulandia.inventario.modelo.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByProductoId(Long productoId);
    List<Producto> findByProductoCod(String productoCod);
}
