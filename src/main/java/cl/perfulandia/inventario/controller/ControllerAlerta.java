package cl.perfulandia.inventario.controller;

import cl.perfulandia.inventario.modelo.Alerta;
import cl.perfulandia.inventario.service.AlertaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/alertas")
public class ControllerAlerta {

    private final AlertaService alertaService;

    // Crear una nueva alerta
    @PostMapping
    public ResponseEntity<Alerta> crearAlerta(@RequestBody Alerta alerta) {
        Alerta creada = alertaService.crearAlerta(alerta);
        return ResponseEntity.ok(creada);
    }

    // Obtener todas las alertas
    @GetMapping
    public ResponseEntity<List<Alerta>> listarTodas() {
        return ResponseEntity.ok(alertaService.obtenerTodas());
    }

    // Obtener una alerta por ID
    @GetMapping("/{id}")
    public ResponseEntity<Alerta> obtenerPorId(@PathVariable Long id) {
        return alertaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Obtener alertas por estado ("Stock_EN-PROCESO")
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Alerta>> obtenerPorTipo(@PathVariable String tipo) {
        return ResponseEntity.ok(alertaService.obtenerPorEstado(tipo));
    }


}