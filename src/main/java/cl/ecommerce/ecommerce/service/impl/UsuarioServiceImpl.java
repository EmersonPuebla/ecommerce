package cl.ecommerce.ecommerce.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.ecommerce.ecommerce.dto.UsuarioDTO;
import cl.ecommerce.ecommerce.repository.UsuarioRepository;
import cl.ecommerce.ecommerce.service.IUsuarioService;
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

    public void delete(Integer run) {
        usuarioRepository.deleteById(run);
    }

}
