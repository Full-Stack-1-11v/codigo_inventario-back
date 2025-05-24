package cl.perfulandia.inventario.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Alerta {
    private Long productoId;
    private String nombreProducto;
    private Long sucursalId;
    private int stockActual;
    private int stockMinimo;
    private String mensaje;
}

