package cl.perfulandia.inventario.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import cl.perfulandia.inventario.dto.StockDto;
import cl.perfulandia.inventario.dto.SucursalDto;

@FeignClient(name="stockSucursal",url = "http://localhost:8082")
public interface productoSucursal {
    
   @PutMapping("/api/v1/sucursales/{sucursalId}/stock")
    void actualizarStockSucursal(@PathVariable("sucursalId") Long sucursalId,@RequestBody StockDto stock);
   
                                  
    @GetMapping("/api/v1/inventario/sucursal/{id}")
    SucursalDto obtenerSucursalPorId(@PathVariable("id")Long id);

    


}


