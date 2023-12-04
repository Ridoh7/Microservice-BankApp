package com.example.Identity.Management.Service;


import com.example.Identity.Management.DTO.Response;
import com.example.Identity.Management.DTO.UserRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
//    Response registerUser (UserRequest userRequest);
    List<Response>allUser();
    Response updateUser(String accountNumber, UserRequest userRequest);
    ResponseEntity<Response>fetchUser(Long userId);
    ResponseEntity<Response>balanceEnquiry(String accountNumber);
    ResponseEntity<Response>nameEnquiry(String accountNumber);
}
