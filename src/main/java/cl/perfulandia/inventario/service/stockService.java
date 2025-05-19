package cl.perfulandia.inventario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import cl.perfulandia.inventario.SucursalStock.MezclaSucurdalBase;
import cl.perfulandia.inventario.modelo.Stock;
import cl.perfulandia.inventario.repository.StockRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class stockService {
    @Autowired
    private final stockRepository stockRepository;
    private final productoSucursal productoSucursal;

    public stockService(StockRepository stockRepository, productoSucursal productoSucursal) {
        this.stockRepository = stockRepository;
        this.productoSucursal = productoSucursal;
    }

    public MezclaSucurdalBase getStockConSucursal(Long stockId) {
        // Obtener stock de la base de datos
        Stock stock = stockRepository.findById(stockId)
            .orElseThrow(() -> new RuntimeException("Stock no encontrado"));

        // Llamar al microservicio de sucursal usando Feign
        MezclaSucurdalBase sucursal = productoSucursal.getSucursalById(stock.getSucursalId());

        // Combinar stock y sucursal en un DTO
        return new MezclaSucurdalBase(stock, sucursal);
    } 

    public List<Stock> obtenerPorProducto(Long productoId) {
        return stockRepository.findByProductoId(productoId);
    }

    public List<Stock> obtenerPorSucursal(Long sucursalId) {
        return stockRepository.findBySucursalId(sucursalId);
    }

    public Optional<Stock> obtenerPorId(Long id) {
        return stockRepository.findById(id);
    }

    public Stock guardar(Stock stock) {
        return stockRepository.save(stock);
    }

    public void reducirStock(Long stockId, int cantidad) {
        Stock stock = stockRepository.findById(stockId)
            .orElseThrow(() -> new EntityNotFoundException("Stock no encontrado"));

        if (stock.getCantidad() < cantidad) {
            throw new IllegalArgumentException("No hay suficiente stock disponible");
        }

        stock.setCantidad(stock.getCantidad() - cantidad);
        stockRepository.save(stock);
    }

    public void aumentarStock(Long stockId, int cantidad) {
        Stock stock = stockRepository.findById(stockId)
            .orElseThrow(() -> new EntityNotFoundException("Stock no encontrado"));

        stock.setCantidad(stock.getCantidad() + cantidad);
        stockRepository.save(stock);
    }
}