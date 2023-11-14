package com.medicines.distribution.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "administratorName", nullable = false)
    private String administratorName;
    @Column(name = "administratorSurname", nullable = false)
    private String administratorSurname;
    @Column(name = "dateAndTime", nullable = false)
    private LocalDateTime dateAndTime;
    @Column(name = "duration", nullable = false)
    private Integer duration; //u minutima
    @Column(name = "isFree", nullable = false)
    private boolean isFree;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    private Company company;

    public Appointment() {
        super();
    }

    public Appointment(Integer id, String administratorName, String administratorSurname, LocalDateTime dateAndTime, Integer duration, boolean isFree, Company company) {
        super();
        this.id = id;
        this.administratorName = administratorName;
        this.administratorSurname = administratorSurname;
        this.dateAndTime = dateAndTime;
        this.duration = duration;
        this.isFree = isFree;
        this.company = company;
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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAdministratorName(String administratorName) {
        this.administratorName = administratorName;
    }

    public void setAdministratorSurname(String administratorSurname) {
        this.administratorSurname = administratorSurname;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
