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

    @GetMapping("/{rut}")
    public ResponseEntity<ApiResponse<UsuarioDTO>> getById(@PathVariable String rut) {
    Integer run = RutUtil.extraerRun(rut);
    String dv = RutUtil.extraerDv(rut);

    if (!RutUtil.esRutValido(run, dv)) {
        return ApiResponse.error(HttpStatus.BAD_REQUEST, "El RUT ingresado no es válido", "U-GET-01");
    }

    String rutNormalizado = RutUtil.rutNormalizado(run, dv);
    UsuarioDTO usuario = usuarioService.findByRut(rutNormalizado);

    if (usuario == null) {
        return ApiResponse.notFound("No se encontró al usuario con RUT: " + rutNormalizado);
    }

    return ApiResponse.success(usuario, "Se ha encontrado al usuario exitosamente");
}

 
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

    @PutMapping("/{rut}")
    public ResponseEntity<ApiResponse<UsuarioDTO>> updateUsuario(@PathVariable String rut, @RequestBody UsuarioDTO usuario) {
    Integer run = RutUtil.extraerRun(rut);
    String dv = RutUtil.extraerDv(rut);

    if (!RutUtil.esRutValido(run, dv)) {
        return ApiResponse.error(HttpStatus.BAD_REQUEST, "El RUT ingresado no es válido", "U-PUT-01");
    }

    String rutNormalizado = RutUtil.rutNormalizado(run, dv);
    UsuarioDTO existingUsuario = usuarioService.findByRut(rutNormalizado);

    if (existingUsuario == null) {
        return ApiResponse.notFound("No se ha encontrado un usuario con el RUT " + rutNormalizado);
    }

    usuario.setRut(rutNormalizado); 
    UsuarioDTO updatedUsuario = usuarioService.update(rutNormalizado, usuario);

    if (updatedUsuario != null) {
        return ApiResponse.success(updatedUsuario, "Se ha actualizado exitosamente el usuario con el RUT " + rutNormalizado);
    }

    return ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "No se pudo actualizar el usuario", "U-PUT-02");
}



    @DeleteMapping("/{rut}")
    public ResponseEntity<ApiResponse<Void>> deleteUsuario(@PathVariable String rut) {
        Integer run = RutUtil.extraerRun(rut);
        String dv = RutUtil.extraerDv(rut);

        if (!RutUtil.esRutValido(run, dv)) {
            return ApiResponse.error(HttpStatus.BAD_REQUEST, "El RUT ingresado no es válido", "U-DELETE-01");
        }

        String rutNormalizado = RutUtil.rutNormalizado(run, dv);
        UsuarioDTO usuario = usuarioService.findByRut(rutNormalizado);

        if (usuario == null) {
            return ApiResponse.notFound("No se ha encontrado a ningún usuario con el RUT " + rutNormalizado);
        }

        usuarioService.delete(rutNormalizado);
        return ApiResponse.success("Se ha eliminado exitosamente al usuario con RUT: " + rutNormalizado);
    }




}
