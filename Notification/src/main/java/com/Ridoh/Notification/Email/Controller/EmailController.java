package com.Ridoh.Notification.Email.Controller;

import com.Ridoh.Notification.Email.Service.EmailService;
import com.Ridoh.Notification.Email.dto.EmailDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
@Service
//@Tag(
//        name = "Email Service REST APIs/Endpoint",
//        description = "Endpoints for Manipulating Email Services"
//)

public class EmailController {

    @Autowired
    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/sendMail")
    public String sendSimpleEmail(@RequestBody EmailDetails details) {
        return emailService.sendSimpleEmail(details);
    }

    @PostMapping("/sendMailWithAttachment")
    public String sendMailWithAttachment(@RequestBody EmailDetails details) {
        return emailService.sendMailWithAttachment(details);
    }

}
