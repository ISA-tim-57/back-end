package com.medicines.distribution.dto;

import com.medicines.distribution.model.OrderEquipment;

public class OrderEquipmentDTO {
    private Integer id;
  //  private PurchaseOrderDTO purchaseOrder;
    private EquipmentDTO equipment;
    private Integer quantity;

    public OrderEquipmentDTO(Integer id/*, PurchaseOrderDTO purchaseOrder*/, EquipmentDTO equipment, Integer quantity) {
        this.id = id;
       // this.purchaseOrder = purchaseOrder;
        this.equipment = equipment;
        this.quantity = quantity;
    }

    public OrderEquipmentDTO(OrderEquipment orderEquipment) {
        this(
                orderEquipment.getId(),
              //  new PurchaseOrderDTO(orderEquipment.getOrder()),
                new EquipmentDTO(orderEquipment.getEquipment()),
                orderEquipment.getQuantity()
        );
    }

    public OrderEquipmentDTO() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

/*    public PurchaseOrderDTO getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(PurchaseOrderDTO purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }
*/
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
