package com.picpaysimplificado.services;

import com.picpaysimplificado.domain.Usuario.TipoUsuario;
import com.picpaysimplificado.domain.Usuario.Usuario;
import com.picpaysimplificado.dtos.UsuarioDTO;
import com.picpaysimplificado.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ServicosUsuario {

    @Autowired
    private UsuarioRepository repository;

    public void validarTransacao(Usuario sender, BigDecimal valorTransacao) throws Exception{
        if(sender.getTipoUsuario() == TipoUsuario.Lojistas) {
            throw new Exception("Usuário do  tipo lojista não está autorizado a realizar uma  transação");
        }

        if(sender.getSaldoUsuario().compareTo(valorTransacao) < 0) {
            throw new Exception("Saldo insuficiente");
        }
    }

    public Usuario findUserById(Long Id) throws Exception{
        return this.repository.findUserById(Id).orElseThrow(() -> new Exception("Usuário não encontrado"));
    }

    public Usuario criarUsuario(UsuarioDTO data) {
        Usuario novoUsuario = new Usuario(data);
        this.saveUser(novoUsuario);
        return novoUsuario;
    }

    public List<Usuario> getAllUsuarios() {
        return this.repository.findAll();
    }

    public void saveUser(Usuario usuario) {
        this.repository.save(usuario);
    }

}
