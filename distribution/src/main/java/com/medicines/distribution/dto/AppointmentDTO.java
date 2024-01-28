package com.medicines.distribution.dto;

import com.medicines.distribution.model.Appointment;

import java.time.LocalDateTime;

public class AppointmentDTO {
    private Integer id;
    private String administratorName;
    private String administratorSurname;
    private Integer adminUserId; //zapravo ovo je userID
    private String dateAndTime;
    private Integer duration;
    private boolean free;
    private Integer companyId;

    public AppointmentDTO() {
    }

    public AppointmentDTO(Integer id, String administratorName, String administratorSurname, Integer adminUserId, String dateAndTime, Integer duration, boolean isFree, Integer companyId) {
        this.id = id;
        this.administratorName = administratorName;
        this.administratorSurname = administratorSurname;
        this.adminUserId = adminUserId;
        this.dateAndTime = dateAndTime;
        this.duration = duration;
        this.free = isFree;
        this.companyId = companyId;
    }

    public AppointmentDTO(Appointment appointment) {
        this(appointment.getId(),appointment.getAdministratorName(),appointment.getAdministratorSurname(),appointment.getAdminUserId(),appointment.getDateAndTime().toString(),appointment.getDuration(),appointment.isFree(),appointment.getCompany().getId());
    }

    public Integer getId() {
        return id;
    }

    public String getAdministratorName() {
        return administratorName;
    }

    public String getAdministratorSurname() {
        return administratorSurname;
    }

    public LocalDateTime getDateAndTime() {
        return LocalDateTime.parse(dateAndTime);
    }

    public String getDateAndTimeAsString() {
        return dateAndTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public boolean isFree() {
        return free;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public Integer getAdminUserId() {
        return adminUserId;
    }
}
