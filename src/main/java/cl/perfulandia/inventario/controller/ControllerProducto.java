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
import org.springframework.web.bind.annotation.RequestParam;
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

    //Listar Todos los Producto
    @GetMapping("/listar")
    public ResponseEntity<List<Producto>> listarProductos(){
        List<Producto> productos = productoService.listar();
        if (productos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productos);
        
    }
    
    //Listo productos activos
    @GetMapping("/listar/activos")
    public ResponseEntity<List<Producto>> listarProductosActivos() {
        List<Producto> productos = productoService.listarProductosActivos();
        return ResponseEntity.ok(productos);
    }
    
    //Agrego producto
    @PostMapping("/agregar")
    public ResponseEntity <Producto> guardar (@RequestBody Producto producto){
        Producto productoNuevo = productoService.guardar(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoNuevo);
    }

    //Busco producto por id
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Producto> buscarProducto (@PathVariable long id){
        try{
            Producto producto = productoService.obtenerProductoPorId(id);
            return ResponseEntity.ok(producto);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    
    //Busco por nombre
    @GetMapping("/buscar/nombre")
    public ResponseEntity<List<Producto>> buscarPorNombre(@RequestParam String nombre) {
        List<Producto> productos = productoService.buscarProductosPorNombre(nombre);
        return ResponseEntity.ok(productos);
    }

    //Actualizo Producto por id
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(
            @PathVariable Long id,
            @RequestBody Producto producto) {
        Producto actualizado = productoService.actualizarProducto(id, producto);
        return ResponseEntity.ok(actualizado);
    }

    //Eliminar producto por id
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