package cl.ecommerce.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.ecommerce.ecommerce.dto.ProductoDTO;
import cl.ecommerce.ecommerce.service.impl.ProductoServiceImpl;

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
    public ResponseEntity<ProductoDTO> insertProducto(@RequestBody ProductoDTO producto) {
        ProductoDTO newProducto = productoService.save(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProducto);
    }


}
