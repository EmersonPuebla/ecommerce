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

import cl.ovox.ecommerce.dto.ColorDTO;
import cl.ovox.ecommerce.response.ApiResponse;
import cl.ovox.ecommerce.service.impl.ColorServiceImpl;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/crud/colores")
public class ColorControllerV1 {

    @Autowired
    private ColorServiceImpl colorService;

    @GetMapping 
    public ResponseEntity<ApiResponse<List<ColorDTO>>> getAll() {
        List<ColorDTO> colores = colorService.findAll();

        if (colores.isEmpty()) {
            return ApiResponse.notFound("No se han encontrado colores");
        }
        
        String mensaje = "Se han encontrado " + colores.size() + " colores.";
        return ApiResponse.success(colores, mensaje);
    }

   @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ColorDTO>> getById(@PathVariable Integer id) {
        ColorDTO color = colorService.findById(id);

        if (color == null) {
            return ApiResponse.notFound("No se encontro el color con ID " + id);
        }

        return ApiResponse.success(color, "Se ha encontrado el color exitosamente.");
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ColorDTO>> insertColor(@RequestBody ColorDTO color) {

        String nombre = color.getNombre().trim().toUpperCase();
        color.setNombre(nombre);

        if (color.getId() != null && colorService.findById(color.getId()) != null) {
            return ApiResponse.error(HttpStatus.CONFLICT, "El color con ID '" + color.getId() + "' ya existe. Por favor, no proporcione un ID para la creación, o use PUT para actualizar.", "PE-POST-01");
        }

        if (colorService.findByNombre(nombre) != null) {
             return ApiResponse.error(HttpStatus.CONFLICT, "El color con nombre '" + nombre + "' ya existe.", "CO-POST-02");
        }

        ColorDTO savedColor = colorService.save(color);
  
        return ApiResponse.success(savedColor, "Se ha insertado exitosamente el color.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ColorDTO>> updateColor(@Valid @PathVariable Integer id, @RequestBody ColorDTO color) {
        
        String nombre = color.getNombre().trim().toUpperCase();
        color.setNombre(nombre);
        color.setId(id);
        
        if (colorService.findById(id) == null) {
            return ApiResponse.notFound("No se ha encontrado el color con el ID " + id);
        }

        if (colorService.findByNombre(nombre) != null) {
             return ApiResponse.error(HttpStatus.CONFLICT, "El color con nombre '" + nombre + "' ya existe.", "CO-PUT-01");
        }

        if (colorService.update(id, color) != null){
            return ApiResponse.success(color, "El color ID " + id + " se ha actualizado exitosamente");
        }
    
        return ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "No se ha podido actualizar el color por un error interno.", "CO-PUT-02");

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Integer id) {
        if (colorService.findById(id) == null){
            return ApiResponse.notFound("No existe un color con ID " + id);
        }
        
        if (colorService.delete(id)) {
            return ApiResponse.success("El color " + id + " se ha eliminado exitosamente.");
        }
        return ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "No se ha podido eliminar el color por un error interno", "CO-DEL-02");
    }
}
