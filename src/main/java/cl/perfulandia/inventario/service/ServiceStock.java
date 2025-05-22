package cl.perfulandia.inventario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.perfulandia.inventario.dto.StockDto;
import cl.perfulandia.inventario.dto.SucursalDto;
import cl.perfulandia.inventario.feign.productoSucursal;
import cl.perfulandia.inventario.modelo.Producto;
import cl.perfulandia.inventario.modelo.Stock;
import cl.perfulandia.inventario.repository.ProductoRepository;
import cl.perfulandia.inventario.repository.StockRepository;
import feign.FeignException;
import jakarta.transaction.Transactional;

@Service
public class ServiceStock {
    private final StockRepository repository;
    public ServiceStock(StockRepository repository) {
        this.repository = repository; 
    }

    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private productoSucursal productoSucursal;

    //Actualizo Stock o creo 
    @Transactional
    public Stock actualizarStock(Long sucursalId, StockDto dto) {
        // Validar que la sucursal exista en el otro microservicio
    try {
        SucursalDto sucursal = productoSucursal.obtenerSucursalPorId(sucursalId);
        if (sucursal == null) {
            throw new RuntimeException("Sucursal no encontrada");
        }
    } catch (FeignException.NotFound e) {
        throw new RuntimeException("Sucursal no encontrada");
    } catch (FeignException e) {
        throw new RuntimeException("Error al consultar microservicio de sucursal");
    }

    // Buscar producto
    Producto producto = productoRepository.findById(dto.getProductoId())
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

    // Buscar stock existente o crear nuevo
    Stock stock = repository.findBySucursalAndProducto(sucursalId, dto.getProductoId())
            .orElse(new Stock());

    // Actualizar campos
    stock.setSucursalId(sucursalId);
    stock.setProducto(producto);
    stock.setCantidad(dto.getNuevaCantidad());

    // Guardar y devolver
    return repository.save(stock);
    }

    public void verificarSucursalExistente(Long sucursalId) {
        SucursalDto sucursal = productoSucursal.obtenerSucursalPorId(sucursalId);
        if (sucursal == null) {
            throw new RuntimeException("Sucursal no encontrada con ID: " + sucursalId);
        }
    }

    // Obtener stock por sucursal y producto
    public Stock obtenerStockPorSucursalYProducto(Long sucursalId, Long productoId) {
        return repository.findBySucursalAndProducto(sucursalId, productoId)
                .orElseThrow(() -> new RuntimeException("Stock no encontrado"));
    }

    // Listar todo el stock de una sucursal
    public List<Stock> listarStockPorSucursal(Long sucursalId) {
        return repository.findAllBySucursal(sucursalId);
    }

    // Buscar productos con stock bajo un umbral
    public List<Stock> listarStockBajo(Long sucursalId, Integer umbral) {
        return repository.findStockBajo(sucursalId, umbral);
    }
    
    //Guardar Stock manual
    public Stock guardar(Stock stock) {
        return repository.save(stock); 
    }

    //Buscar Stock por id
    public Stock buscar(long id){
        return repository.findById(id).get();
    }

    //Eliminar Stock
    public void eliminar (long id){
        repository.deleteById(id);
    }
}
