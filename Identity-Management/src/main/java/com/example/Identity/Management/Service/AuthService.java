package com.example.Identity.Management.Service;

import com.example.Identity.Management.DTO.*;

public interface AuthService {
    AuthResponse login (LoginDto loginDto);

    Response register (RegisterDto registerDto);

    Response registerAdmin(AdminDto adminDto);
}
