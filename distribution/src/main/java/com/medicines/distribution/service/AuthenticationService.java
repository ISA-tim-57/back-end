package com.medicines.distribution.service;

import com.medicines.distribution.dto.AuthenticationRequest;
import com.medicines.distribution.dto.AuthenticationResponse;
import com.medicines.distribution.dto.UserDTO;
import com.medicines.distribution.model.Address;
import com.medicines.distribution.model.User;
import com.medicines.distribution.repository.UserRepository;
import com.medicines.distribution.util.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenUtils tokenUtils;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(UserDTO request) {
        User user = new User(
                request.getId(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                request.getUsername(),
                request.getName(),
                request.getSurname(),
                request.convertToAddress(request.getAddress()),
                request.getPhone(),
                request.getProfession(),
                request.getRole(),
                request.getCompanyInfo());

        User savedUser = repository.save(user);
        var jwtToken = tokenUtils.generateToken(user);
        //var refreshToken = tokenUtils.generateRefreshToken(user);
        //saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword())
        );

        var user = repository.findUserByUsername(request.getUsername()).orElseThrow();
        var jwtToken = tokenUtils.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
