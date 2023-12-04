package com.example.Identity.Management.Service;

import com.example.Identity.Management.DTO.Data;
import com.example.Identity.Management.DTO.Response;
import com.example.Identity.Management.DTO.UserRequest;
import com.example.Identity.Management.Entity.User;
import com.example.Identity.Management.Repository.UserRepo;
import com.example.Identity.Management.Util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public List<Response> allUser() {
        List<User>usersList=userRepo.findAll();
        List<Response>response=new ArrayList<>();

        for (User user: usersList) {
            response.add(Response.builder()
                    .responseCode(ResponseUtil.USER_SUCCESS_CODE)
                    .responseMessage(ResponseUtil.USER_SUCCESS_MESSAGE)
                    .data(String.valueOf(Data.builder()
                            .accountBalance(user.getAccountBalance())
                            .accountNumber(user.getAccountNumber())
                            .accountName(user.getFirstName()+" "+user.getLastName()+" "+user.getOtherName())
                            .build()))
                    .build());

        }
        return response;
    }

    @Override
    public Response updateUser(String accountNumber, UserRequest userRequest) {
        Optional<User> optionalUser = Optional.ofNullable(userRepo.findByAccountNumber(accountNumber));

        if (optionalUser.isPresent()) {
            // User with the provided account number exists, so update the user details
            User existingUser = optionalUser.get();
            existingUser.setFirstName(userRequest.getFirstName());
            existingUser.setLastName(userRequest.getLastName());
            existingUser.setOtherName(userRequest.getOtherName());
            existingUser.setStateOfOrigin(userRequest.getStateOfOrigin());
            existingUser.setGender(userRequest.getGender());
            existingUser.setAccountBalance(userRequest.getAccountBalance());
            existingUser.setEmail(userRequest.getEmail());
            existingUser.setStatus(userRequest.getStatus());
            existingUser.setUsername(userRequest.getUsername());
            existingUser.setPhoneNumber(userRequest.getPhoneNumber());
            existingUser.setAlternativePhoneNumber(userRequest.getAlternativePhoneNumber());
            existingUser.setDateOfBirth(userRequest.getDateOfBirth());
            existingUser.setAddress(userRequest.getAddress());

            // Save the updated user back to the database
            userRepo.save(existingUser);

            return Response.builder()
                    .responseCode(ResponseUtil.USER_UPDATE_CODE)
                    .responseMessage(ResponseUtil.USER_UPDATE_MESSAGE)
                    .data(String.valueOf(Data.builder()
                            .accountBalance(existingUser.getAccountBalance())
                            .accountNumber(existingUser.getAccountNumber())
                            .accountName(existingUser.getFirstName() + " " + existingUser.getLastName()
                                    + " " + existingUser.getOtherName())
                            .build()))
                    .build();
        } else {
            // User with the provided account number does not exist
            return Response.builder()
                    .responseCode(ResponseUtil.USER_NOT_FOUND_CODE)
                    .responseMessage(ResponseUtil.ACCOUNT_NOT_FOUND_MESSAGE)
                    .data(null)
                    .build();
        }

    }

    @Override
    public ResponseEntity<Response> fetchUser(Long userId) {
        if (!userRepo.existsById(userId)){
            return new ResponseEntity<>(Response.builder()
                    .responseMessage(ResponseUtil.USERID_NOT_FOUND_MESSAGE)
                    .responseCode(ResponseUtil.USER_ID_NOT_FOUND_CODE)
                    .data(null)
                    .build(), HttpStatus.NOT_FOUND);
        }
        User user=userRepo.findById(userId).get();
        return new ResponseEntity<>(Response.builder()
                .responseCode(ResponseUtil.USER_EXIST_CODE)
                .responseMessage(ResponseUtil.USER_EXIST_MESSAGE)
                .data(String.valueOf(Data.builder()
                        .accountNumber(user.getAccountNumber())
                        .accountBalance(user.getAccountBalance())
                        .accountName(user.getFirstName()+" "+user.getLastName()+" "+user.getOtherName())
                        .build()))
                .build(),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> balanceEnquiry(String accountNumber) {
        boolean isAccountExists= userRepo.existsByAccountNumber(accountNumber);
        if (!userRepo.existsByAccountNumber(accountNumber)) {
            return new ResponseEntity<>(Response.builder()
                    .responseMessage(ResponseUtil.USERID_NOT_FOUND_MESSAGE)
                    .responseCode(ResponseUtil.USER_ID_NOT_FOUND_CODE)
                    .data(null)
                    .build(), HttpStatus.NOT_FOUND);
        }
            User user=userRepo.findByAccountNumber(accountNumber);
                return new ResponseEntity<>(Response.builder()
                        .responseCode(ResponseUtil.USER_EXIST_CODE)
                        .responseMessage(ResponseUtil.USER_EXIST_MESSAGE)
                        .data(String.valueOf(Data.builder()
                                .accountBalance(user.getAccountBalance())
                                .accountNumber(user.getAccountNumber())
                                .accountName(user.getFirstName()+" "+user.getLastName()+" "+user.getOtherName())
                                .build()))
                        .build(),HttpStatus.FOUND);
            }

    @Override
    public ResponseEntity<Response> nameEnquiry(String accountNumber) {
        boolean isAccountExist= userRepo.existsByAccountNumber(accountNumber);
        if (!isAccountExist) {
            return new ResponseEntity<>(Response.builder()
                    .responseMessage(ResponseUtil.USERID_NOT_FOUND_MESSAGE)
                    .responseCode(ResponseUtil.USER_ID_NOT_FOUND_CODE)
                    .data(null)
                    .build(), HttpStatus.NOT_FOUND);
        }

            User user=userRepo.findByAccountNumber(accountNumber);
            return new ResponseEntity<>(Response.builder()
                    .responseCode(ResponseUtil.USER_EXIST_CODE)
                    .responseMessage(ResponseUtil.USER_EXIST_MESSAGE)
                    .data(String.valueOf(Data.builder()
                            .accountNumber(user.getAccountNumber())
                            .accountBalance(user.getAccountBalance())
                            .accountName(user.getFirstName()+" "+user.getLastName()+" "+user.getOtherName())
                            .build()))
                    .build(),HttpStatus.FOUND);
    }
}



