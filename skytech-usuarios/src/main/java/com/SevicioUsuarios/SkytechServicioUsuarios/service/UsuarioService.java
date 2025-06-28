package com.SevicioUsuarios.SkytechServicioUsuarios.service;

import com.SevicioUsuarios.SkytechServicioUsuarios.repository.UsuarioRepository;
import com.SevicioUsuarios.SkytechServicioUsuarios.model.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).get();
    }
    
    public Usuario findByNombreUsuario(String nombreUsuario) {
        return usuarioRepository.findByNombreUsuario(nombreUsuario);
    }

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario findByCorreo(String correoElectronico) {
        return usuarioRepository.findByCorreoElectronico(correoElectronico);
    }

    public Usuario inicioSesion(String nombreUsuario, String contrasena) {
        return usuarioRepository.inicioSesion(nombreUsuario, contrasena);
    }

    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario obtenerSuscripcionDeluUsuario(Long idUsuario) {
        return usuarioRepository.obtenerSuscripcionDeluUsuario(idUsuario);
    }

    
}
