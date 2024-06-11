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
    @Column(name = "admin_user_id", nullable = false)    //zapravo ovo je userID
    private Integer adminUserId;
    @Column(name = "dateAndTime", nullable = false)
    private LocalDateTime dateAndTime;
    @Column(name = "duration", nullable = false)
    private Integer duration; //u minutima
    @Column(name = "isFree", nullable = false)
    private boolean isFree;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    private Company company;


    @JoinColumn(name = "purchase_order_id",nullable = true)
    @OneToOne
    private PurchaseOrder purchaseOrder;



    public Appointment() {

        super();
        this.purchaseOrder=null;

    }

    public Appointment(Integer id, String administratorName, String administratorSurname, Integer adminUserId, LocalDateTime dateAndTime, Integer duration, boolean isFree, Company company) {
        super();
        this.id = id;
        this.administratorName = administratorName;
        this.administratorSurname = administratorSurname;
        this.adminUserId = adminUserId;
        this.dateAndTime = dateAndTime;
        this.duration = duration;
        this.isFree = isFree;
        this.company = company;
        this.purchaseOrder=null;
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

    public Integer getAdminUserId() {
        return adminUserId;
    }

    public void setAdminUserId(Integer adminUserId) {
        this.adminUserId = adminUserId;
    }
}
