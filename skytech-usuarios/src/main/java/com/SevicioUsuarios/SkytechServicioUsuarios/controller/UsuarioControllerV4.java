package com.SevicioUsuarios.SkytechServicioUsuarios.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.web.bind.annotation.*;
import com.SevicioUsuarios.SkytechServicioUsuarios.service.UsuarioService;
import com.SevicioUsuarios.SkytechServicioUsuarios.assembler.UsuarioModelAssembler;
import com.SevicioUsuarios.SkytechServicioUsuarios.model.Usuario;
import java.util.List;
import java.util.stream.Collectors;




@RestController
@RequestMapping("/api/v4/usuarios")
public class UsuarioControllerV4 {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioModelAssembler assembler;

//METODOS GET

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Usuario>> listarUsuarios() {
        List<EntityModel<Usuario>> usuarios = usuarioService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
            
            return CollectionModel.of(usuarios,
                linkTo(methodOn(UsuarioControllerV4.class).listarUsuarios()).withSelfRel());
            }
    
    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Usuario> buscarPorId(@PathVariable Long id) {
        Usuario usuario = usuarioService.findById(id);
        return assembler.toModel(usuario);
    }
}