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

    public UsuarioDTO update(String rut, UsuarioDTO usuario){
        /*if (usuarioRepository.findByRut(rut) != null){
            return usuarioRepository.save(usuario);
        }*/
        return null;
    }

    public UsuarioDTO findByRut(String rut) {        

        String rutFormateado = 
        RutUtil.rutNormalizado(RutUtil.extraerRun(rut), RutUtil.extraerDv(rut)).toUpperCase();

        return usuarioRepository.findByRut(rutFormateado).orElse(null);
    }


/* 
    public UsuarioDTO findByRut(Integer rut) {
        return usuarioRepository.findByRut(rut).orElse(null);
    }
*/
    //deleteOrDeactivateByRun
    public void delete(String rut) {
       
    }

}
