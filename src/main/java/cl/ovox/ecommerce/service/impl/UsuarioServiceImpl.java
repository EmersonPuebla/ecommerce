package cl.ovox.ecommerce.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.ovox.ecommerce.dto.UsuarioDTO;
import cl.ovox.ecommerce.repository.UsuarioRepository;
import cl.ovox.ecommerce.service.IUsuarioService;
import cl.ovox.ecommerce.util.RutUtil;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UsuarioServiceImpl implements IUsuarioService{
    
    @Autowired 
    UsuarioRepository usuarioRepository;

    public List<UsuarioDTO> findAll() {
        return usuarioRepository.findAll();
    }

    public UsuarioDTO save(UsuarioDTO usuario) {
    try {
            
        Optional<UsuarioDTO> existingUsuario = usuarioRepository.findByRut(usuario.getRut());

        if (existingUsuario.isPresent()) {
            throw new IllegalArgumentException("El usuario con el RUT : '" + usuario.getRut() + "' ya existe.");
        }

        if (usuario.getRut() == null || usuario.getRut().trim().isEmpty()) {
            throw new IllegalArgumentException("El RUT no puede ser nulo o vacío.");
        }

    } catch (NumberFormatException e) {
        throw new IllegalArgumentException("Error de formato intenta de nuevo.");
    }

        return usuarioRepository.save(usuario);
    }

@Override
@Transactional
public UsuarioDTO update(String rut, UsuarioDTO usuario) {
    // Normalizar el RUT
    String rutFormateado = RutUtil.rutNormalizado(RutUtil.extraerRun(rut), RutUtil.extraerDv(rut));

    // Buscar usuario existente
    Optional<UsuarioDTO> existingUsuario = usuarioRepository.findByRut(rutFormateado);
    if (existingUsuario.isEmpty()) {
        throw new IllegalArgumentException("No se encontró ningún usuario con el RUT: " + rutFormateado);
    }

    // Actualizar los campos del usuario existente
    UsuarioDTO usuarioExistente = existingUsuario.get();
    usuarioExistente.setPnombre(usuario.getPnombre());
    usuarioExistente.setSnombre(usuario.getSnombre());
    usuarioExistente.setAppaterno(usuario.getAppaterno());
    usuarioExistente.setApmaterno(usuario.getApmaterno());
    usuarioExistente.setFechaNacimiento(usuario.getFechaNacimiento());
    usuarioExistente.setCorreo(usuario.getCorreo());
    usuarioExistente.setTelefono(usuario.getTelefono());

    // Guardar cambios
    return usuarioRepository.save(usuarioExistente);
}


    public UsuarioDTO findByRut(String rut) {        

        String rutFormateado = 
        RutUtil.rutNormalizado(RutUtil.extraerRun(rut), RutUtil.extraerDv(rut));

        return usuarioRepository.findByRut(rutFormateado).orElse(null);
    }


/* 
    public UsuarioDTO findByRut(Integer rut) {
        return usuarioRepository.findByRut(rut).orElse(null);
    }
*/
    //deleteOrDeactivateByRun
    @Override
    public void delete(String rut) {
        String rutFormateado = 
            RutUtil.rutNormalizado(RutUtil.extraerRun(rut), RutUtil.extraerDv(rut));

        Optional<UsuarioDTO> usuario = usuarioRepository.findByRut(rutFormateado);

        if (usuario.isPresent()) {
            usuarioRepository.delete(usuario.get());
        } else {
            throw new IllegalArgumentException("No se encontró ningún usuario con el RUT: " + rutFormateado);
        }
    }


}
