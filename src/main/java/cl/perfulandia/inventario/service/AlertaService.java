package cl.perfulandia.inventario.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cl.perfulandia.inventario.modelo.Alerta;
import cl.perfulandia.inventario.modelo.Producto;
import cl.perfulandia.inventario.repository.MovimientoRepository;
import cl.perfulandia.inventario.repository.ProductoRepository;

@Service
public class AlertaService {

    private final ProductoRepository productoRepository;
    private final MovimientoRepository movimientoRepository;

    public AlertaService(ProductoRepository productoRepository, MovimientoRepository movimientoRepository) {
        this.productoRepository = productoRepository;
        this.movimientoRepository = movimientoRepository;
    }

    public List<Alerta> verificarStockBajo(Long sucursalId) {
        List<Alerta> alertas = new ArrayList<>();
        List<Producto> productos = productoRepository.findAll();

        for (Producto producto : productos) {
            Integer stockActual = movimientoRepository.obtenerStockActual(sucursalId, producto.getProductoId());

            if (stockActual == null) stockActual = 0;

            if (stockActual < producto.getStockMinimo()) {
                Alerta alerta = new Alerta(
                        producto.getProductoId(),
                        producto.getNombreProducto(),
                        sucursalId,
                        stockActual,
                        producto.getStockMinimo(),
                        "⚠ Stock bajo para el producto '" + producto.getNombreProducto() + "' en la sucursal " + sucursalId
                );
                alertas.add(alerta);
            }
        }

        return alertas;
    }
}
