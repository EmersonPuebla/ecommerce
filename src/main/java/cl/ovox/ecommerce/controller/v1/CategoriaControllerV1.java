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
    public ResponseEntity<ApiResponse<List<CategoriaDTO>>> getAll() {
        List<CategoriaDTO> categorias = categoriaService.findAll();

        if (categorias.isEmpty()) {
            return ApiResponse.error(HttpStatus.NOT_FOUND, "No existen categorias disponibles.", "C-GET-01");       
        }
        return ApiResponse.success(categorias, "Se han encontrado " + categorias.size() + " categorias.");
    }

   @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoriaDTO>> getById(@PathVariable String nombre) {
        CategoriaDTO categoria = categoriaService.findByNombre(nombre);

        if (categoria == null) {
            return ApiResponse.error(HttpStatus.NOT_FOUND, "No existe la categoria ingresada.", "C-GET-NAME-01");       
        }

        return ApiResponse.success(categoria, "Se han encontrado la categoria " + categoria.getNombre() + " exitosamente.");
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
    public ResponseEntity<ApiResponse<CategoriaDTO>> updateCategoria(@PathVariable String nombre, @RequestBody CategoriaDTO categoria) {
        if (categoriaService.findByNombre(nombre) == null) {
            return ResponseEntity.notFound().build();
        }
    
        CategoriaDTO newCategoria = categoriaService.update(nombre, categoria);

        if (newCategoria != null) {
            return ApiResponse.success(newCategoria, "Categoria actualizada exitosamente.");        
        }
        
        return ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "La categoria no ha podido ser actualizada.", "C-PUT-01");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> eliminar(@PathVariable String nombre) {
        //try {
           // categoriaService.delete(nombre);
            // PENDIENTE ARREGLAR ESTE FOKIN ERROR 
            return null;//ApiResponse.success(newCategoria, "Categoria actualizada exitosamente.");
        //} catch ( Exception e ) {
        //    return ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "La categoria no se logró eliminar", "C-DELETE-01");
        //}
    }
    

}
