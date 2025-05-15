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
@Table(name="Producto")
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productoId;
    
    @Column(unique=true,length = 20,nullable = false)
    private String cod;

    @Column(nullable = false)
    private String nombreProducto;
    
    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private double precioProducto;
}