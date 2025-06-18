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

import cl.ovox.ecommerce.dto.UsuarioDTO;
import cl.ovox.ecommerce.response.ApiResponse;
import cl.ovox.ecommerce.service.impl.UsuarioServiceImpl;
import cl.ovox.ecommerce.util.RutUtil;

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

   /* @GetMapping("/{rut}")
    public ResponseEntity<ApiResponse<UsuarioDTO>> getById(@PathVariable Integer rut) {
        UsuarioDTO usuario = usuarioService.findByRut(rut);

        if (usuario == null) {
            return ApiResponse.notFound("No se encontró al usuario con RUT: " + rut);
        }

        return ApiResponse.success(usuario, "Se ha encontrado al usuario exitosamente");
    */ 
    @PostMapping
    public ResponseEntity<ApiResponse<UsuarioDTO>> insertUsuario(@RequestBody UsuarioDTO usuario) {
        Integer run = RutUtil.extraerRun(usuario.getRut());
        String dv = RutUtil.extraerDv(usuario.getRut());
        String rut = RutUtil.rutNormalizado(run, dv);

        usuario.setRut(rut);

        if (RutUtil.esRutValido(run, dv)){
            if (usuarioService.findByRut(rut) != null) {
            return ApiResponse.error(HttpStatus.CONFLICT, "El usuario ya existe", "U-POST-01");
            }
            else{
                UsuarioDTO newUsuario = usuarioService.save(usuario);
                return ApiResponse.success(newUsuario, "Se ha creado exitosamente el usuario");
                }
        }

        return ApiResponse.error(HttpStatus.BAD_REQUEST,"El RUT ingresado no es valido", "U-POST-02");
    }


    /*@PutMapping("/{rut}")
    public ResponseEntity<ApiResponse<UsuarioDTO>> updateUsuario(@PathVariable Integer rut, @RequestBody UsuarioDTO usuario) {
        if (usuarioService.findByRut(rut) == null) {
            return ApiResponse.notFound("No se ha encontrado a un usuario con el rut " + rut);
        }
    
        if (usuarioService.update(rut, usuario) != null) {
            ApiResponse.success(usuario, "Se actualizado exitosamente el usuario con el rut " + rut);
        }
        
        return ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "Algo a salido mal", "U-PUT-01");
    */
    

    /*@DeleteMapping("/{rut}")
    public ResponseEntity<?> deleteUsuario(@PathVariable Integer rut) {
        UsuarioDTO usuario = usuarioService.findByRut(rut);
        
        if (usuario != null) {
            usuarioService.delete(rut);
            return ApiResponse.success("Se ha eliminado exitosamente al usuario rut: " + rut); 
        }
        
        return ApiResponse.notFound("No se ha encontrado a ningún usuario con el rut " + rut);
    
    return null;}*/

}
