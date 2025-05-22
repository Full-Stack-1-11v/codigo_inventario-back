package cl.perfulandia.inventario.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cl.perfulandia.inventario.dto.ProductoDto;

@FeignClient(name = "producto-ms", url = "http://localhost:8081")
public interface clienteSucursal {
    @GetMapping("/productos/{id}")
    ProductoDto obtenerProducto(@PathVariable("id") Long id);
}