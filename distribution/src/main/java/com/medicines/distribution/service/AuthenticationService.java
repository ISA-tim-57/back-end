package com.medicines.distribution.service;

import com.medicines.distribution.dto.BasicUserDTO;
import com.medicines.distribution.dto.UserDTO;
import com.medicines.distribution.model.Address;
import com.medicines.distribution.model.BasicUser;
import com.medicines.distribution.model.Role;
import com.medicines.distribution.model.User;
import com.medicines.distribution.repository.BasicUserRepository;
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
    private final BasicUserRepository basicUserRepository;
    private final AddressService addressService;
    private final PasswordEncoder passwordEncoder;
    private final TokenUtils tokenUtils;
    private final AuthenticationManager authenticationManager;

    @Autowired
    private RoleService roleService;

    public BasicUser register(BasicUserDTO request) {


        Address address = new Address();
        address = addressService.save(request.convertToAddress(request.getAddress()));

        List<Role> roles = roleService.findByName("ROLE_USER");

        User user = new User();
        user.setEmail(request.getUser().getEmail());
        user.setUsername(request.getUser().getUsername());
        user.setPassword(passwordEncoder.encode(request.getUser().getPassword()));
        user.setRoles(roles);
        user = repository.save(user);

        BasicUser u = new BasicUser();

        u.setUser(user);
        u.setName(request.getName());
        u.setSurname(request.getSurname());
        u.setAddress(address);
        u.setPhone(request.getPhone());
        u.setProfession(request.getProfession());
        u.setPenalty(0);

        BasicUser savedUser = basicUserRepository.save(u);
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
