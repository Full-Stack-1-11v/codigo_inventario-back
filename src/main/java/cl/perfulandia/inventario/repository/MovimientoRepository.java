package cl.perfulandia.inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.perfulandia.inventario.modelo.Movimiento;


@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
    
}
