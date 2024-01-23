package com.medicines.distribution.dto;

import com.medicines.distribution.model.User;

public class CompanyAdminDTO {

    private Integer id;
    private String email;
    private String username;
    private String name;
    private String surname;
    private Integer companyId;

    public CompanyAdminDTO() {
    }

    public CompanyAdminDTO(User admin) {
        this(admin.getId(),admin.getEmail(),admin.getUsername(), admin.getName(), admin.getSurname(), admin.getCompany().getId());
    }


    public CompanyAdminDTO(Integer id, String email, String username, String name, String surname, Integer companyId) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.companyId = companyId;
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
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

    public Integer getCompanyId() {
        return companyId;
    }
}
