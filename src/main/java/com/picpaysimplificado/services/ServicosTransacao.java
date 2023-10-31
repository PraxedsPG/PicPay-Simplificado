package com.picpaysimplificado.services;

import com.picpaysimplificado.domain.Transacoes.Transacoes;
import com.picpaysimplificado.domain.Usuario.Usuario;
import com.picpaysimplificado.dtos.TransacaoDTO;
import com.picpaysimplificado.repositories.TransacoesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class ServicosTransacao {

    @Autowired
    private ServicosUsuario servicoUsuario;

    @Autowired
    private TransacoesRepository repository;

    @Autowired
    private ServicoNotificacao servicoNotificacao;

    private RestTemplate restTemplate;

    public Transacoes criarTransacao(TransacaoDTO transacao) throws Exception {
        Usuario sender = this.servicoUsuario.findUserById(transacao.senderId());
        Usuario receiver = this.servicoUsuario.findUserById(transacao.receiverId());

        servicoUsuario.validarTransacao(sender, transacao.valor());

        boolean autorizado = this.autorizarTransacao(sender, transacao.valor());

        if(!autorizado){
            throw new Exception("Transação não autorizada");
        }
        Transacoes novatransacao = new Transacoes();
        novatransacao.setValorTransacao(transacao.valor());
        novatransacao.setSender(sender);
        novatransacao.setReceiver(receiver);
        novatransacao.setHorarioTransacao(LocalDateTime.now());

        sender.setSaldoUsuario(sender.getSaldoUsuario().subtract(transacao.valor()));
        receiver.setSaldoUsuario(receiver.getSaldoUsuario().add(transacao.valor()));

        this.repository.save(novatransacao);
        this.servicoUsuario.saveUser(sender);
        this.servicoUsuario.saveUser(receiver);

        this.servicoNotificacao.enviarNotificacao(sender,"Transação realizada com sucesso!");
        this.servicoNotificacao.enviarNotificacao(receiver,"Transaçãp recebida com sucesso!");

        return novatransacao;
    }

    public boolean autorizarTransacao(Usuario sender, BigDecimal valor){
      ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity("https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6", Map.class);

      if(authorizationResponse.getStatusCode() == HttpStatus.OK){
          String message = (String) authorizationResponse.getBody().get("message");
          return "Autorizado".equalsIgnoreCase(message);
      } else return false;
    }
}
