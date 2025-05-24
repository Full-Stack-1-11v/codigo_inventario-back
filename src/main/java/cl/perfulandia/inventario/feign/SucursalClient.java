package cl.perfulandia.inventario.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cl.perfulandia.inventario.dto.SucursalDto;

@FeignClient(name = "sucursal-service", url = "https://codigo-sucursal-back.onrender.com")
public interface SucursalClient {

    @GetMapping("/api/sucursales")
    List<SucursalDto> obtenerTodasLasSucursales();
    
    @GetMapping("/api/sucursales/{id}")
    SucursalDto obtenerSucursalPorId(@PathVariable("id") Long id);
}
