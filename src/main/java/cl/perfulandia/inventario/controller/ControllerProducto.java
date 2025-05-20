package cl.perfulandia.inventario.controller;

import java.util.List;
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
import cl.perfulandia.inventario.modelo.Producto;
import cl.perfulandia.inventario.service.ProductoService;


@RestController
@RequestMapping("/api/v1/producto")
public class ControllerProducto {

    private ProductoService productoService;

    public ControllerProducto(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Producto>> listarProductos(){
        List<Producto> productos = productoService.listar();
        if (productos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productos);
        
    }

    @PostMapping("/agregar")
    public ResponseEntity <Producto> guardar (@RequestBody Producto producto){
        Producto productoNuevo = productoService.guardar(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoNuevo);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Producto> buscarProducto (@PathVariable long id){
        try{
            Producto producto = productoService.buscar(id);
            return ResponseEntity.ok(producto);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    //@GetMapping("/cod/{cod}")
    //public ResponseEntity<List<Producto>> buscarPorCod (@PathVariable String cod){
        //try{
            //List<Producto> producto = productoService.obtenerPorCod(cod);
            //return ResponseEntity.ok(producto);
        //}catch(Exception e){
            //return ResponseEntity.notFound().build();
        //}
    //}


    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Producto> actualizar(@PathVariable Long id, @RequestBody Producto producto) {
        return ResponseEntity.ok(productoService.guardar(producto));
    }

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable long id){
        try {
            productoService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


}