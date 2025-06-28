package com.SevicioUsuarios.SkytechServicioUsuarios.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.SevicioUsuarios.SkytechServicioUsuarios.model.Usuario;
import com.SevicioUsuarios.SkytechServicioUsuarios.model.Rol;
import com.SevicioUsuarios.SkytechServicioUsuarios.model.Suscripcion;
import com.SevicioUsuarios.SkytechServicioUsuarios.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Time;
import java.util.List;

@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @Autowired
    private ObjectMapper objectMapper;

    private Rol rol;
    private Suscripcion suscripcion;
    private Usuario usuario;

    @BeforeEach
    void setUp() {
        rol = new Rol(1L, "USUARIO", "Rol de usuario estándar");
        
        suscripcion = new Suscripcion(1L, "BASICA", Time.valueOf("02:00:00"), 10.99f);
        
        usuario = new Usuario(1L, "jdoe", "John", "Doe", "Alberto", "De la sierra", "jdoe@example.com", "ContraseñaSegura123", rol, suscripcion);
    }

    @Test
    public void testGetAllUsuarios() throws Exception {
        when(usuarioService.findAll()).thenReturn(List.of(usuario));
        mockMvc.perform(get("/api/v3/usuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idUsuario").value(1))
                .andExpect(jsonPath("$[0].nombreUsuario").value("jdoe"))
                .andExpect(jsonPath("$[0].primerNombre").value("John"))
                .andExpect(jsonPath("$[0].apellidoPaterno").value("Doe"))
                .andExpect(jsonPath("$[0].segundoNombre").value("Alberto"))
                .andExpect(jsonPath("$[0].apellidoMaterno").value("De la sierra"))
                .andExpect(jsonPath("$[0].correoElectronico").value("jdoe@example.com"))
                .andExpect(jsonPath("$[0].rol.idRol").value(1))
                .andExpect(jsonPath("$[0].suscripcion.idSuscripcion").value(1));

    }

    @Test
    public void testBuscarPorID() throws Exception {
        when(usuarioService.findById(1L)).thenReturn(usuario);
        mockMvc.perform(get("/api/v3/usuarios/1"))
                                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idUsuario").value(1))
                .andExpect(jsonPath("$.nombreUsuario").value("jdoe"))
                .andExpect(jsonPath("$.primerNombre").value("John"))
                .andExpect(jsonPath("$.apellidoPaterno").value("Doe"))
                .andExpect(jsonPath("$.segundoNombre").value("Alberto"))
                .andExpect(jsonPath("$.apellidoMaterno").value("De la sierra"))
                .andExpect(jsonPath("$.correoElectronico").value("jdoe@example.com"))
                .andExpect(jsonPath("$.rol.idRol").value(1))
                .andExpect(jsonPath("$.suscripcion.idSuscripcion").value(1));
    }

    @Test
    public void testBuscarPorNombreDeUsuario() throws Exception {
        when(usuarioService.findByNombreUsuario("jdoe")).thenReturn(usuario);
        mockMvc.perform(get("/api/v3/usuarios/username/jdoe"))
            .andExpect(status().isOk())    
            .andExpect(jsonPath("$.idUsuario").value(1))
            .andExpect(jsonPath("$.nombreUsuario").value("jdoe"))
            .andExpect(jsonPath("$.primerNombre").value("John"))
            .andExpect(jsonPath("$.apellidoPaterno").value("Doe"))
            .andExpect(jsonPath("$.segundoNombre").value("Alberto"))
            .andExpect(jsonPath("$.apellidoMaterno").value("De la sierra"))
            .andExpect(jsonPath("$.correoElectronico").value("jdoe@example.com"))
                .andExpect(jsonPath("$.rol.idRol").value(1))
                .andExpect(jsonPath("$.suscripcion.idSuscripcion").value(1));
    }

    @Test
    public void testDeleteUsuario() throws Exception {
        doNothing().when(usuarioService).delete(1L);
        mockMvc.perform(delete("/api/v3/usuarios/1"))
                .andExpect(status().isNoContent());
        verify(usuarioService, times(1)).delete(1L);
    }

    @Test
    public void testGuardarUsuario() throws Exception {
        when(usuarioService.save(any(Usuario.class))).thenReturn(usuario);
        
        mockMvc.perform(post("/api/v3/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idUsuario").value(1))
                .andExpect(jsonPath("$.nombreUsuario").value("jdoe"))
                .andExpect(jsonPath("$.primerNombre").value("John"))
                .andExpect(jsonPath("$.apellidoPaterno").value("Doe"))
                .andExpect(jsonPath("$.segundoNombre").value("Alberto"))
                .andExpect(jsonPath("$.apellidoMaterno").value("De la sierra"))
                .andExpect(jsonPath("$.correoElectronico").value("jdoe@example.com"))
                .andExpect(jsonPath("$.rol.idRol").value(1L))
                .andExpect(jsonPath("$.suscripcion.idSuscripcion").value(1L));
    }

    @Test
    public void testActualizarNombreDeUsuario() throws Exception {

        Usuario usuarioActNom = new Usuario();
        usuarioActNom.setNombreUsuario("doeJ");

        Usuario usuarioActualizado = new Usuario(1L, 
        "doeJ", "John", "Doe", "Alberto", "De la sierra", 
        "jdoe@example.com", "ContraseñaSegura123", rol, suscripcion);

        when(usuarioService.findById(1L)).thenReturn(usuario);
        when(usuarioService.save(any(Usuario.class))).thenReturn(usuarioActualizado);

        mockMvc.perform(patch("/api/v3/usuarios/1//username")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idUsuario").value(1))
                .andExpect(jsonPath("$.nombreUsuario").value("doeJ"))
                .andExpect(jsonPath("$.primerNombre").value("John"))
                .andExpect(jsonPath("$.apellidoPaterno").value("Doe"))
                .andExpect(jsonPath("$.segundoNombre").value("Alberto"))
                .andExpect(jsonPath("$.apellidoMaterno").value("De la sierra"))
                .andExpect(jsonPath("$.correoElectronico").value("jdoe@example.com"))
                .andExpect(jsonPath("$.rol.idRol").value(1L))
                .andExpect(jsonPath("$.suscripcion.idSuscripcion").value(1L));
    
                    verify(usuarioService, times(1)).findById(1L);
                    verify(usuarioService, times(1)).save(any(Usuario.class));
    }

    @Test
    public void testActualizarContraseña() throws Exception {
        
        Usuario usuarioActContrasena = new Usuario();
        
        usuarioActContrasena.setContrasena("NuevaContraseñaSegura456");
        
        Usuario usuarioActualizado = new Usuario(1L, 
        "jdoe", "John", "Doe", "Alberto", "De la sierra", 
        "jdoe@example.com", "NuevaContraseñaSegura456", rol, suscripcion);

        when(usuarioService.findById(1L)).thenReturn(usuario);
        when(usuarioService.save(any(Usuario.class))).thenReturn(usuarioActualizado);

                mockMvc.perform(patch("/api/v3/usuarios/1/contrasena")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idUsuario").value(1))
                .andExpect(jsonPath("$.nombreUsuario").value("jdoe"))
                .andExpect(jsonPath("$.primerNombre").value("John"))
                .andExpect(jsonPath("$.apellidoPaterno").value("Doe"))
                .andExpect(jsonPath("$.segundoNombre").value("Alberto"))
                .andExpect(jsonPath("$.apellidoMaterno").value("De la sierra"))
                .andExpect(jsonPath("$.correoElectronico").value("jdoe@example.com"))
                .andExpect(jsonPath("$.rol.idRol").value(rol.getIdRol()))
                .andExpect(jsonPath("$.suscripcion.idSuscripcion").value(suscripcion.getIdSuscripcion()));
    
                    verify(usuarioService, times(1)).findById(1L);
                    verify(usuarioService, times(1)).save(any(Usuario.class));
    
            }
}