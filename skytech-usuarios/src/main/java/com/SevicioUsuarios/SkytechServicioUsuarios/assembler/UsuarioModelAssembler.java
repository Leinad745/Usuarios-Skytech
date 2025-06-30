package com.SevicioUsuarios.SkytechServicioUsuarios.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.SevicioUsuarios.SkytechServicioUsuarios.controller.UsuarioControllerV4;
import com.SevicioUsuarios.SkytechServicioUsuarios.model.Usuario;

@Component
public class UsuarioModelAssembler implements RepresentationModelAssembler<Usuario, EntityModel<Usuario>> {

    @Override
    public EntityModel<Usuario> toModel(Usuario usuario) {
        return EntityModel.of(usuario,
                linkTo(methodOn(UsuarioControllerV4.class).buscarPorId(usuario.getIdUsuario())).withSelfRel(),
                linkTo(methodOn((UsuarioControllerV4.class)).listarUsuarios()).withRel("juegos"));
    }

}
