package com.medicines.distribution.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class PurchaseOrder {

    public enum Status {
        ON_HOLD, COMPLETED, CANCELLED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "order")
    private List<OrderEquipment> orderEquipments = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "company_admin_id")
    private CompanyAdmin companyAdmin;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private BasicUser customer;

    @OneToOne
    private Appointment appointment;

    @Enumerated(EnumType.STRING)
    private Status status;

    public PurchaseOrder() {
    }

    public PurchaseOrder(Integer id, List<OrderEquipment> orderEquipments, CompanyAdmin companyAdmin, BasicUser customer, Appointment appointment, Status status) {
        this.id = id;
        this.orderEquipments = orderEquipments;
        this.companyAdmin = companyAdmin;
        this.customer = customer;
        this.appointment = appointment;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<OrderEquipment> getOrderEquipments() {
        return orderEquipments;
    }

    public void setOrderEquipments(List<OrderEquipment> orderEquipments) {
        this.orderEquipments = orderEquipments;
    }

    public CompanyAdmin getCompanyAdmin() {
        return companyAdmin;
    }

    public void setCompanyAdmin(CompanyAdmin companyAdmin) {
        this.companyAdmin = companyAdmin;
    }

    public BasicUser getCustomer() {
        return customer;
    }

    public void setCustomer(BasicUser customer) {
        this.customer = customer;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
