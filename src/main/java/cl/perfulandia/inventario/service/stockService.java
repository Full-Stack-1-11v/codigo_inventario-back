package cl.perfulandia.inventario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import cl.perfulandia.inventario.modelo.Stock;
import cl.perfulandia.inventario.repository.StockRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class StockService {
    private final StockRepository stockRepository;

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
