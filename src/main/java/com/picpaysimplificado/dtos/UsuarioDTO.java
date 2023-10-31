package com.picpaysimplificado.dtos;

import com.picpaysimplificado.domain.Usuario.TipoUsuario;

import java.math.BigDecimal;

public record UsuarioDTO(
        String primeiroNome,
        String ultimoNome,
        String cpf,
        BigDecimal saldoUsuario,
        String email,
        String senha,

        TipoUsuario tipoUsuario
    ) {
}
