package cl.perfulandia.inventario.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.perfulandia.inventario.modelo.Movimiento;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento,Long>    {
    List<Movimiento> findByProductoId(Long productoId);
    List<Movimiento> findByTipoMovimiento(String tipoMovimiento);
    List<Movimiento> findByFechaHoraBetween(LocalDateTime desde, LocalDateTime hasta);
}
