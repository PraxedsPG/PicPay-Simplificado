package com.picpaysimplificado.controllers;

import com.picpaysimplificado.domain.Transacoes.Transacoes;
import com.picpaysimplificado.dtos.TransacaoDTO;
import com.picpaysimplificado.services.ServicosTransacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    @Autowired
    private ServicosTransacao servicosTransacao;

    @PostMapping
    public ResponseEntity<Transacoes> criarTransacao(@RequestBody TransacaoDTO transacao) throws Exception{
        Transacoes novaTransacao = this.servicosTransacao.criarTransacao(transacao);
        return new ResponseEntity<>(novaTransacao, HttpStatus.OK);
    }


}
