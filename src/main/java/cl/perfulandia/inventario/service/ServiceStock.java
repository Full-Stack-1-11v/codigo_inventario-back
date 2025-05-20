package cl.perfulandia.inventario.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.perfulandia.inventario.modelo.Stock;
import cl.perfulandia.inventario.repository.StockRepository;

@Service
public class ServiceStock {
    private final StockRepository repository;
    public ServiceStock(StockRepository repository) {
        this.repository = repository; 
    }

    public List<Stock> listar() {
        return repository.findAll(); 
    }
    
    public Stock guardar(Stock stock) {
        return repository.save(stock); 
    }

    public Stock buscar(long id){
        return repository.findById(id).get();
    }

    public void eliminar (long id){
        repository.deleteById(id);
    }
}
