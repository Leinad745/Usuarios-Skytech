package com.SevicioUsuarios.SkytechServicioUsuarios.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.SevicioUsuarios.SkytechServicioUsuarios.service.UsuarioService;
import com.SevicioUsuarios.SkytechServicioUsuarios.model.Usuario;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/api/v3/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

//METODOS GET

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> usuarios = usuarioService.findAll();
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
        try {
            Usuario usuario = usuarioService.findById(id);
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/username/{nombreUsuario}")
    public ResponseEntity<Usuario> buscarPorNombreDeUsuario(@PathVariable String nombreUsuario) {
        try {
            Usuario usuario = usuarioService.findByNombreUsuario(nombreUsuario);
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    

//METODOS DELETE

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long id) {
        try {
            usuarioService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

//METODOS POST

    @PostMapping
    public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = this.usuarioService.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }


//METODOS Patch

    @PatchMapping("/{id}/username")
    public ResponseEntity<Usuario> actualizarNombreDeUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        try {
            Usuario usuarioAct = this.usuarioService.findById(id);

            usuarioAct.setNombreUsuario(usuario.getNombreUsuario());
            return ResponseEntity.ok(usuarioService.save(usuarioAct));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PatchMapping("/{id}/contrasena")
    public ResponseEntity<Usuario> actualizarContrasena(@PathVariable Long id, @RequestBody Usuario usuario) {
        try {
            Usuario usuarioAct = this.usuarioService.findById(id);

            usuarioAct.setContrasena(usuario.getContrasena());
            return ResponseEntity.ok(usuarioService.save(usuarioAct));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/role")
    public ResponseEntity<Usuario> actualizarRol(@PathVariable Long id, @RequestBody Usuario usuario) {
        try {
            Usuario usuarioAct = this.usuarioService.findById(id);

            usuarioAct.setRol(usuario.getRol());
            return ResponseEntity.ok(usuarioService.save(usuarioAct));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/subs")
    public ResponseEntity<Usuario> actualizarSuscripcion(@PathVariable Long id, @RequestBody Usuario usuario) {
        try {
            Usuario usuarioAct = this.usuarioService.findById(id);

            usuarioAct.setSuscripcion(usuario.getSuscripcion());
            return ResponseEntity.ok(usuarioService.save(usuarioAct));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    //METODOS PUT

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        try {
            Usuario usuarioAct = this.usuarioService.findById(id);

            usuarioAct.setNombreUsuario(usuario.getNombreUsuario());
            usuarioAct.setPrimerNombre(usuario.getPrimerNombre());
            usuarioAct.setApellidoPaterno(usuario.getApellidoPaterno());
            usuarioAct.setSegundoNombre(usuario.getSegundoNombre());
            usuarioAct.setApellidoMaterno(usuario.getApellidoMaterno());
            usuarioAct.setCorreoElectronico(usuario.getCorreoElectronico());
            usuarioAct.setContrasena(usuario.getContrasena());
            usuarioAct.setRol(usuario.getRol());
            usuarioAct.setSuscripcion(usuario.getSuscripcion());
            return ResponseEntity.ok(usuarioService.save(usuarioAct));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}