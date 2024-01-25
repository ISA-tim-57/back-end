package com.medicines.distribution.dto;

import com.medicines.distribution.model.Address;
import com.medicines.distribution.model.User;

import java.time.LocalTime;
import java.util.List;

public class UserDTO {

    private Integer id;
    private String email;
    private String password;
    private String username;
    private String role;

    public UserDTO() {
    }

    public UserDTO(User user) {
        this(user.getId(), user.getEmail(), user.getPassword(), user.getUsername(),user.getRoles().get(0).getName());
    }

    public UserDTO(Integer id, String email, String password, String username, String role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

//    public Address convertToAddress(AddressDTO addressDTO) {
//
//        Address address = new Address();
//        address.setCountry(addressDTO.getCountry());
//        address.setCity(addressDTO.getCity());
//        address.setStreet(addressDTO.getStreet());
//        address.setNumber(addressDTO.getNumber());
//        address.setZipCode(addressDTO.getZipCode());
//
//        return address;
//    }

}

