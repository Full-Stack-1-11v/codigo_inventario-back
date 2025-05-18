package cl.perfulandia.inventario.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


import cl.perfulandia.inventario.modelo.Stock;
@FeignClient(name="sucursal")
public interface productoSucursal {
    @GetMapping("/sucursales")
    List<Stock> obtenerStocks();
}

