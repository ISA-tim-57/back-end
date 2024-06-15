package com.medicines.distribution.controller;

import com.medicines.distribution.dto.AuthenticationRequest;
import com.medicines.distribution.dto.BasicUserDTO;
import com.medicines.distribution.dto.UserDTO;
import com.medicines.distribution.dto.UserTokenState;
import com.medicines.distribution.exeption.ResourceConflictException;
import com.medicines.distribution.model.BasicUser;
import com.medicines.distribution.model.User;
import com.medicines.distribution.repository.BasicUserRepository;
import com.medicines.distribution.service.AuthenticationService;
import com.medicines.distribution.service.UserService;
import com.medicines.distribution.util.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final AuthenticationManager authenticationManager;
    private final TokenUtils tokenUtils;



    @Autowired
    UserService userService;

    @Autowired
    BasicUserRepository basicUserRepository;

    @PutMapping("/verification/{id}")
    public ResponseEntity<String> verifyUser(@PathVariable Integer id) {
        authenticationService.verifyUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value ="/register", consumes = "application/json")
    public ResponseEntity<BasicUserDTO> register(@RequestBody BasicUserDTO request){
        User existingUser = userService.findByEmail(request.getUser().getEmail());

        if(existingUser != null){
            throw new ResourceConflictException(request.getUser().getId(), "Username already exists");
        }

        return ResponseEntity.ok(new BasicUserDTO(authenticationService.register(request)));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<UserTokenState> authenticate(@RequestBody AuthenticationRequest request){

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()

        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = (User) authentication.getPrincipal();

        // Proveriti da li korisnik ima rolu ROLE_USER
        // Proveriti da li korisnik ima rolu ROLE_USER
        boolean isBasicUser = user.getRoles().stream()
                .anyMatch(role -> role.getName().equals("ROLE_USER"));

        if (isBasicUser) {
            BasicUser basicUser = basicUserRepository.findByUserId(user.getId());
            if (basicUser != null && !basicUser.isActive()) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }

        String jwt  = tokenUtils.generateToken(user.getUsername());
        int expiresIn = tokenUtils.getExpiredIn();
        return ResponseEntity.ok(new UserTokenState(jwt, expiresIn));
    }

    @GetMapping
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Helloooasdas");
    }
}
