package com.medicines.distribution.controller;

import com.medicines.distribution.dto.AuthenticationRequest;
import com.medicines.distribution.dto.UserDTO;
import com.medicines.distribution.dto.UserTokenState;
import com.medicines.distribution.exeption.ResourceConflictException;
import com.medicines.distribution.model.User;
import com.medicines.distribution.service.AuthenticationService;
import com.medicines.distribution.service.UserService;
import com.medicines.distribution.util.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final AuthenticationManager authenticationManager;
    private final TokenUtils tokenUtils;

    @Autowired
    UserService userService;

    @PostMapping(value ="/register", consumes = "application/json")
    public ResponseEntity<User> register(@RequestBody UserDTO request){
        User existingUser = userService.findByUsername(request.getUsername());

        if(existingUser != null){
            throw new ResourceConflictException(request.getId(), "Username already exists");
        }

        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<UserTokenState> authenticate(@RequestBody AuthenticationRequest request){

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()

        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = (User) authentication.getPrincipal();
        String jwt  = tokenUtils.generateToken(user.getUsername());
        int expiresIn = tokenUtils.getExpiredIn();
        return ResponseEntity.ok(new UserTokenState(jwt, expiresIn));
    }

    @GetMapping
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Helloooasdas");
    }
}
