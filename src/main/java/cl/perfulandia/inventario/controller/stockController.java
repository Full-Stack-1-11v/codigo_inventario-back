package cl.perfulandia.inventario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.perfulandia.inventario.SucursalStock.MezclaSucurdalBase;
import cl.perfulandia.inventario.modelo.Stock;
import cl.perfulandia.inventario.service.stockService;
import lombok.RequiredArgsConstructor;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stock")
public class stockController {  
    @Autowired
    private stockService stockService;

    @GetMapping("/producto/{producto_Id}")
    public List<Stock> obtenerPorProducto(@PathVariable Long productoId) {
        return stockService.obtenerPorProducto(productoId);
    }

    @GetMapping("/sucursal/{sucursalId}")
    public List<Stock> obtenerPorSucursal(@PathVariable Long sucursalId) {
        return stockService.obtenerPorSucursal(sucursalId);
    }

    @PostMapping
    public ResponseEntity<Stock> crear(@RequestBody Stock stock) {
        return ResponseEntity.ok(stockService.guardar(stock));
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

   

    public stockController(stockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/{id}/con-sucursal")
    public ResponseEntity<MezclaSucurdalBase> getStockConSucursal(@PathVariable Long stockId) {
        return ResponseEntity.ok(stockService.getStockConSucursal(stockId));
    }
}

