package cl.perfulandia.inventario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.perfulandia.inventario.modelo.Sucursal;
import cl.perfulandia.inventario.service.SucursalService;
@RestController
@RequestMapping("/api/v1/sucursal")
public class ControllerSucursal {

    @Autowired
    private SucursalService sucursalService;

    @GetMapping("/listar")
    public ResponseEntity<List<Sucursal>> listarSucursal(){
        List<Sucursal> sucursales = sucursalService.listarSucursal();
        if (sucursales.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(sucursales);
        
    }

    @PostMapping("/agregar")
    public ResponseEntity <Sucursal> guardar (@RequestBody Sucursal sucursal){
        Sucursal sucursalNueva = sucursalService.guardarSucursal(sucursal);
        return ResponseEntity.status(HttpStatus.CREATED).body(sucursalNueva);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sucursal> buscarSucursal (@PathVariable long id){
        try{
            Sucursal sucursal = sucursalService.buscarSucursal(id);
            return ResponseEntity.ok(sucursal);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Sucursal> actualizarProducto (@PathVariable long id , @RequestBody Sucursal sucursal){
        try {
            Sucursal suc = sucursalService.buscarSucursal(id);
            suc.setId(sucursal.getId());
            suc.setNombre(sucursal.getNombre());
            suc.setDireccion(sucursal.getDireccion());
            sucursalService.guardarSucursal(suc);
            return ResponseEntity.ok(sucursal);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarSucursal(@PathVariable long id){
        try {
            sucursalService.eliminarSucursal(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


}
    


    



    



  

   
