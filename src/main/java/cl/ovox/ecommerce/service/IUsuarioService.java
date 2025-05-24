package cl.ovox.ecommerce.service;

import java.util.List;

import cl.ovox.ecommerce.dto.UsuarioDTO;

public interface IUsuarioService {
    List<UsuarioDTO> findAll();
    UsuarioDTO findById(Integer run);
    UsuarioDTO save(UsuarioDTO usuario);
    UsuarioDTO update(Integer run, UsuarioDTO usuario);
    void delete(Integer run);

}
