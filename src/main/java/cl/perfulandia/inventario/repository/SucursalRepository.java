package cl.perfulandia.inventario.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import cl.perfulandia.inventario.modelo.Sucursal;
@Repository
public interface SucursalRepository extends JpaRepository<Sucursal , Long> {
    
}
