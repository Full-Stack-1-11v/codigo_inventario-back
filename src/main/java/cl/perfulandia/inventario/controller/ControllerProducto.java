package cl.perfulandia.inventario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.perfulandia.inventario.modelo.Producto;
import cl.perfulandia.inventario.service.ProductoService;

@RestController
@RequestMapping("/api/v1/producto")
public class ControllerProducto {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/listar")
    public ResponseEntity<List<Producto>> listarProductos(){
        List<Producto> productos = productoService.listarProductos();
        if (productos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productos);
        
    }

    @PostMapping("/agregar")
    public ResponseEntity <Producto> guardar (@RequestBody Producto producto){
        Producto productoNuevo = productoService.guardarProductos(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoNuevo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> buscarProducto (@PathVariable long id){
        try{
            Producto producto = productoService.buscarPorId(id);
            return ResponseEntity.ok(producto);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    //@GetMapping("/cod/{cod}")
    //public ResponseEntity<Producto> buscarPorCod (@PathVariable String cod){
        //try{
            //Producto producto = productoService.buscarPorCod(cod);
            //return ResponseEntity.ok(producto);
        //}catch(Exception e){
            //return ResponseEntity.notFound().build();
        //}
    //}


    //@PutMapping("/actualizar/{id}")
    //public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {
        //Producto productoActualizado = productoService.actualizarProducto(id, producto);
        //if (productoActualizado == null) {
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        //}
        //return ResponseEntity.ok(productoActualizado);
    //}

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable long id){
        try {
            productoService.eliminarProducto(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


}