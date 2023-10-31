package com.picpaysimplificado.controllers;

import com.picpaysimplificado.domain.Usuario.Usuario;
import com.picpaysimplificado.dtos.UsuarioDTO;
import com.picpaysimplificado.services.ServicosUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    ServicosUsuario servicosUsuario;
    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody UsuarioDTO usuario) {
        Usuario novoUsuario = servicosUsuario.criarUsuario(usuario);
        return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsuarios(){
        List<Usuario> usuarios = this.servicosUsuario.getAllUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

}
