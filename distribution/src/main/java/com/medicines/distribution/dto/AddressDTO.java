package com.medicines.distribution.dto;

import com.medicines.distribution.model.Address;

public class AddressDTO {

    private Integer id;
    private String country;
    private String city;

    private String street;
    private String number;
    private String zipCode;

    public AddressDTO() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public AddressDTO(Integer id, String country, String city, String street, String number, String zipCode) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.street = street;
        this.number = number;
        this.zipCode = zipCode;
    }

    public AddressDTO(Address address) {
        this(address.getId(),address.getCountry(),address.getCity(),address.getStreet(),address.getNumber(),address.getZipCode());
    }

    public Integer getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getNumber() {
        return number;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getStreet() {
        return street;
    }
}
