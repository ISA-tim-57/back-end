package com.medicines.distribution.model;

import jakarta.persistence.*;

@Entity
public class OrderEquipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "purchaseOrder_id")
    private PurchaseOrder purchaseOrder;

    @ManyToOne
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;

    private int quantity;

    public OrderEquipment() {
    }

    public OrderEquipment(Integer id, PurchaseOrder order, Equipment equipment, int quantity) {
        this.id = id;
        this.purchaseOrder = order;
        this.equipment = equipment;
        this.quantity = quantity;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PurchaseOrder getOrder() {
        return purchaseOrder;
    }

    public void setOrder(PurchaseOrder order) {
        this.purchaseOrder = order;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
