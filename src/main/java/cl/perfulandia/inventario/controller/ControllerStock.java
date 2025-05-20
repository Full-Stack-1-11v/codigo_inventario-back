package cl.perfulandia.inventario.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import cl.perfulandia.inventario.modelo.Stock;
import cl.perfulandia.inventario.service.ServiceStock;

@RestController
@RequestMapping("/api/v1/stock")
public class ControllerStock {

    private final ServiceStock serviceStock;
    public ControllerStock(ServiceStock serviceStock) {
        this.serviceStock = serviceStock;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Stock>> listarStock(){
        List<Stock> cantidades = serviceStock.listar();
        if (cantidades.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cantidades);
        
    }

    @PostMapping("/agregar")
    public ResponseEntity <Stock> guardar (@RequestBody Stock stock){
        Stock stockNuevo = serviceStock.guardar(stock);
        return ResponseEntity.status(HttpStatus.CREATED).body(stockNuevo);
    }

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable long id){
        try {
            serviceStock.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Stock> buscarProducto (@PathVariable long id){
        try{
            Stock stock = serviceStock.buscar(id);
            return ResponseEntity.ok(stock);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Stock> actualizar(@PathVariable Long id, @RequestBody Stock stock) {
        return ResponseEntity.ok(serviceStock.guardar(stock));
    }
   

  
}
