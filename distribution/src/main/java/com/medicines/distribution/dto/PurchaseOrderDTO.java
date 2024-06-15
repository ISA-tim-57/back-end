package com.medicines.distribution.dto;

import com.medicines.distribution.model.OrderEquipment;
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

    public PurchaseOrderDTO() {
    }

    public PurchaseOrderDTO(Integer id, CompanyAdminDTO companyAdmin, BasicUserDTO customer, AppointmentDTO appointment, String status, List<OrderEquipmentDTO> orderEquipments) {
        this.id = id;
        this.companyAdmin = companyAdmin;
        this.customer = customer;
        this.appointment = appointment;
        this.status = status != null ? PurchaseOrder.Status.valueOf(status) : null;
        this.orderEquipments = orderEquipments;
    }

    public PurchaseOrderDTO(PurchaseOrder purchaseOrder) {
        this.id = purchaseOrder.getId();
        this.companyAdmin = new CompanyAdminDTO(purchaseOrder.getCompanyAdmin());
        this.customer = new BasicUserDTO(purchaseOrder.getCustomer());
        this.appointment = new AppointmentDTO(purchaseOrder.getAppointment());
        this.status = purchaseOrder.getStatus() != null ? PurchaseOrder.Status.valueOf(purchaseOrder.getStatus().toString()) : null;
        this.orderEquipments = convertToDTOList(purchaseOrder.getOrderEquipments());
    }

    public List<OrderEquipmentDTO> getOrderEquipments() {
        return orderEquipments;
    }

    public void setOrderEquipments(List<OrderEquipmentDTO> orderEquipments) {
        this.orderEquipments = orderEquipments;
    }

    public PurchaseOrder.Status setStatus(String status) {
        if (Arrays.stream(PurchaseOrder.Status.values()).anyMatch(e -> e.name().equals(status))) {
            switch (status) {
                case "ON_HOLD":
                    return PurchaseOrder.Status.ON_HOLD;
                case "COMPLETED":
                    return PurchaseOrder.Status.COMPLETED;
                default:
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

    private List<OrderEquipmentDTO> convertToDTOList(List<OrderEquipment> orderEquipments) {
        List<OrderEquipmentDTO> alldtos = new ArrayList<>();
        for (OrderEquipment oe : orderEquipments) {
            OrderEquipmentDTO dtoTEMP = new OrderEquipmentDTO();
            dtoTEMP.setQuantity(oe.getQuantity());
            dtoTEMP.setPurchaseOrder(oe.getOrder().getId());
            dtoTEMP.setEquipment(new EquipmentDTO(oe.getEquipment()));
            dtoTEMP.setId(oe.getId());
            alldtos.add(dtoTEMP);
        }
        return alldtos;
    }
}
