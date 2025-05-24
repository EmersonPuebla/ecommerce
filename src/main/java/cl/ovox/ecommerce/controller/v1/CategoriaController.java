package cl.ovox.ecommerce.controller.v1;

import java.util.List;
import java.util.UUID;

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

import cl.ovox.ecommerce.dto.CategoriaDTO;
import cl.ovox.ecommerce.service.impl.CategoriaServiceImpl;

@RestController
@RequestMapping("/api/v1/crud/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaServiceImpl categoriaService;

    @GetMapping 
    public ResponseEntity<List<CategoriaDTO>> getAll() {
        List<CategoriaDTO> categorias = categoriaService.findAll();

        if (categorias.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(categorias);
    }

   @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> getById(@PathVariable UUID id) {
        CategoriaDTO categoria = categoriaService.findById(id);

        if (categoria == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(categoria);
    }

     @PostMapping
    public ResponseEntity<?> insertCategoria(@RequestBody CategoriaDTO categoria) {

        if (categoriaService.findById(categoria.getId()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);         
        }

        CategoriaDTO newCategoria = categoriaService.save(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCategoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> updateCategoria(@PathVariable UUID id, @RequestBody CategoriaDTO categoria) {
        if (categoriaService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
    
        CategoriaDTO newCategoria = categoriaService.update(id, categoria);

        if (newCategoria != null) {
            return ResponseEntity.ok(categoria);        
        }
        
        return ResponseEntity.internalServerError().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable UUID id) {
        try {
            categoriaService.delete(id);
            return ResponseEntity.noContent().build();
        } catch ( Exception e ) {
            return  ResponseEntity.notFound().build();
        }
    }
    

}
