package com.picpaysimplificado.services;

import com.picpaysimplificado.domain.Usuario.Usuario;
import com.picpaysimplificado.dtos.NotificacaoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ServicoNotificacao {

    private RestTemplate restTemplate;

    public void enviarNotificacao(Usuario usuario, String mensagem) throws Exception {
        String email = usuario.getEmail();
        NotificacaoDTO notificationRequest = new NotificacaoDTO(email,mensagem);

        ResponseEntity<String> notificationResponse = restTemplate.postForEntity("http://o4d9z.mocklab.io/notify", notificationRequest, String.class);

        if(!(notificationResponse.getStatusCode() == HttpStatus.OK));
        System.out.println("erro ao enviar notificação");
        throw new Exception("Serviço de notificação está fora do ar");
    }
}
