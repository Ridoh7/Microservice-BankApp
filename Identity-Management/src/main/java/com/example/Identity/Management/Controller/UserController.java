package com.example.Identity.Management.Controller;

import com.example.Identity.Management.DTO.Response;
import com.example.Identity.Management.DTO.UserRequest;
import com.example.Identity.Management.Service.UserService;
import com.example.Identity.Management.Util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/{accountNumber}")
    public Response updateUser(@PathVariable String accountNumber,
                               @RequestBody UserRequest userRequest) {
        Response response = userService.updateUser(accountNumber, userRequest);

        if (response.getResponseCode().equals(ResponseUtil.USER_SUCCESS_CODE)) {
            return new ResponseEntity<>(response, HttpStatus.OK).getBody();
        } else if (response.getResponseCode().equals(ResponseUtil.USER_NOT_FOUND_CODE)) {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND).getBody();
    }
        return response;
    }


    @GetMapping("/AllUsers")
    public List<Response> allUser() {
        return userService.allUser();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Response> fetchUser(@PathVariable(name = "userId") Long userId) {
        return userService.fetchUser(userId);
    }

    @GetMapping("/balEnquiry")
    public ResponseEntity<Response> balanceEnquiry(@RequestParam(name = "accountBalance") String accountNumber) {
        return userService.balanceEnquiry(accountNumber);
    }

    @GetMapping("/nameEnquiry")
    public ResponseEntity<Response> nameEnquiry(@RequestParam(name = "accountName") String accountNumber) {
        return userService.nameEnquiry(accountNumber);
    }
}