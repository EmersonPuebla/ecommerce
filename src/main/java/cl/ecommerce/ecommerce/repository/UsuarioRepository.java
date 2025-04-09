package cl.ecommerce.ecommerce.repository;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import cl.ecommerce.ecommerce.dto.UsuarioDTO;

@Repository
public class UsuarioRepository {

    //"DB"
    private ArrayList<UsuarioDTO> listUsuario = new ArrayList<>();
    public UsuarioRepository(){
        listUsuario.add(new UsuarioDTO(1, "Daron", "Vartan", "Malakian", "18/7/1975", "holamundo@gmail.com", "ejemplo_hash1"));
    }

    // Get all
    public ArrayList<UsuarioDTO> getAll() {
        return listUsuario;
    }

    // Get by Rut
    public UsuarioDTO getByRut(Long rut) {
        for (UsuarioDTO usuario : listUsuario) {
            if (usuario.getRut() == rut) {
                return usuario;
            }
        }
        return null;
    }

    // Insert User
    public boolean insertUser(UsuarioDTO usuario) {
        for (UsuarioDTO usuarioDTO : listUsuario) {
            if (usuarioDTO.getRut() == usuario.getRut()) {
                return false;
            }
        }
        listUsuario.add(usuario);
        return true;
    }

    // Update User
    public boolean updatePersona(Long rut, UsuarioDTO usuario) {
        for (UsuarioDTO usuarioDTO : listUsuario) {
            if (usuarioDTO.getRut() == rut) {

                usuarioDTO.setRut(usuario.getRut());
                usuarioDTO.setNombres(usuario.getNombres());
                usuarioDTO.setApPaterno(usuario.getApPaterno());
                usuarioDTO.setApMaterno(usuario.getApMaterno());
                usuarioDTO.setFechaNacimiento(usuario.getFechaNacimiento());
                usuarioDTO.setEmail(usuario.getEmail());
                usuarioDTO.setHashContraseña(usuario.getHashContraseña());
                return true;
            }
        }
        return false;
    }

    // Delete User
    public boolean deleteUsuario(Long rut) {
        for (UsuarioDTO usuarioDTO : listUsuario) {
            if (usuarioDTO.getRut() == rut) {
                listUsuario.remove(usuarioDTO);
                return true;
            }
        }
        return false;
    }

}
