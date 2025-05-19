package cl.perfulandia.inventario.SucursalStock;

import cl.perfulandia.inventario.modelo.Stock;
import lombok.Data;
@Data
public class MezclaSucurdalBase {
    private Stock stock;
    private SucursalStock sucursal;

    public MezclaSucurdalBase(Stock stock, SucursalStock sucursal) {
        this.stock = stock;
        this.sucursal = sucursal;
    }

}
