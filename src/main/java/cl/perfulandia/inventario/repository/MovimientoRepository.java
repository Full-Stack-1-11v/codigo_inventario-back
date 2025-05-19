package cl.perfulandia.inventario.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.perfulandia.inventario.modelo.Movimiento;
import cl.perfulandia.inventario.modelo.Producto;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento,Long>    {
    List<Movimiento> findByProducto_ProductoId(Long productoId);

    List<Movimiento> findByTipoMovimiento(String tipoMovimiento);
    List<Movimiento> findByProducto(Producto producto);
}
