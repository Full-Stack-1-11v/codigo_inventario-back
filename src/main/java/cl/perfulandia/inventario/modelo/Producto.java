package cl.perfulandia.inventario.modelo;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
    private String productoCod;

    @Column(nullable = false)
    private String nombreProducto;
    
    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private float precioProducto;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<Stock> stocks;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<Movimiento> movimientos;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<Stock> alertas;
}