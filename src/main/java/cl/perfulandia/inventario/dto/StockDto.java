package cl.perfulandia.inventario.dto;
import lombok.Data;
@Data
public class StockDto {

    private Long productoId;
    private Integer nuevaCantidad;
    private double precioProducto;

}


