package com.SevicioUsuarios.SkytechServicioUsuarios.service;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import com.SevicioUsuarios.SkytechServicioUsuarios.repository.UsuarioRepository;
import com.SevicioUsuarios.SkytechServicioUsuarios.model.Rol;
import com.SevicioUsuarios.SkytechServicioUsuarios.model.Suscripcion;
import com.SevicioUsuarios.SkytechServicioUsuarios.model.Usuario;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.Optional;
import java.sql.Time;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    private Rol rol = new Rol(1L, "USUARIO", "Rol de usuario estándar");
        
    private Suscripcion suscripcion = new Suscripcion(1L, "BASICA", Time.valueOf("02:00:00"), 10.99f);

    private Usuario usuario = new Usuario(1L, "jdoe", "John", "Doe", "Alberto", "De la sierra",
         "jdoe@example.com", "ContraseñaSegura123", rol, suscripcion);
    
    
    @Test
    public void testFindAll() {
         when(usuarioRepository.findAll()).thenReturn(List.of(usuario));
        
         List<Usuario> usuarios = usuarioService.findAll();
            assertNotNull(usuarios);
            assertEquals(1, usuarios.size());

        }
    
    @Test
    public void testFindById() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
    
        Usuario found = usuarioService.findById(1L);

        assertNotNull(found);
        assertEquals(1L, found.getIdUsuario());
    }

    @Test
    public void testFindByNombreUsuario() {
            when(usuarioRepository.findByNombreUsuario(usuario.getNombreUsuario())).thenReturn(usuario);
            Usuario found = usuarioService.findByNombreUsuario(usuario.getNombreUsuario());
            assertNotNull(found);
            assertEquals(usuario.getNombreUsuario(), found.getNombreUsuario());
    }

    @Test
    public void testSave() {
        when(usuarioRepository.save(usuario)).thenReturn(usuario);
        Usuario saved = usuarioService.save(usuario);
        assertNotNull(saved);
        assertEquals(usuario.getIdUsuario(), saved.getIdUsuario());
    }

    @Test
    public void testDelete() {
        doNothing().when(usuarioRepository).deleteById(1L);
        usuarioService.delete(1L);
        verify(usuarioRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testInicioSesion() {
        when(usuarioRepository.inicioSesion(usuario.getNombreUsuario(), usuario.getContrasena())).thenReturn(usuario);

        Usuario loggedInUser = usuarioService.inicioSesion(usuario.getNombreUsuario(), usuario.getContrasena());
        assertNotNull(loggedInUser);
        assertEquals(usuario.getNombreUsuario(), loggedInUser.getNombreUsuario());
        assertEquals(usuario.getContrasena(), loggedInUser.getContrasena());
    }
}