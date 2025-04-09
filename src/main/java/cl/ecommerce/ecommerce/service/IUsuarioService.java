package cl.ecommerce.ecommerce.service;

import java.util.List;

import cl.ecommerce.ecommerce.dto.UsuarioDTO;

public interface IUsuarioService {
    List<UsuarioDTO> findAll();
    UsuarioDTO findById(Integer run);
    UsuarioDTO save(UsuarioDTO usuario);
    void delete(Integer run);

}
