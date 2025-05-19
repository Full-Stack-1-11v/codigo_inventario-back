package cl.perfulandia.inventario.controller;
import cl.perfulandia.inventario.modelo.Movimiento;
import cl.perfulandia.inventario.modelo.Producto;
import cl.perfulandia.inventario.service.MovimientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/movimiento")
public class ControllerMovimiento {
    private final MovimientoService movimientoService;

    // Registrar un nuevo movimiento (ingreso, egreso, transferencia)
    @PostMapping
    public ResponseEntity<Movimiento> registrarMovimiento(@RequestBody Movimiento movimiento) {
        Movimiento registrado = movimientoService.agregarMovimiento(movimiento);
        return ResponseEntity.ok(registrado);
    }

    // Obtener todos los movimientos
    @GetMapping("/ListarTodos")
    public ResponseEntity<List<Movimiento>> listarTodos(@RequestBody Producto productoId) {
        return ResponseEntity.ok(movimientoService.obtenerTodos(productoId));
    }
    

}


