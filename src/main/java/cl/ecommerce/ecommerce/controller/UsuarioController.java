package cl.ecommerce.ecommerce.controller;

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

import cl.ecommerce.ecommerce.dto.UsuarioDTO;
import cl.ecommerce.ecommerce.service.impl.UsuarioServiceImpl;

@RestController
@RequestMapping("/api/crud/usuarios")

public class UsuarioController {
 
    @Autowired
    private UsuarioServiceImpl usuarioService;


    @GetMapping 
    public ResponseEntity<List<UsuarioDTO>> getAll() {
        List<UsuarioDTO> usuarios = usuarioService.findAll();

        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{run}")
    public ResponseEntity<UsuarioDTO> getById(@PathVariable Integer run) {
        UsuarioDTO usuario = usuarioService.findById(run);

        if (usuario == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> insertUsuario(@RequestBody UsuarioDTO usuario) {
        if (usuarioService.findById(usuario.getRun()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);         
        }
        
        UsuarioDTO newUsuario = usuarioService.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUsuario);
    }

    @PutMapping("/{run}")
    public ResponseEntity<UsuarioDTO> updateUsuario(@PathVariable Integer run, @RequestBody UsuarioDTO usuario) {
        if (usuarioService.findById(run) == null) {
            return ResponseEntity.notFound().build();
        }
    
        UsuarioDTO newUsuario = usuarioService.update(run, usuario);

        if (newUsuario != null) {
            return ResponseEntity.ok(usuario);        
        }
        
        return ResponseEntity.internalServerError().build();
    }

    @DeleteMapping("/{run}")
    public ResponseEntity<?> deleteUsuario(@PathVariable Integer run) {
        UsuarioDTO usuario = usuarioService.findById(run);
        
        if (usuario != null) {
            return ResponseEntity.ok(usuario); 
        }
        
        return ResponseEntity.notFound().build();
    }

}
