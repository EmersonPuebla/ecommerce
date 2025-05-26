package cl.ovox.ecommerce.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.ovox.ecommerce.dto.ProductoEstadoDTO;
import cl.ovox.ecommerce.dto.UsuarioDTO;
import cl.ovox.ecommerce.repository.UsuarioRepository;
import cl.ovox.ecommerce.service.IUsuarioService;
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
        return usuarioRepository.save(usuario);
    }

    public UsuarioDTO update(Integer rut, UsuarioDTO usuario){
        if (usuarioRepository.findByRut(rut) != null){
            return usuarioRepository.save(usuario);
        }
        return null;
        
    }

    public UsuarioDTO findByRut(Integer rut) {
        return usuarioRepository.findByRut(rut).orElse(null);
    }

    public void delete(Integer rut) {
        usuarioRepository.deleteByRut(rut);
    }

}
