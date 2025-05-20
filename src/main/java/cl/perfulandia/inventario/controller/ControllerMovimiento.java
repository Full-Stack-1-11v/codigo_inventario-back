package cl.perfulandia.inventario.controller;
import cl.perfulandia.inventario.modelo.Movimiento;
import cl.perfulandia.inventario.service.MovimientoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movimiento")
public class ControllerMovimiento {
    private final MovimientoService movimientoService;

    public ControllerMovimiento(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @PostMapping("/agregar")
    public ResponseEntity <Movimiento> guardar (@RequestBody Movimiento movimiento){
        Movimiento movNuevo = movimientoService.guardar(movimiento);
        return ResponseEntity.status(HttpStatus.CREATED).body(movNuevo);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Movimiento>> listarMov(){
        List<Movimiento> movimientos = movimientoService.listar();
        if (movimientos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(movimientos);
        
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Movimiento> buscarMov (@PathVariable long id){
        try{
            Movimiento movimiento = movimientoService.buscar(id);
            return ResponseEntity.ok(movimiento);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<?> eliminarMov(@PathVariable long id){
        try {
            movimientoService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Movimiento> actualizar(@PathVariable Long id, @RequestBody Movimiento movimiento) {
        return ResponseEntity.ok(movimientoService.guardar(movimiento));
    }
    

}


