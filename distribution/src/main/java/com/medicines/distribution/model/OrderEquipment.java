package com.medicines.distribution.model;

import jakarta.persistence.*;

@Entity
public class OrderEquipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private PurchaseOrder order;

    @ManyToOne
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;

    private int quantity;

    public OrderEquipment() {
    }

    public OrderEquipment(Integer id, PurchaseOrder order, Equipment equipment, int quantity) {
        this.id = id;
        this.order = order;
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
        return order;
    }

    public void setOrder(PurchaseOrder order) {
        this.order = order;
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
