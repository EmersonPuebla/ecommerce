package cl.ecommerce.ecommerce.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.ecommerce.ecommerce.repository.UsuarioRepository;
import cl.ecommerce.ecommerce.service.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService{
    @Autowired 
    UsuarioRepository repoUsuario;


}
