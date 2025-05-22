package cl.perfulandia.inventario.controller;
import cl.perfulandia.inventario.modelo.Movimiento;
import cl.perfulandia.inventario.service.MovimientoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/api/v1/movimiento")
public class ControllerMovimiento {
    private final MovimientoService movimientoService;

    public ControllerMovimiento(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }
    //Obtener movimiento por id
    @GetMapping("/{id}")
    public ResponseEntity<Movimiento> obtenerMovimiento(@PathVariable Long id) {
        Movimiento movimiento = movimientoService.obtenerMovimientoPorId(id);
        return ResponseEntity.ok(movimiento);
    }

    //Listar por sucursal y producto
    @GetMapping("/sucursal/SyP")
    public ResponseEntity<List<Movimiento>> listarPorSucursalYProducto(
            @RequestParam Long sucursalId,
            @RequestParam Long productoId) {
        List<Movimiento> lista = movimientoService.findBySucursalIdAndProductoProductoId(sucursalId, productoId);
        return ResponseEntity.ok(lista);
    }
    
    //Elimino movimiento
    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<?> eliminarMov(@PathVariable long id){
        try {
            movimientoService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    //Obtengo entre rangos de fecha
    @GetMapping("/rango-fechas")
    public ResponseEntity<List<Movimiento>> listarEntreFechas(
            @RequestParam String inicio,
            @RequestParam String fin) {
        LocalDateTime inicioDate = LocalDateTime.parse(inicio);
        LocalDateTime finDate = LocalDateTime.parse(fin);
        List<Movimiento> lista = movimientoService.listarMovimientosEntreFechas(inicioDate, finDate);
        return ResponseEntity.ok(lista);
    }
    

}


