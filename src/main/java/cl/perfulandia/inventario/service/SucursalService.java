package cl.perfulandia.inventario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cl.perfulandia.inventario.modelo.Sucursal;
import cl.perfulandia.inventario.repository.SucursalRepository;
import jakarta.transaction.Transactional;

import java.util.List;
@Service
@Transactional
public class SucursalService {
    @Autowired
    private SucursalRepository SucursalRepository;

    public List<Sucursal> listarSucursal() {
        return SucursalRepository.findAll();
    }

    public Sucursal guardarSucursal(Sucursal sucursal){
        return SucursalRepository.save(sucursal);
    }

    public Sucursal buscarSucursal(long id){
        return SucursalRepository.findById(id).get();
    }

    public void eliminarSucursal (long id){
        SucursalRepository.deleteById(id);
    }

    
}
