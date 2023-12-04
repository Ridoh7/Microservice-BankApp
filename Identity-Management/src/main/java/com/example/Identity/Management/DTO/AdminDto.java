package com.example.Identity.Management.DTO;

import lombok.*;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminDto {
    private String firstName;
    private String lastName;
    private String otherName;
    private String username;
    private String email;
    private String password;
}
