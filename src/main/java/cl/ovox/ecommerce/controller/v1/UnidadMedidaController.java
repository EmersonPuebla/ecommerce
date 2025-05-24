package cl.ovox.ecommerce.controller.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.ovox.ecommerce.dto.UnidadMedidaDTO;
import cl.ovox.ecommerce.service.impl.UnidadMedidaImpl;

@RestController
@RequestMapping("/api/v1/crud/unidadmedida")

public class UnidadMedidaController {

    @Autowired
    private UnidadMedidaImpl unidadMedidaService;

    @GetMapping
    public ResponseEntity<List<UnidadMedidaDTO>> getAll(){
        List<UnidadMedidaDTO> unidadMedida = unidadMedidaService.findAll();
        if (unidadMedida.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(unidadMedida);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnidadMedidaDTO> getById(@PathVariable Integer id){
        UnidadMedidaDTO unidad_medida = unidadMedidaService.findById(id);
        if (unidad_medida == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(unidad_medida);
    }

    //@PostMapping
    //public ResponseEntity<?> insertUnidadMedida(@RequestBody UnidadMedidaDTO unidad_medida){
        
    //if (unidadMedidaService.findById(unidad_medida.getId()) != null) {
    //    return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    //}
    
    //UnidadMedidaDTO newUnidadMedida = unidadMedidaService.save(unidad_medida);
    //return ResponseEntity.status(HttpStatus.CREATED).body(newUnidadMedida);
    //}

    @PutMapping("/{id}")
    public ResponseEntity<UnidadMedidaDTO> updateUnidadMedida(@PathVariable Integer id, @RequestBody UnidadMedidaDTO unidad_medida) {
        if (unidadMedidaService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
    
        UnidadMedidaDTO newUnidadMedida = unidadMedidaService.update(id, unidad_medida);

        if (newUnidadMedida != null) {
            return ResponseEntity.ok(unidad_medida);        
        }
        
        return ResponseEntity.internalServerError().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            unidadMedidaService.delete(id);
            return ResponseEntity.noContent().build();
        } catch ( Exception e ) {
            return  ResponseEntity.notFound().build();
        }
    }
}
