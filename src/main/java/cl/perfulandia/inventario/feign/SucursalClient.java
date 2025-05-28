package cl.perfulandia.inventario.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import cl.perfulandia.inventario.dto.SucursalDto;

@FeignClient(name = "sucursal-service", url = "https://codigo-sucursal-back.onrender.com")
public interface SucursalClient {

    @GetMapping("/api/sucursales")
    List<SucursalDto> obtenerTodasLasSucursales();
    
    @GetMapping("/api/sucursales/{id}")
    SucursalDto obtenerSucursalPorId(@PathVariable("id") Long id);

    @PostMapping("/inventario/sucursal/{sucursalId}/agregar")
    String asignarProductosASucursal(@PathVariable("sucursalId") Long sucursalId, @RequestBody List<Long> idProductos);
}
