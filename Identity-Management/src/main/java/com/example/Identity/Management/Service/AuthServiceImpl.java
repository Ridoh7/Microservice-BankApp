package com.example.Identity.Management.Service;

import com.example.Identity.Management.DTO.*;
import com.example.Identity.Management.Entity.Role;
import com.example.Identity.Management.Entity.User;
import com.example.Identity.Management.Repository.RoleRepository;
import com.example.Identity.Management.Repository.UserRepo;
import com.example.Identity.Management.Security.JwtTokenProvider;
import com.example.Identity.Management.Util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepo userRepo;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(UserRepo userRepo, RoleRepository roleRepository, AuthenticationManager authenticationManager,
                           JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.roleRepository = roleRepository;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AuthResponse login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return AuthResponse.builder()
                .token(jwtTokenProvider.generateToken(authentication))
                .build();

    }

    @Override
    public Response register(RegisterDto registerDto) {
//
//        if (userRepo.existsByEmailOrUsername(registerDto.getUsername(), registerDto.getEmail())) {
//            return "Username or Email is already taken";
//        }
        boolean isUserExist=userRepo.existsByEmailOrUsername(registerDto.getEmail(), registerDto.getUsername());
        if (isUserExist) {
            return Response.builder()
                    .responseCode(ResponseUtil.USER_EXIST_CODE)
                    .responseMessage(ResponseUtil.USER_EXIST_MESSAGE)
                    .data(null)
                    .build();

        }

        Role role = roleRepository.findByRoleName("USER").orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        User user = User.builder()
                .firstName(registerDto.getFirstName())
                .lastName(registerDto.getLastName())
                .otherName(registerDto.getOtherName())
                .username(registerDto.getUsername())
                .email(registerDto.getEmail())
                .accountNumber(ResponseUtil.generateAccountNumber(ResponseUtil.LENGTH_OF_ACCOUNT_NUMBER))
                .password(passwordEncoder.encode(registerDto.getPassword()))
                .roles(Collections.singleton(role))
                .build();

        User savedUser=userRepo.save(user);

        return Response.builder()
                .responseCode(ResponseUtil.USER_SUCCESS_CODE)
                .responseMessage(ResponseUtil.USER_SUCCESS_MESSAGE)
                .data(String.valueOf(Data.builder()
                        .accountBalance(savedUser.getAccountBalance())
                        .accountNumber(savedUser.getAccountNumber())
                        .accountName(savedUser.getFirstName()+" "+ savedUser.getLastName()+" "
                                + savedUser.getOtherName())
                        .build()))
                .build();
    }
    @Override
    public Response registerAdmin(AdminDto adminDto) {
        boolean isUserExist=userRepo.existsByEmailOrUsername(adminDto.getEmail(), adminDto.getUsername());
        if (isUserExist) {
            return Response.builder()
                    .responseCode(ResponseUtil.USER_EXIST_CODE)
                    .responseMessage(ResponseUtil.USER_EXIST_MESSAGE)
                    .data(null)
                    .build();

        }

        Role role = roleRepository.findByRoleName("ADMIN").orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        User user = User.builder()
                .firstName(adminDto.getFirstName())
                .lastName(adminDto.getLastName())
                .otherName(adminDto.getOtherName())
                .username(adminDto.getUsername())
                .email(adminDto.getEmail())
                .accountNumber(ResponseUtil.generateAccountNumber(ResponseUtil.LENGTH_OF_ACCOUNT_NUMBER))
                .password(passwordEncoder.encode(adminDto.getPassword()))
                .roles(Collections.singleton(role))
                .build();

        User savedAdmin=userRepo.save(user);

        return Response.builder()
                .responseCode(ResponseUtil.USER_SUCCESS_CODE)
                .responseMessage(ResponseUtil.USER_SUCCESS_MESSAGE)
                .data(String.valueOf(Data.builder()
                        .accountBalance(savedAdmin.getAccountBalance())
                        .accountNumber(savedAdmin.getAccountNumber())
                        .accountName(savedAdmin.getFirstName()+" "+ savedAdmin.getLastName()+" "
                                + savedAdmin.getOtherName())
                        .build()))
                .build();
    }
}
