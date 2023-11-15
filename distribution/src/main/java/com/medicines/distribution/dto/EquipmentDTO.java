package com.medicines.distribution.dto;

import com.medicines.distribution.model.Equipment;

public class EquipmentDTO {
    private Integer id;
    private String name;
    private String description;
    private double price;
    private Integer companyId;

    public EquipmentDTO() {
    }

    public EquipmentDTO(Integer id, String name, String description, double price, Integer companyId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.companyId = companyId;
    }

    public EquipmentDTO(Equipment equipment) {
        this(equipment.getId(), equipment.getName(), equipment.getDescription(), equipment.getPrice(), equipment.getCompany().getId());
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

    public Integer getCompanyId() {
        return companyId;
    }
}
