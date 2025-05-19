package cl.ecommerce.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.ecommerce.ecommerce.dto.UnidadMedidaDTO;
import cl.ecommerce.ecommerce.service.impl.UnidadMedidaImpl;

@RestController
@RequestMapping("/api/crud/unidadmedida")
public class UnidadMedidaController {

    @Autowired
    UnidadMedidaImpl unidadMedidaService;

    @GetMapping
    public ResponseEntity<List<UnidadMedidaDTO>> getAll(){
        List<UnidadMedidaDTO> unidadMedida = unidadMedidaService.findAll();
        if (unidadMedida.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(unidadMedida);
    }

 

}
