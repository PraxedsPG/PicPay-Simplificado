package com.picpaysimplificado.domain.Usuario;

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

}
