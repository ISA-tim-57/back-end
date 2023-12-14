package com.medicines.distribution.controller;

import com.medicines.distribution.dto.AuthenticationRequest;
import com.medicines.distribution.dto.AuthenticationResponse;
import com.medicines.distribution.dto.UserDTO;
import com.medicines.distribution.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping(value ="/register", consumes = "application/json")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody UserDTO request){
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @GetMapping
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Helloooasdas");
    }
}
