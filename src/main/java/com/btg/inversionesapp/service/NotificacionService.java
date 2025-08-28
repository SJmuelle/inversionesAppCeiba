package com.btg.inversionesapp.service;

import com.btg.inversionesapp.model.Cliente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificacionService {

    private static final Logger log = LoggerFactory.getLogger(NotificacionService.class);
    private final JavaMailSender mailSender;

    public NotificacionService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Value("${app.notifications.email.from:no-reply@btg.com}")
    private String fromEmail;

    public void enviar(Cliente cliente, String asunto, String mensaje, String tipo) {
        if ("EMAIL".equalsIgnoreCase(tipo)) {
            try {
                SimpleMailMessage mail = new SimpleMailMessage();
                mail.setFrom(fromEmail);
                mail.setTo(cliente.getEmail());
                mail.setSubject(asunto);
                mail.setText(mensaje);
                mailSender.send(mail);
                log.info("Email enviado a {}: {}", cliente.getEmail(), asunto);
            } catch (Exception e) {
                log.warn("No se pudo enviar email. Mensaje sería para {}: {} - {}",
                        cliente.getEmail(), asunto, mensaje);
            }
        } else if ("SMS".equalsIgnoreCase(tipo)) {
            log.info("SMS (simulado) a {}: {} - {}", cliente.getTelefono(), asunto, mensaje);
        } else {
            log.warn("Tipo de notificación no soportado: {}", tipo);
        }
    }
}
