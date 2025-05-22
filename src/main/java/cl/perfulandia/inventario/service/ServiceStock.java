package cl.perfulandia.inventario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.perfulandia.inventario.dto.StockDto;
import cl.perfulandia.inventario.dto.SucursalDto;
import cl.perfulandia.inventario.feign.productoSucursal;
import cl.perfulandia.inventario.modelo.Stock;
import cl.perfulandia.inventario.repository.StockRepository;

@Service
public class ServiceStock {
    private final StockRepository repository;
    public ServiceStock(StockRepository repository) {
        this.repository = repository; 
    }

    @Autowired
    private productoSucursal productoSucursal;

    public void actualizarStockYNotificar(Long productoId, int nuevaCantidad, Long sucursalId) {
        // Actualizar stock local
        Stock stock = repository.findById(productoId)
                        .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        stock.setCantidad(nuevaCantidad);
        repository.save(stock);

        // Crear el DTO
        StockDto dto = new StockDto();
        dto.setProductoId(productoId);
        dto.setNuevaCantidad(nuevaCantidad);

        // Notificar a la sucursal
        productoSucursal.actualizarStockSucursal(sucursalId, dto);
    }

    public void verificarSucursalExistente(Long sucursalId) {
        SucursalDto sucursal = productoSucursal.obtenerSucursal(sucursalId);
        if (sucursal == null) {
            throw new RuntimeException("Sucursal no encontrada con ID: " + sucursalId);
        }
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
