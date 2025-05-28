package cl.perfulandia.inventario.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.perfulandia.inventario.modelo.Alerta;
import cl.perfulandia.inventario.service.AlertaService;

@RestController
@RequestMapping("/api/inventario/alertas")
public class ControllerAlerta {

    private final AlertaService alertaService;

    public ControllerAlerta(AlertaService alertaService) {
        this.alertaService = alertaService;
    }
    
    @GetMapping("/sucursal/{sucursalId}")
    public List<Alerta> obtenerAlertasPorSucursal(@PathVariable Long sucursalId) {
        return alertaService.verificarStockBajo(sucursalId);
    }
}