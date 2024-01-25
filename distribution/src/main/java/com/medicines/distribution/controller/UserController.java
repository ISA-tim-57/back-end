package com.medicines.distribution.controller;


import com.medicines.distribution.dto.*;
import com.medicines.distribution.model.BasicUser;
import com.medicines.distribution.model.CompanyAdmin;
import jdk.javadoc.doclet.Reporter;


import com.medicines.distribution.model.Company;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


import com.medicines.distribution.model.User;
import com.medicines.distribution.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;



@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "api/users")
public class UserController {


    @Autowired
    UserService userService;


//    @PostMapping(consumes = "application/json")
//    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO) {
//
//        List<User> existingUsers = userService.findAll();
//
//        // Provera da li postoji korisnik sa istim korisničkim imenom
//        boolean usernameExists = existingUsers.stream()
//                .anyMatch(user -> user.getUsername().equals(userDTO.getUsername()));
//
//        if (usernameExists) {
//            return new ResponseEntity<>(HttpStatus.CONFLICT);
//        }
//
//        // Ako korisnik sa istim korisničkim imenom ne postoji, nastavi sa registracijom
//        User user = new User();
//        user.setEmail(userDTO.getEmail());
//        user.setPassword(userDTO.getPassword());
//        user.setName(userDTO.getName());
//        user.setSurname(userDTO.getSurname());
//        user.setAddress(userDTO.convertToAddress(userDTO.getAddress()));
//        user.setPhone(userDTO.getPhone());
//        user.setProfession(userDTO.getProfession());
//        user.setCompanyInfo(userDTO.getCompanyInfo());
//        user.setUsername(userDTO.getUsername());
//        user.setCompany(null);
//
//        user = userService.save(user);
//        return new ResponseEntity<>(new UserDTO(user), HttpStatus.CREATED);
//    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Integer id){
        User user = userService.findOne(id);

        if(user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
    }

    @GetMapping(value = "companyadmin/{id}")
    public ResponseEntity<CompanyAdminDTO> getCompanyAdmin(@PathVariable Integer id){
        CompanyAdmin companyAdmin = userService.findOneCompanyAdmin(id);

        if(companyAdmin == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new CompanyAdminDTO(companyAdmin), HttpStatus.OK);
    }

    @GetMapping(value = "basicuser/{id}")
    public ResponseEntity<BasicUserDTO> getBasicUser(@PathVariable Integer id){
        BasicUser basicUser = userService.findOneBasicUser(id);

        if(basicUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new BasicUserDTO(basicUser), HttpStatus.OK);
    }





    @PutMapping("updatecompanyadmin/{id}")
    public ResponseEntity<CompanyAdminDTO> update(@PathVariable Integer id, @RequestBody CompanyAdminDTO updatedCompanyAdmin){
        CompanyAdmin companyAdmin = new CompanyAdmin();
        companyAdmin.setName(updatedCompanyAdmin.getName());
        companyAdmin.setSurname(updatedCompanyAdmin.getSurname());
        CompanyAdmin admin = userService.updateCompanyAdmin(id, companyAdmin );
        return new ResponseEntity<>(new CompanyAdminDTO(admin), HttpStatus.OK);
    }


    @GetMapping("/whoami")
    public ResponseEntity<UserDTO> user(Principal user) {
        User tempUser = this.userService.findByEmail(user.getName());
        return new ResponseEntity<>(new UserDTO(tempUser), HttpStatus.OK);
    }

    @PutMapping("changepassword/{id}")
    public ResponseEntity<Integer> changePassword(@PathVariable Integer id, @RequestBody ChangePasswordRequest request){
        User userWithChangedPassword = userService.changePassword(id,request);
        if(userWithChangedPassword == null){
            return new ResponseEntity<>(0,HttpStatus.OK);
        }
        return new ResponseEntity<>(userWithChangedPassword.getId(),HttpStatus.OK);
    }
}


