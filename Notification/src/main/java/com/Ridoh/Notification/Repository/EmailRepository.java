package com.Ridoh.Notification.Repository;

import com.Ridoh.Notification.Email.dto.EmailDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<EmailDetails, String> {
}
