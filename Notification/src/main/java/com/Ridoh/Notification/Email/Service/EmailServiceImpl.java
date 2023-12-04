package com.Ridoh.Notification.Email.Service;

import com.Ridoh.Notification.Email.dto.EmailDetails;
import com.Ridoh.Notification.Repository.EmailRepository;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService{

    @Autowired
    private final JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String sender;

//    @Autowired
//    public EmailServiceImpl(EmailService emailService) {
//
//    }

    @Override
    public String sendSimpleEmail(EmailDetails emailDetails) {
        try {
            SimpleMailMessage mailMessage= new SimpleMailMessage();

            mailMessage.setFrom(sender);
            mailMessage.setTo(emailDetails.getRecipient());
            mailMessage.setText(emailDetails.getMessageBody());
            mailMessage.setSubject(emailDetails.getSubject());

            javaMailSender.send(mailMessage);
            return "Mail sent successfully";

        }
        catch (MailException e){
            throw new RuntimeException();
        }
    }
    @Override
    public String sendMailWithAttachment(EmailDetails emailDetails) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(emailDetails.getRecipient());
            mimeMessageHelper.setText(emailDetails.getMessageBody());
            mimeMessageHelper.setSubject(emailDetails.getSubject());

            FileSystemResource file = new FileSystemResource(new File(emailDetails.getAttachment()));
            mimeMessageHelper.addAttachment(Objects.requireNonNull(file.getFilename()), file);

            javaMailSender.send(mimeMessage);
            return "Mail sent Successfully";
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
