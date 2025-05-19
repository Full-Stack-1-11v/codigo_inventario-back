package cl.perfulandia.inventario.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import cl.perfulandia.inventario.modelo.Alerta;
import cl.perfulandia.inventario.repository.AlertaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class AlertaService {
    private final AlertaRepository alertaRepository;

    public Alerta crearAlerta(Alerta alerta) {
        alerta.setEstadoAlerta("pendiente");
        return alertaRepository.save(alerta);
    }

    public List <Alerta> obtenerTodas(){
        return alertaRepository.findAll();
    }
    public List<Alerta> obtenerPorEstado(String estado) {
        return alertaRepository.findByEstadoAlerta(estado);
    }
    
    public Optional<Alerta> obtenerPorId(Long alertaId){
        return alertaRepository.findById(alertaId);
    }

    public void marcarComoResuelta(Long alertaId) {
        Alerta alerta = alertaRepository.findById(alertaId)
            .orElseThrow(() -> new EntityNotFoundException("Alerta no encontrada"));

        alerta.setEstadoAlerta("resuelta");
        alertaRepository.save(alerta);
    }
}
