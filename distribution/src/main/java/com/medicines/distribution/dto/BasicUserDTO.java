package com.medicines.distribution.dto;

import com.medicines.distribution.model.Address;
import com.medicines.distribution.model.BasicUser;
import com.medicines.distribution.model.Role;
import com.medicines.distribution.model.User;

import java.util.List;

public class BasicUserDTO {

    private UserDTO user;
    private String name;
    private String surname;
    private AddressDTO address;
    private String phone;
    private String profession;

    private Integer penalty;

    public BasicUserDTO(UserDTO user, String name, String surname, AddressDTO address, String phone, String profession, Integer penalty) {
        this.user = user;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
        this.profession = profession;
        this.penalty = penalty;
    }

    public BasicUserDTO(BasicUser user){
        this(new UserDTO(user.getId(),user.getUser().getEmail(),user.getUser().getPassword(),user.getUser().getUsername(), user.getUser().getRoles().get(0).getName()),
                user.getName(),user.getSurname(), new AddressDTO(user.getAddress()),user.getPhone(),user.getProfession(), user.getPenalty());
    }

    public Address convertToAddress(AddressDTO addressDTO) {

        Address address = new Address();
        address.setCountry(addressDTO.getCountry());
        address.setCity(addressDTO.getCity());
        address.setStreet(addressDTO.getStreet());
        address.setNumber(addressDTO.getNumber());
        address.setZipCode(addressDTO.getZipCode());

        return address;
    }


    public UserDTO getUser() {
        return user;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getProfession() {
        return profession;
    }

    public Integer getPenalty() {
        return penalty;
    }
}
