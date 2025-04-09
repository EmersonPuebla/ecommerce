package cl.ecommerce.ecommerce.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cl.ecommerce.ecommerce.dto.PublicacionDTO;
import cl.ecommerce.ecommerce.service.impl.PublicacionServiceImpl;

@RestController
@RequestMapping("/api/crud/publicaciones")
public class PublicacionController {

    @Autowired
    private PublicacionServiceImpl publicacionService;

    @GetMapping
    public ResponseEntity<List<PublicacionDTO>> getAll(@PathVariable Integer id) {
        List<PublicacionDTO> publicaciones = publicacionService.findAll();
        if (publicaciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(publicaciones);
    }
    


}
