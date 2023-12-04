package com.Ridoh.Notification.Email.Service;

import com.Ridoh.Notification.Email.dto.EmailDetails;

public interface EmailService {

    String sendSimpleEmail(EmailDetails emailDetails);
    String sendMailWithAttachment(EmailDetails details);
}
