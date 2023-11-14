package com.medicines.distribution.dto;

import com.medicines.distribution.model.Address;
import com.medicines.distribution.model.Company;

public class CompanyDTO {

    private Integer id;
    private String name;
    private String description;
    private AddressDTO address;
    private double rating;

    public CompanyDTO() {
    }

    public CompanyDTO(Company company) {
        this(company.getId(),company.getName(),company.getDescription(),new AddressDTO(company.getAddress()),company.getRating());
    }

    public CompanyDTO(Integer id, String name, String description, AddressDTO address, double rating) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.address = address;
        this.rating = rating;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public double getRating() {
        return rating;
    }

    public Address convertToAddress(AddressDTO addressDTO) {
        Address address = new Address();
        address.setCountry(addressDTO.getCountry());
        address.setCity(addressDTO.getCity());
        address.setNumber(addressDTO.getNumber());
        address.setZipCode(addressDTO.getZipCode());

        return address;
    }
}
