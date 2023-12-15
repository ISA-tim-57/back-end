package com.medicines.distribution.service;

import com.medicines.distribution.dto.UserDTO;
import com.medicines.distribution.model.Address;
import com.medicines.distribution.model.Role;
import com.medicines.distribution.model.User;
import com.medicines.distribution.repository.UserRepository;
import com.medicines.distribution.util.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final AddressService addressService;
    private final PasswordEncoder passwordEncoder;
    private final TokenUtils tokenUtils;
    private final AuthenticationManager authenticationManager;

    @Autowired
    private RoleService roleService;

    public User register(UserDTO request) {


        Address address = new Address();
        address = addressService.save(request.convertToAddress(request.getAddress()));

        List<Role> roles = roleService.findByName("ROLE_USER");

        User u = new User();

        u.setUsername(request.getUsername());
        u.setPassword(passwordEncoder.encode(request.getPassword()));
        u.setEmail(request.getEmail());
        u.setName(request.getName());
        u.setSurname(request.getSurname());
        u.setAddress(address);
        u.setPhone(request.getPhone());
        u.setProfession(request.getProfession());
        u.setCompanyInfo(request.getCompanyInfo());
        u.setRoles(roles);

        User savedUser = repository.save(u);
//        var jwtToken = tokenUtils.generateToken(user);
        //var refreshToken = tokenUtils.generateRefreshToken(user);
        //saveUserToken(savedUser, jwtToken);
//        return AuthenticationResponse.builder()
//                .token(jwtToken)
//                .build();
        return savedUser;
    }

//    public AuthenticationResponse authenticate(AuthenticationRequest request){
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword())
//        );
//
//        var user = repository.findUserByUsername(request.getUsername());
//        var jwtToken = tokenUtils.generateToken(user);
//        return AuthenticationResponse.builder()
//                .token(jwtToken)
//                .build();
//    }
}
