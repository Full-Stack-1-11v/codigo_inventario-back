package cl.perfulandia.inventario.modelo;
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
    @Column(name="movimiento_id")
    private Long movimientoId;
    @Column(name="fecha")
    private String fecha;
    @Column(name="tipo")
    private String tipo; // INGRESO o SALIDA
    @Column(name="cantidad")
    private Integer cantidad;

    @ManyToOne
    @JoinColumn(name="producto_id")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name="sucursal_id")
    private Sucursal sucursal;
}
