package com.Ridoh.Notification.Email.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class EmailDetails {
    @Id
    private String id;
    private String recipient;
    private String messageBody;
    private String attachment;
    private String subject;
    @CreatedDate
    private LocalDateTime created;
}
