package cl.perfulandia.inventario.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import cl.perfulandia.inventario.modelo.Producto;
import feign.Param;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    // Buscar productos por nombre 
    @Query("SELECT p FROM Producto p WHERE LOWER(p.nombreProducto) LIKE LOWER(CONCAT('%', :nombreProducto, '%'))")
    List<Producto> buscarPorNombre(@Param("nombre") String nombreProducto);

    // Buscar productos activos
    @Query("SELECT p FROM Producto p WHERE p.activo = true")
    List<Producto> buscarProductosActivos();
}

