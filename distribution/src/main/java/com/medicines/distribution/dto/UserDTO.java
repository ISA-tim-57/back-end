package com.medicines.distribution.dto;


import com.medicines.distribution.model.Address;
import com.medicines.distribution.model.User;

public class UserDTO{

    private Integer id;
    private String email;
    private String password;
    private String username;
    private String name;
    private String surname;
    private AddressDTO address;
    private String phone;
    private String profession;
    private String companyInfo;


    public UserDTO() {
    }

    public UserDTO(Integer id, String email, String password, String username, String name, String surname, AddressDTO address, String phone, String profession, String companyInfo) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
        this.profession = profession;
        this.companyInfo = companyInfo;
    }

    public UserDTO(User user){
        this(user.getId(),user.getEmail(),user.getPassword(), user.getUsername(), user.getName(), user.getSurname(), new AddressDTO(user.getAddress()), user.getPhone(), user.getProfession(),user.getCompanyInfo());
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

    public String getCompanyInfo() {
        return companyInfo;
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



}