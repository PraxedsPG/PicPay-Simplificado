package com.picpaysimplificado.dtos;

import java.math.BigDecimal;

public record TransacaoDTO(BigDecimal valor, Long senderId, Long receiverId) {
}
