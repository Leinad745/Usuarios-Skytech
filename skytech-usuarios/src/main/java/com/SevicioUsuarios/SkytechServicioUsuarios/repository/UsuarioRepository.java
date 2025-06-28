package com.SevicioUsuarios.SkytechServicioUsuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.SevicioUsuarios.SkytechServicioUsuarios.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public Usuario findByIdUsuario(Long idUsuario);
    public Usuario findByNombreUsuario(String nombreUsuario);
    public Usuario findByCorreoElectronico(String correoElectronico);

    @Query(value = "SELECT nombreUsuario FROM usuario WHERE nombreUsuario = ?1 AND contrasena = ?2", nativeQuery = true)
    public Usuario inicioSesion(String nombreUsuario, String contrasena);

    @Query(value="SELECT nombreUsuario, suscripcion FROM usuario WHERE idUsuario = ?1", nativeQuery = true)
    public Usuario obtenerSuscripcionDeluUsuario(Long idUsuario);

    
}
