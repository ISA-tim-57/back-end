package com.medicines.distribution.dto;

import com.medicines.distribution.model.CompanyAdmin;
import com.medicines.distribution.model.User;

public class CompanyAdminDTO {

    private UserDTO user;
    private String name;
    private String surname;
    private Integer companyId;

    public CompanyAdminDTO(UserDTO user, Integer companyId, String name, String surname) {
        this.user = user;
        this.name = name;
        this.surname = surname;
        this.companyId = companyId;
    }

    public CompanyAdminDTO(CompanyAdmin admin){
        this(new UserDTO(admin.getId(), admin.getUser().getEmail(),admin.getUser().getPassword(),admin.getUser().getUsername(),admin.getUser().getRoles().get(0).getName()), admin.getCompany().getId(), admin.getName(), admin.getSurname());
    }

    public UserDTO getUser() {
        return user;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}
