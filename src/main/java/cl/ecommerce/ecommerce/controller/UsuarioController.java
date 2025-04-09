package cl.ecommerce.ecommerce.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.ecommerce.ecommerce.dto.ResponseDTO;

@RestController
@RequestMapping("/api/crud/usuario")

public class UsuarioController {
    
    @GetMapping
    public ResponseDTO getAll(){
        return null;
    }

    @GetMapping("/{rut}")
    public ResponseDTO getByRut(){
        return null;
    }

    @PostMapping
    public ResponseDTO insertUsuario(){
        return null;
    }

    @PutMapping("/{rut}")
    public ResponseDTO updateUsuario(){
        return null;
    }

    @DeleteMapping("/{rut}")
    public ResponseDTO deleteUsuario(){
        return null;
    }
}
