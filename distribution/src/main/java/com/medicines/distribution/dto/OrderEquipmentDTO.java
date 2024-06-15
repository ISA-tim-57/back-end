package com.medicines.distribution.dto;

import com.medicines.distribution.model.OrderEquipment;

public class OrderEquipmentDTO {
    private Integer id;
    private Integer purchaseOrder;
    private EquipmentDTO equipment;
    private Integer quantity;

    public OrderEquipmentDTO(Integer id, Integer purchaseOrder, EquipmentDTO equipment, Integer quantity) {
        this.id = id;
        this.purchaseOrder = purchaseOrder;
        this.equipment = equipment;
        this.quantity = quantity;
    }


    public OrderEquipmentDTO() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

  public Integer getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(Integer purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public void setEquipment(EquipmentDTO equipment) {
        this.equipment = equipment;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public EquipmentDTO getEquipment() {
        return equipment;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
