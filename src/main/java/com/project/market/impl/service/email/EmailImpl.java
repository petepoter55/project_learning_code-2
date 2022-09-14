package com.project.market.impl.service.email;

import com.project.market.dto.req.email.EmailDtoRequest;
import com.project.market.impl.exception.ResponseException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailImpl {
    private static final Logger logger = Logger.getLogger(EmailImpl.class);
    @Autowired
    private JavaMailSender javaMailSender;


    @Value("${spring.mail.username}")
    private String sender;

    public ResponseEntity<String> sendEmail(EmailDtoRequest emailDtoRequest) {
        try {
            MimeMessage emailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mailBuilder = new MimeMessageHelper(emailMessage, true);
            mailBuilder.setTo(emailDtoRequest.getMailTo());
            mailBuilder.setFrom(emailDtoRequest.getMailFrom());
            mailBuilder.setText(emailDtoRequest.getMailContent(), true);
            mailBuilder.setSubject(emailDtoRequest.getMailSubject());
            javaMailSender.send(emailMessage);
        } catch (ResponseException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<String>("Email sent successfully", HttpStatus.OK);
    }

}
