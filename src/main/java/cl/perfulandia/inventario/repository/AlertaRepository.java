package cl.perfulandia.inventario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.perfulandia.inventario.modelo.Alerta;

@Repository
public interface AlertaRepository extends JpaRepository<Alerta, Long> {
    List<Alerta> findByEstadoAlerta(String estado);
    List<Alerta> findByTipoAlerta(String tipo);
    List<Alerta> findByProductoIdAndEstadoAlerta(Long productoId, String estado);
}
