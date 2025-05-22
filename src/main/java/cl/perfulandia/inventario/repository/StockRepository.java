package cl.perfulandia.inventario.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import cl.perfulandia.inventario.modelo.Stock;
import feign.Param;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    // Buscar stock por sucursal y producto
    @Query("SELECT s FROM Stock s WHERE s.sucursalId = :sucursalId AND s.producto.id = :productoId")
    Optional<Stock> findBySucursalAndProducto(@Param("sucursalId") Long sucursalId, @Param("productoId") Long productoId);

    // Listar todo el stock de una sucursal
    @Query("SELECT s FROM Stock s WHERE s.sucursalId = :sucursalId")
    List<Stock> findAllBySucursal(@Param("sucursalId") Long sucursalId);

    // Buscar productos con stock bajo un umbral en una sucursal (alerta de reposición)
    @Query("SELECT s FROM Stock s WHERE s.sucursalId = :sucursalId AND s.cantidad < :umbral")
    List<Stock> findStockBajo(@Param("sucursalId") Long sucursalId, @Param("umbral") Integer umbral);
}


