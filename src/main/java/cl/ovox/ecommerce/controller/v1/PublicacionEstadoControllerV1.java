package cl.ovox.ecommerce.controller.v1;

import org.springframework.web.bind.annotation.RestController;

import cl.ovox.ecommerce.dto.PublicacionEstadoDTO;
import cl.ovox.ecommerce.service.impl.PublicacionEstadoServiceImpl;

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


@RestController
@RequestMapping("api/v1/crud/publicacionestado")
public class PublicacionEstadoControllerV1 {
@Autowired
    private PublicacionEstadoServiceImpl publicacionEstadoService;

    @GetMapping 
    public ResponseEntity<List<PublicacionEstadoDTO>> getAll() {
        List<PublicacionEstadoDTO> publicacionEstado = publicacionEstadoService.findAll();

        if (publicacionEstado.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(publicacionEstado);
    }

   @GetMapping("/{id}")
    public ResponseEntity<PublicacionEstadoDTO> getById(@PathVariable Integer id) {
        PublicacionEstadoDTO publicacionEstado = publicacionEstadoService.findById(id);

        if (publicacionEstado == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(publicacionEstado);
    }

     @PostMapping
    public ResponseEntity<?> insertPublicacionEstado(@RequestBody PublicacionEstadoDTO publicacionEstado) {

        if (publicacionEstadoService.findById(publicacionEstado.getId()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);         
        }

        PublicacionEstadoDTO newPublicacionEstado = publicacionEstadoService.save(publicacionEstado);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPublicacionEstado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            publicacionEstadoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch ( Exception e ) {
            return  ResponseEntity.notFound().build();
        }
    }

}
