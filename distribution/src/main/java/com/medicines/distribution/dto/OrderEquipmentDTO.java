package com.medicines.distribution.dto;

import com.medicines.distribution.model.OrderEquipment;

public class OrderEquipmentDTO {
    private Integer id;
    private Integer purchaseOrderId;
    private EquipmentDTO equipment;
    private Integer quantity;

    public OrderEquipmentDTO(Integer id, Integer purchaseOrderId, EquipmentDTO equipment, Integer quantity) {
        this.id = id;
        this.purchaseOrderId = purchaseOrderId;
        this.equipment = equipment;
        this.quantity = quantity;
    }

    public OrderEquipmentDTO(OrderEquipment orderEquipment) {
        this(orderEquipment.getId(), orderEquipment.getOrder().getId(),new EquipmentDTO(orderEquipment.getEquipment()),orderEquipment.getQuantity());
    }

    public Integer getId() {
        return id;
    }

    public Integer getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public EquipmentDTO getEquipment() {
        return equipment;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
