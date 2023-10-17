package com.picpaysimplificado.domain.Transacoes;

import com.picpaysimplificado.domain.Usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "transacoes")
@Table(name =  "transacoes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "Id")
public class Transacoes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long Id;

    private BigDecimal valorTransacao;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Usuario sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private Usuario receiver;

    private LocalDateTime horarioTransacao;

}
