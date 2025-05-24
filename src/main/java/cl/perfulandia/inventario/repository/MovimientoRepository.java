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
    
   List<Movimiento> findBySucursalId(Long sucursalId);

    // Corrigiendo referencia productoId -> m.producto.id y usando 'INGRESO' y 'SALIDA' según tu modelo
    @Query("SELECT COALESCE(SUM(CASE WHEN m.tipo = 'INGRESO' THEN m.cantidad ELSE -m.cantidad END), 0) FROM Movimiento m WHERE m.sucursalId = :sucursalId AND m.producto.id = :productoId")
    Integer obtenerStockActual(@Param("sucursalId") Long sucursalId, @Param("productoId") Long productoId);

    // Cambiado para que retorne List<Long> con productoId
    @Query("SELECT DISTINCT m.producto.id FROM Movimiento m WHERE m.sucursalId = :sucursalId")
    List<Long> obtenerProductosPorSucursal(@Param("sucursalId") Long sucursalId);

    // Buscar movimientos por producto y sucursal ordenados por fecha descendente
    @Query("SELECT m FROM Movimiento m WHERE m.sucursalId = :sucursalId AND m.producto.id = :productoId ORDER BY m.fecha DESC")
    List<Movimiento> findBySucursalAndProducto(@Param("sucursalId") Long sucursalId, @Param("productoId") Long productoId);

    // Buscar movimientos entre fechas
    @Query("SELECT m FROM Movimiento m WHERE m.fecha BETWEEN :inicio AND :fin")
    List<Movimiento> findByFechaBetween(@Param("inicio") LocalDateTime inicio, @Param("fin") LocalDateTime fin);

    // Total de entradas por producto (usando INGRESO)
    @Query("SELECT COALESCE(SUM(m.cantidad), 0) FROM Movimiento m WHERE m.producto.id = :idProducto AND m.tipo = 'INGRESO'")
    Integer totalEntradasPorProducto(@Param("idProducto") Long idProducto);

    // Stock total por sucursal (sumatoria de todos los movimientos)
    @Query("SELECT COALESCE(SUM(CASE WHEN m.tipo = 'INGRESO' THEN m.cantidad ELSE -m.cantidad END), 0) FROM Movimiento m WHERE m.sucursalId = :sucursalId")
    Integer obtenerStockTotalPorSucursal(@Param("sucursalId") Long sucursalId);
}

