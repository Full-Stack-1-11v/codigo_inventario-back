package cl.perfulandia.inventario.feign;


import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


import cl.perfulandia.inventario.modelo.Stock;
@FeignClient(name="stockSucursal",url = "http://localhost:8082")
public interface productoSucursal {
    @GetMapping("/stocks")
    List <Stock> obtenerStocks();
}

