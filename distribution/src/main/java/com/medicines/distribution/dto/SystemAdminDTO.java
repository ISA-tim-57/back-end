package com.medicines.distribution.dto;

import com.medicines.distribution.model.SystemAdmin;

public class SystemAdminDTO {
    private UserDTO user;

    public SystemAdminDTO(UserDTO user) {
        this.user = user;
    }

    public SystemAdminDTO(SystemAdmin admin){
        this(new UserDTO(admin.getId(),admin.getUser().getEmail(),admin.getUser().getPassword(), admin.getUser().getUsername(), admin.getUser().getRoles().get(0).getName()));
    }

    public UserDTO getUser() {
        return user;
    }
}
