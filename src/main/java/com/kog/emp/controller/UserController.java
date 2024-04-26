package com.kog.emp.controller;

import com.kog.emp.dto.AuthRequest;
import com.kog.emp.dto.UserDto;
import com.kog.emp.entity.AuthenticationResponse;
import com.kog.emp.entity.User;

import com.kog.emp.repository.UserRepository;
import com.kog.emp.service.AuthenticationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequiredArgsConstructor
@SecurityRequirement(name = "EmployeeManagement")
@RestController
@RequestMapping("/auth")
public class UserController {


    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;


    @GetMapping("/welcome")
    public String welcome() {
        return "welcome to employee management application";
    }

  @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody UserDto request){
        return ResponseEntity.ok(authenticationService.register(request));
    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthRequest request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
    @GetMapping("/logout")
     public ResponseEntity<String> logout(){
        return ResponseEntity.ok("Logged Out Successfully");
     }

   @GetMapping("/allUsers")
    public List<User> getAllUsers(){
        return authenticationService.getAllUsers();
   }

}

