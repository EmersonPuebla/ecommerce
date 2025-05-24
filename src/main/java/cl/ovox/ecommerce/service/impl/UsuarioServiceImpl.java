package cl.ovox.ecommerce.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public UsuarioDTO findById(Integer run) {
        return usuarioRepository.findById(run).get();
    }

    public UsuarioDTO save(UsuarioDTO usuario) {
        return usuarioRepository.save(usuario);
    }

    public UsuarioDTO update(Integer run, UsuarioDTO usuario){
        if (usuarioRepository.findById(run) != null){
            return usuarioRepository.save(usuario);
        }
        return null;
        
    }

    public void delete(Integer run) {
        usuarioRepository.deleteById(run);
    }

}
