package com.medicines.distribution.dto;

import com.medicines.distribution.model.Appointment;

import java.time.LocalDateTime;

public class AppointmentDTO {
    private Integer id;
    private String administratorName;
    private String administratorSurname;
    private LocalDateTime dateAndTime;
    private Integer duration;
    private boolean isFree;
    private CompanyDTO company;

    public AppointmentDTO() {
    }

    public AppointmentDTO(Integer id, String administratorName, String administratorSurname, LocalDateTime dateAndTime, Integer duration, boolean isFree, CompanyDTO company) {
        this.id = id;
        this.administratorName = administratorName;
        this.administratorSurname = administratorSurname;
        this.dateAndTime = dateAndTime;
        this.duration = duration;
        this.isFree = isFree;
        this.company = company;
    }

    public AppointmentDTO(Appointment appointment) {
        this(appointment.getId(),appointment.getAdministratorName(),appointment.getAdministratorSurname(),appointment.getDateAndTime(),appointment.getDuration(),appointment.isFree(),new CompanyDTO(appointment.getCompany()));
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
        return dateAndTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public boolean isFree() {
        return isFree;
    }

    public CompanyDTO getCompany() {
        return company;
    }
}
