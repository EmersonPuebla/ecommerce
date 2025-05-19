package cl.ecommerce.ecommerce.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import cl.ecommerce.ecommerce.dto.PublicacionDTO;
import cl.ecommerce.ecommerce.service.impl.PublicacionServiceImpl;

@RestController
@RequestMapping("/api/crud/publicaciones")
public class PublicacionController {

    @Autowired
    private PublicacionServiceImpl publicacionService;


    @GetMapping 
    public ResponseEntity<List<PublicacionDTO>> getAll() {
        List<PublicacionDTO> productos = publicacionService.findAll();

        if (productos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublicacionDTO> getById(@PathVariable Integer id) {
        PublicacionDTO producto = publicacionService.findById(id);

        if (producto == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(producto);
    }

    @PostMapping
    public ResponseEntity<?> insertProducto(@RequestBody PublicacionDTO producto) {

        if (publicacionService.findById(producto.getId()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);         
        }

        PublicacionDTO newPublicacion = publicacionService.save(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPublicacion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublicacionDTO> updateProducto(@PathVariable Integer id, @RequestBody PublicacionDTO producto) {
        if (publicacionService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
    
        PublicacionDTO newPublicacion = publicacionService.update(id, producto);

        if (newPublicacion != null) {
            return ResponseEntity.ok(producto);        
        }
        
        return ResponseEntity.internalServerError().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            publicacionService.delete(id);
            return ResponseEntity.noContent().build();
        } catch ( Exception e ) {
            return  ResponseEntity.notFound().build();
        }
    }


}
