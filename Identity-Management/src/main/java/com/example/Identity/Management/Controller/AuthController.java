package com.example.Identity.Management.Controller;

import com.example.Identity.Management.DTO.AdminDto;
import com.example.Identity.Management.DTO.LoginDto;
import com.example.Identity.Management.DTO.RegisterDto;
import com.example.Identity.Management.Service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@AllArgsConstructor
public class AuthController {
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto){
        return new ResponseEntity<>(authService.register(registerDto), HttpStatus.CREATED);
    }
    @PostMapping("/registerAdmin")
    public ResponseEntity<?> register(@RequestBody AdminDto adminDto){
        return new ResponseEntity<>(authService.registerAdmin(adminDto), HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto){
        return new ResponseEntity<>(authService.login(loginDto), HttpStatus.OK);
    }
}
