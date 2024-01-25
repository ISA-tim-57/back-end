package com.medicines.distribution.dto;

import com.medicines.distribution.model.Appointment;
import com.medicines.distribution.model.BasicUser;
import com.medicines.distribution.model.PurchaseOrder;

import java.util.Arrays;

public class PurchaseOrderDTO {
    private Integer id;
    private CompanyAdminDTO companyAdmin;
    private BasicUserDTO customer;
    private AppointmentDTO appointment;
    private PurchaseOrder.Status status;

    public PurchaseOrderDTO(Integer id, CompanyAdminDTO companyAdmin, BasicUserDTO customer, AppointmentDTO appointment, String status) {
        this.id = id;
        this.companyAdmin = companyAdmin;
        this.customer = customer;
        this.appointment = appointment;
        this.status = setStatus(status);
    }

    public PurchaseOrderDTO(PurchaseOrder purchaseOrder) {
        this(purchaseOrder.getId(),
                new CompanyAdminDTO(purchaseOrder.getCompanyAdmin()),
                new BasicUserDTO(purchaseOrder.getCustomer()),
                new AppointmentDTO(purchaseOrder.getAppointment()),
                purchaseOrder.getStatus().toString());
    }

    public PurchaseOrder.Status setStatus(String status) {
        if (Arrays.stream(PurchaseOrder.Status.values()).anyMatch(e -> e.name().equals(status))) {
            if(status.equals("ON_HOLD")){
                return PurchaseOrder.Status.ON_HOLD;
            } else if (status.equals("COMPLETED")) {
                return PurchaseOrder.Status.COMPLETED;
            }
            else {
                return PurchaseOrder.Status.CANCELLED;
            }
        } else {
            throw new IllegalArgumentException("Invalid OrderStatus value: " + status);
        }
    }

    public Integer getId() {
        return id;
    }

    public CompanyAdminDTO getCompanyAdmin() {
        return companyAdmin;
    }

    public BasicUserDTO getCustomer() {
        return customer;
    }

    public AppointmentDTO getAppointment() {
        return appointment;
    }

    public PurchaseOrder.Status getStatus() {
        return status;
    }
}
