package cl.perfulandia.inventario.repository;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cl.perfulandia.inventario.modelo.Movimiento;
import feign.Param;





@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
    // Buscar movimientos por producto y sucursal ordenados por fecha descendente
    @Query("SELECT m FROM Movimiento m WHERE m.sucursalId = :sucursalId AND m.producto.id = :productoId ORDER BY m.fecha DESC")
    List<Movimiento> findBySucursalAndProducto(@Param("sucursalId") Long sucursalId, @Param("productoId") Long productoId);

    // Buscar movimientos entre fechas
    @Query("SELECT m FROM Movimiento m WHERE m.fecha BETWEEN :inicio AND :fin")
    List<Movimiento> findByFechaBetween(@Param("inicio") LocalDateTime inicio, @Param("fin") LocalDateTime fin);

    // Total de entradas por producto
    @Query("SELECT SUM(m.cantidad) FROM Movimiento m WHERE m.tipo = 'ENTRADA' AND m.producto.id = :productoId")
    Integer totalEntradasPorProducto(@Param("productoId") Long productoId);

    List<Movimiento> findBySucursalIdAndProductoProductoId(Long sucursalId, Long productoId);
}

