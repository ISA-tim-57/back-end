package com.medicines.distribution.dto;

import com.medicines.distribution.model.PurchaseOrder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PurchaseOrderDTO {
    private Integer id;
    private CompanyAdminDTO companyAdmin;
    private BasicUserDTO customer;
    private AppointmentDTO appointment;
    private PurchaseOrder.Status status;

    private List<OrderEquipmentDTO> orderEquipments;

    public PurchaseOrderDTO(Integer id, CompanyAdminDTO companyAdmin, BasicUserDTO customer, AppointmentDTO appointment, String status) {
        this.id = id;
        this.companyAdmin = companyAdmin;
        this.customer = customer;
        this.appointment = appointment;
        this.status = PurchaseOrder.Status.valueOf(status);
        this.orderEquipments=new ArrayList<>();
    }

    public PurchaseOrderDTO(PurchaseOrder purchaseOrder) {
        this(purchaseOrder.getId(),
                new CompanyAdminDTO(purchaseOrder.getCompanyAdmin()),
                new BasicUserDTO(purchaseOrder.getCustomer()),
                new AppointmentDTO(purchaseOrder.getAppointment()),
                purchaseOrder.getStatus().toString());
    }

    public List<OrderEquipmentDTO> getOrderEquipments() {
        return orderEquipments;
    }

    public void setOrderEquipments(List<OrderEquipmentDTO> orderEquipments) {
        this.orderEquipments = orderEquipments;
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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCompanyAdmin(CompanyAdminDTO companyAdmin) {
        this.companyAdmin = companyAdmin;
    }

    public void setCustomer(BasicUserDTO customer) {
        this.customer = customer;
    }

    public void setAppointment(AppointmentDTO appointment) {
        this.appointment = appointment;
    }

    public PurchaseOrder.Status getStatus() {
        return status;
    }

    public void setStatus(PurchaseOrder.Status status) {
        this.status = status;
    }
}
