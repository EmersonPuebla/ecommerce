package cl.ovox.ecommerce.controller;

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

import cl.ovox.ecommerce.dto.ProductoDTO;
import cl.ovox.ecommerce.service.impl.ProductoServiceImpl;

import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/crud/productos")
public class ProductoController {
    
    @Autowired
    private ProductoServiceImpl productoService;


    @GetMapping 
    public ResponseEntity<List<ProductoDTO>> getAll() {
        List<ProductoDTO> productos = productoService.findAll();

        if (productos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> getById(@PathVariable Integer id) {
        ProductoDTO producto = productoService.findById(id);

        if (producto == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(producto);
    }

    @PostMapping
    public ResponseEntity<?> insertProducto(@RequestBody ProductoDTO producto) {

        if (productoService.findById(producto.getId()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);         
        }

        ProductoDTO newProducto = productoService.save(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProducto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> updateProducto(@PathVariable Integer id, @RequestBody ProductoDTO producto) {
        if (productoService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
    
        ProductoDTO newProducto = productoService.update(id, producto);

        if (newProducto != null) {
            return ResponseEntity.ok(producto);        
        }
        
        return ResponseEntity.internalServerError().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            productoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch ( Exception e ) {
            return  ResponseEntity.notFound().build();
        }
    }
}
