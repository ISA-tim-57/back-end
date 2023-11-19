package com.medicines.distribution.controller;

import com.medicines.distribution.dto.UserDTO;
import com.medicines.distribution.model.User;
import com.medicines.distribution.service.UserService;
import jdk.javadoc.doclet.Reporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "api/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO) {

        List<User> existingUsers = userService.findAll();

        // Provera da li postoji korisnik sa istim korisničkim imenom
        boolean usernameExists = existingUsers.stream()
                .anyMatch(user -> user.getUsername().equals(userDTO.getUsername()));

        if (usernameExists) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        // Ako korisnik sa istim korisničkim imenom ne postoji, nastavi sa registracijom
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setAddress(userDTO.convertToAddress(userDTO.getAddressDTO()));
        user.setPhone(userDTO.getPhone());
        user.setProfession(userDTO.getProfession());
        user.setCompanyInfo(userDTO.getCompanyInfo());
        user.setUsername(userDTO.getUsername());

        user = userService.save(user);
        return new ResponseEntity<>(new UserDTO(user), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Integer id){
        User user = userService.findOne(id);

        if(user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
    }

    @PutMapping("updateUser/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Integer id, @RequestBody User updatedUser){
        User user = userService.updateUser(id,updatedUser);
        return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
    }

}