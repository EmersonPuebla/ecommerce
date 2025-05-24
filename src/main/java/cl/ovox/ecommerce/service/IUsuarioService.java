package cl.ovox.ecommerce.service;

import java.util.List;
import java.util.UUID;

import cl.ovox.ecommerce.dto.UsuarioDTO;

public interface IUsuarioService {
    List<UsuarioDTO> findAll();
    UsuarioDTO findById(UUID id);
    UsuarioDTO save(UsuarioDTO usuario);
    UsuarioDTO update(UUID id, UsuarioDTO usuario);
    void delete(UUID id);

}
