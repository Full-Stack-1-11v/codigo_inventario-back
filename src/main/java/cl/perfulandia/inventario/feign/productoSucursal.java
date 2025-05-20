package cl.perfulandia.inventario.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cl.perfulandia.inventario.dto.SucursalDto;

@FeignClient(name="stockSucursal",url = "http://localhost:8082")
public interface productoSucursal {
    @GetMapping("/{id}")
    SucursalDto obtenerSucursal(@PathVariable("id")Long id);
}

