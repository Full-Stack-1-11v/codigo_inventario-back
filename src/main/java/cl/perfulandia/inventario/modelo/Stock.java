package cl.perfulandia.inventario.modelo;


import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="Stock")
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stockId;
    
    @Column(nullable = false)
    private int cantidad;
    
    @Column(nullable = false)
    private int stockMinimo;
    
    @Column(nullable = false)
    private String estado;
   
    private Long sucursalId; 

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @OneToMany(mappedBy = "stockOrigen")
    private List<Movimiento> movimientosOrigen;

    @OneToMany(mappedBy = "stockDestino")
    private List<Movimiento> movimientosDestino;

    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL)
    private List<Alerta> alertas;

    
}