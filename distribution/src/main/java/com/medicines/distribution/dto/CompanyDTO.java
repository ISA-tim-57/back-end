package com.medicines.distribution.dto;

import com.medicines.distribution.model.Address;
import com.medicines.distribution.model.Company;

import java.time.LocalTime;

public class CompanyDTO {

    private Integer id;
    private String name;
    private String description;
    private AddressDTO address;
    private double rating;
    private LocalTime workingHoursStart;
    private LocalTime workingHoursEnd;

    public CompanyDTO() {
    }

    public CompanyDTO(Company company) {
        this(company.getId(),company.getName(),company.getDescription(),new AddressDTO(company.getAddress()),company.getRating(),company.getWorkingHoursStart(),company.getWorkingHoursEnd());
    }

    public CompanyDTO(Integer id, String name, String description, AddressDTO address, double rating, LocalTime workingHoursStart, LocalTime workingHoursEnd) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.address = address;
        this.rating = rating;
        this.workingHoursStart = workingHoursStart;
        this.workingHoursEnd = workingHoursEnd;

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

    public LocalTime getWorkingHoursStart() {
        return workingHoursStart;
    }

    public LocalTime getWorkingHoursEnd() {
        return workingHoursEnd;
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
