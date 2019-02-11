package ru.project.viviv.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import ru.project.viviv.exceptions.AppException;
import ru.project.viviv.model.dto.OnRegistrationCompleteEvent;
import ru.project.viviv.model.entity.User;

import javax.mail.internet.MimeMessage;
import java.util.UUID;




@Component
public class RegistrationListener implements
        ApplicationListener<OnRegistrationCompleteEvent> {

    @Autowired
    private UserService userService;

    @Qualifier("messageSource")
    @Autowired
    private MessageSource messages;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }


    private void confirmRegistration(OnRegistrationCompleteEvent event) {

        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.createVerificationToken(user, token);

        String recipientAddress = user.getEmail();
        String subject = "Подтверждение регистрации VIVIV.RU";
        String confirmationUrl
                = event.getAppUrl() + "/regitrationConfirm.html?token=" + token;

 try {
     MimeMessage mm = mailSender.createMimeMessage();
     MimeMessageHelper helper = new MimeMessageHelper(mm, true);
     mm.setHeader("Регистрация пользователя ", user.getEmail());
     helper.setTo(recipientAddress);
     helper.setFrom("registration@viviv.ru");
     helper.setSubject(subject);
     helper.setText("Подтвердите свой email " + "http://localhost:8080" + confirmationUrl);

     mailSender.send(mm);

 }
 catch (Exception ex){
     AppException.error("Sending email error",ex);
 }


    }
}