package cl.perfulandia.inventario.modelo;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="Movimiento")
@NoArgsConstructor
@AllArgsConstructor
public class Movimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movimientoId;
    
    @Column(nullable = false)
    private int cantidad;
    
    @Column(nullable = false)
    private String tipoMovimiento;

  
    @Column(nullable = false)
    private String fecha_hora;

    @Column(nullable = false)
    private String observaciones;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "stock_origen_id")
    private Stock stockOrigen;

    @ManyToOne
    @JoinColumn(name = "stock_destino_id")
    private Stock stockDestino;
}
