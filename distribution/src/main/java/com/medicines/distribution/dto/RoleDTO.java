package com.medicines.distribution.dto;

import com.medicines.distribution.model.Role;

public class RoleDTO {

    private Long id;
    private String name;

    public RoleDTO(){

    }

    public RoleDTO(Role role){
        this(role.getId(),role.getName());
    }
    public RoleDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
