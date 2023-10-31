package com.picpaysimplificado.domain.Usuario;

import com.picpaysimplificado.dtos.UsuarioDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "usuario")
@Table(name = "usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "Id")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String primeiroNome;

    private String ultimoNome;

    @Column(unique = true)
    private String cpf;

    @Column(unique = true)
    private String email;

    private String senha;

    private BigDecimal saldoUsuario;

    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;

    public Usuario(UsuarioDTO data){
        this.primeiroNome = data.primeiroNome();
        this.ultimoNome = data.ultimoNome();
        this.cpf = data.cpf();
        this.saldoUsuario = data.saldoUsuario();
        this.tipoUsuario = data.tipoUsuario();
        this.senha = data.senha();
        this.email = data.email();
    }

}
