package cl.ovox.ecommerce.controller.v1;

import java.util.List;
import java.util.UUID;

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

import cl.ovox.ecommerce.dto.UsuarioDTO;
import cl.ovox.ecommerce.response.ApiResponse;
import cl.ovox.ecommerce.service.impl.UsuarioServiceImpl;

@RestController
@RequestMapping("/api/v1/crud/usuarios")

public class UsuarioControllerV1 {
 
    @Autowired
    private UsuarioServiceImpl usuarioService;


    @GetMapping 
    public ResponseEntity<ApiResponse<List<UsuarioDTO>>> getAll() {
        List<UsuarioDTO> usuarios = usuarioService.findAll();

        if (usuarios.isEmpty()) {
            return ApiResponse.notFound("No se han encontrado usuarios.");
        }

        String mensaje = "Se han encontrado " + usuarios.size() + " usuarios.";
        return ApiResponse.success(usuarios, mensaje);
    }

    @GetMapping("/{rut}")
    public ResponseEntity<ApiResponse<UsuarioDTO>> getById(@PathVariable Integer rut) {
        UsuarioDTO usuario = usuarioService.findByRut(rut);

        if (usuario == null) {
            return ApiResponse.notFound("No se encontró al usuario con RUT: " + rut);
        }

        return ApiResponse.success(usuario, "Se ha encontrado al usuario exitosamente");
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> insertUsuario(@RequestBody UsuarioDTO usuario) {
        if (usuarioService.findByRut(usuario.getRut()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);         
        }
        
        UsuarioDTO newUsuario = usuarioService.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUsuario);
    }


    @PutMapping("/{run}")
    public ResponseEntity<UsuarioDTO> updateUsuario(@PathVariable UUID id, @RequestBody UsuarioDTO usuario) {
        if (usuarioService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
    
        UsuarioDTO newUsuario = usuarioService.update(id, usuario);

        if (newUsuario != null) {
            return ResponseEntity.ok(usuario);        
        }
        
        return ResponseEntity.internalServerError().build();
    }



    @DeleteMapping("/{rut}")
    public ResponseEntity<?> deleteUsuario(@PathVariable Integer rut) {
        UsuarioDTO usuario = usuarioService.findByRut(rut);
        
        if (usuario != null) {
            return ResponseEntity.ok(usuario); 
        }
        
        return ResponseEntity.notFound().build();
    }

}
