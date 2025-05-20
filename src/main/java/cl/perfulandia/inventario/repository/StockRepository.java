package cl.perfulandia.inventario.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import cl.perfulandia.inventario.modelo.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    
}
