package cl.perfulandia.inventario.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cl.perfulandia.inventario.dto.StockDto;
import cl.perfulandia.inventario.modelo.Stock;
import cl.perfulandia.inventario.service.ServiceStock;

@RestController
@RequestMapping("/api/v1/stock")
public class ControllerStock {

    private final ServiceStock serviceStock;
    public ControllerStock(ServiceStock serviceStock) {
        this.serviceStock = serviceStock;
    }
    
    //Un simple add por asiacaso
    @PostMapping("/agregar")
    public ResponseEntity <Stock> guardar (@RequestBody Stock stock){
        Stock stockNuevo = serviceStock.guardar(stock);
        return ResponseEntity.status(HttpStatus.CREATED).body(stockNuevo);
    }
    //Elimino stock por id
    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable long id){
        try {
            serviceStock.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    //Buscar por id stock
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Stock> buscarProducto (@PathVariable long id){
        try{
            Stock stock = serviceStock.buscar(id);
            return ResponseEntity.ok(stock);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

     // Obtener stock de producto en sucursal
    @GetMapping("/{sucursalId}/producto/{productoId}")
    public ResponseEntity<Stock> obtenerStock(
            @PathVariable Long sucursalId,
            @PathVariable Long productoId) {
        Stock stock = serviceStock.obtenerStockPorSucursalYProducto(sucursalId, productoId);
        return ResponseEntity.ok(stock);
    }

    // Listar todo el stock de una sucursal
    @GetMapping("/{sucursalId}")
    public ResponseEntity<List<Stock>> listarStockPorSucursal(@PathVariable Long sucursalId) {
        List<Stock> lista = serviceStock.listarStockPorSucursal(sucursalId);
        return ResponseEntity.ok(lista);
    }

    // Listar productos con stock bajo un umbral
    @GetMapping("/{sucursalId}/stock-bajo")
    public ResponseEntity<List<Stock>> listarStockBajo(
            @PathVariable Long sucursalId,
            @RequestParam Integer umbral) {
        List<Stock> lista = serviceStock.listarStockBajo(sucursalId, umbral);
        return ResponseEntity.ok(lista);
    }

    // Actualizar stock
    @PutMapping("/{sucursalId}/stock")
    public ResponseEntity<Stock> actualizarStock(
            @PathVariable Long sucursalId,
            @RequestBody StockDto dto) {
        Stock stock = serviceStock.actualizarStock(sucursalId, dto);
        return ResponseEntity.ok(stock);
    }
  
}
