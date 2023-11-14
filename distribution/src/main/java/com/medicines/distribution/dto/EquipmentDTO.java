package com.medicines.distribution.dto;

import com.medicines.distribution.model.Equipment;

public class EquipmentDTO {
    private Integer id;
    private String name;
    private String description;
    private double price;
    private CompanyDTO company;

    public EquipmentDTO() {
    }

    public EquipmentDTO(Integer id, String name, String description, double price, CompanyDTO company) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.company = company;
    }

    public EquipmentDTO(Equipment equipment) {
        this(equipment.getId(), equipment.getName(), equipment.getDescription(), equipment.getPrice(), new CompanyDTO(equipment.getCompany()));
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

    public double getPrice() {
        return price;
    }

    public CompanyDTO getCompany() {
        return company;
    }
}
