package com.ms.email.services;

import com.ms.email.enums.StatusEmail;
import com.ms.email.models.EmailModel;
import com.ms.email.repositories.EmailRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Log4j2
public class EmailService {

    final EmailRepository emailRepository;
    final JavaMailSender mailSender;

    public EmailService(EmailRepository emailRepository, JavaMailSender mailSender) {
        this.emailRepository = emailRepository;
        this.mailSender = mailSender;
    }
    @Value("${spring.mail.username}")
    private String emailFrom;

    @Transactional
    public void sendEmail(EmailModel emailModel){
        try {
            log.info("Tentando realizar chamar o service ");
            emailModel.setSendDateEmail(LocalDateTime.now());
            emailModel.setEmailFrom(this.emailFrom);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(emailModel.getEmailTo());
            message.setSubject(emailModel.getSubject());
            message.setText(emailModel.getText());
            mailSender.send(message);
            log.info("Email enviado com sucesso para {}", emailModel.getEmailTo());
            emailModel.setStatusEmail(StatusEmail.SENT);
        }
        catch (MailException e) {
            log.error("Ocorreu um erro durante o envio do email: {}", e.getMessage());
            emailModel.setStatusEmail(StatusEmail.ERROR);
        } finally {
            log.info("Email Salvo na TBL");
            emailRepository.save(emailModel);
        }
    }
}
