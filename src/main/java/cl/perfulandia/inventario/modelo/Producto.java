package cl.perfulandia.inventario.modelo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="producto")
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "producto_id")
    private Long productoId;
    @Column(name="nombre_producto")
    private String nombreProducto;
    @Column(name="descripcion")
    private String descripcion;
    @Column(name="marca")
    private String marca;
    private boolean activo;
    @Column(name="stock_minimo")
    private Integer stockMinimo;
    @Column(name="stock_actual")
    private int stockActual;
    @Column(name="precio_producto")
    private double precioProducto;
    
    private long sucursalId;
}