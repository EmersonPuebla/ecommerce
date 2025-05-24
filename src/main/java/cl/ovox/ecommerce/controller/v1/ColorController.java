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
import cl.ovox.ecommerce.service.impl.ColorServiceImpl;

@RestController
@RequestMapping("/api/v1/crud/colores")
public class ColorController {

    @Autowired
    private ColorServiceImpl colorService;

    @GetMapping 
    public ResponseEntity<List<ColorDTO>> getAll() {
        List<ColorDTO> colores = colorService.findAll();

        if (colores.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(colores);
    }

   @GetMapping("/{id}")
    public ResponseEntity<ColorDTO> getById(@PathVariable Integer id) {
        ColorDTO color = colorService.findById(id);

        if (color == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(color);
    }

     @PostMapping
    public ResponseEntity<?> insertColor(@RequestBody ColorDTO color) {

        if (colorService.findById(color.getId()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);         
        }

        ColorDTO newColor = colorService.save(color);
        return ResponseEntity.status(HttpStatus.CREATED).body(newColor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ColorDTO> updateColor(@PathVariable Integer id, @RequestBody ColorDTO color) {
        if (colorService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
    
        ColorDTO newColor = colorService.update(id, color);

        if (newColor != null) {
            return ResponseEntity.ok(color);        
        }
        
        return ResponseEntity.internalServerError().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            colorService.delete(id);
            return ResponseEntity.noContent().build();
        } catch ( Exception e ) {
            return  ResponseEntity.notFound().build();
        }
    }
    

}
