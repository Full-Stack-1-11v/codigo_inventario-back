package cl.perfulandia.inventario.dto;

import lombok.Data;

@Data
public class ProductoDto {
    private long productoId;
    private String nombreProducto;
    private Double precioProducto;
    // getters y setters
}
