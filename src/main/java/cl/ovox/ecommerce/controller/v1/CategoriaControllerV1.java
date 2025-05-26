package cl.ovox.ecommerce.controller.v1;

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

import cl.ovox.ecommerce.dto.CategoriaDTO;
import cl.ovox.ecommerce.response.ApiResponse;
import cl.ovox.ecommerce.service.impl.CategoriaServiceImpl;

@RestController
@RequestMapping("/api/v1/crud/categorias")
public class CategoriaControllerV1 {

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
    public ResponseEntity<CategoriaDTO> getById(@PathVariable String nombre) {
        CategoriaDTO categoria = categoriaService.findByNombre(nombre);

        if (categoria == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(categoria);
    }

     @PostMapping
    public ResponseEntity<?> insertCategoria(@RequestBody CategoriaDTO categoria) {

        if (categoriaService.findByNombre(categoria.getNombre()) != null) {
            return ApiResponse.error(HttpStatus.CONFLICT, "La categoria " + categoria.getNombre() + " ya existe.", "C-POST-01");       
        }

        CategoriaDTO newCategoria = categoriaService.save(categoria);
        return ApiResponse.success(newCategoria, "Se ha insertado exitosamente la categoria");
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> updateCategoria(@PathVariable String nombre, @RequestBody CategoriaDTO categoria) {
        if (categoriaService.findByNombre(nombre) == null) {
            return ResponseEntity.notFound().build();
        }
    
        CategoriaDTO newCategoria = categoriaService.update(nombre, categoria);

        if (newCategoria != null) {
            return ResponseEntity.ok(categoria);        
        }
        
        return ResponseEntity.internalServerError().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable String nombre) {
        try {
            categoriaService.delete(nombre);
            return ResponseEntity.noContent().build();
        } catch ( Exception e ) {
            return  ResponseEntity.notFound().build();
        }
    }
    

}
