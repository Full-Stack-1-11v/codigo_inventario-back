package cl.perfulandia.inventario.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cl.perfulandia.inventario.service.StockService;
import cl.perfulandia.inventario.SucursalStock.MezclaSucurdalBase;
import cl.perfulandia.inventario.modelo.Producto;
import cl.perfulandia.inventario.modelo.Stock;

@RestController
@RequestMapping("/api/v1/stock")
public class ControllerStock {

    private final StockService stockService;

    // Constructor explícito para inyección (no usar @Autowired ni Lombok)
    public ControllerStock(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping("/agregar")
    public ResponseEntity <Producto> guardar (@RequestBody Stock stock){
        Producto stockNuevo = stockService.guardar(stock);
        return ResponseEntity.status(HttpStatus.CREATED).body(stockNuevo);
    }

    @PutMapping("/{id}/aumentar")
    public ResponseEntity<Void> aumentar(@PathVariable Long id, @RequestParam int cantidad) {
        stockService.aumentarStock(id, cantidad);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/reducir")
    public ResponseEntity<Void> reducir(@PathVariable Long id, @RequestParam int cantidad) {
        stockService.reducirStock(id, cantidad);
        return ResponseEntity.ok().build();
    }

  
}
