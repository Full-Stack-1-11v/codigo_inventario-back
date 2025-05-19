package cl.perfulandia.inventario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import cl.perfulandia.inventario.modelo.Stock;
import cl.perfulandia.inventario.repository.StockRepository;
import jakarta.persistence.EntityNotFoundException;
@Service
@RequiredArgsConstructor
public class stockService {
    private final StockRepository stockRepository;

    public List<Stock> obtenerPorProducto(Long productoId) {
        return stockRepository.findByProductoId(productoId);
    }

    public List<Stock> obtenerPorSucursal(Long sucursalId) {
        return stockRepository.findBySucursalId(sucursalId);
    }

    public void aumentarStock(Long stockId, long cantidad) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'aumentarStock'");
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

