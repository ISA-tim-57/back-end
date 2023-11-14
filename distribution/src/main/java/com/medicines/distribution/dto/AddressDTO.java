package com.medicines.distribution.dto;

import com.medicines.distribution.model.Address;

public class AddressDTO {

    private Integer id;
    private String country;
    private String city;
    private String number;
    private String zipCode;

    public AddressDTO() {
    }

    public AddressDTO(Integer id, String country, String city, String number, String zipCode) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.number = number;
        this.zipCode = zipCode;
    }

    public AddressDTO(Address address) {
        this(address.getId(),address.getCountry(),address.getCity(),address.getNumber(),address.getZipCode());
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
}
