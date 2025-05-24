package cl.perfulandia.inventario.controller;
import cl.perfulandia.inventario.modelo.Movimiento;
import cl.perfulandia.inventario.service.MovimientoService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("movimientos")
public class ControllerMovimiento {

    private final MovimientoService movimientoService;

    public ControllerMovimiento(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    //Buscar movimiento Por sucursal
    @GetMapping("/sucursal/{sucursalId}")
    public ResponseEntity<List<Movimiento>> porSucursal(@PathVariable Long sucursalId) {
        return ResponseEntity.ok(movimientoService.buscarPorSucursalId(sucursalId));
    }

    //Buscar producto y sucursal
    @GetMapping("/producto")
    public ResponseEntity<List<Movimiento>> porProductoYSucursal(
            @RequestParam Long sucursalId,
            @RequestParam Long productoId) {
        return ResponseEntity.ok(movimientoService.buscarPorSucursalYProducto(sucursalId, productoId));
    }

    //Buscar movimiento por fecha
    @GetMapping("/fecha")
    public ResponseEntity<List<Movimiento>> porFechas(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin) {
        return ResponseEntity.ok(movimientoService.buscarPorFechas(inicio, fin));
    }

    //obtengo historial de entradas de los productos
    @GetMapping("/producto/{productoId}/entradas")
    public ResponseEntity<Integer> totalEntradas(@PathVariable Long productoId) {
        return ResponseEntity.ok(movimientoService.totalEntradasPorProducto(productoId));
    }
}

    



    
