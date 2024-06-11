package com.medicines.distribution.controller;

import com.medicines.distribution.dto.*;
import com.medicines.distribution.model.*;
import com.medicines.distribution.repository.Appointmentrepository;
import com.medicines.distribution.repository.BasicUserRepository;
import com.medicines.distribution.repository.CompanyAdminRepository;
import com.medicines.distribution.repository.PurchaseOrderRepository;
import com.medicines.distribution.service.OrderEquipmentService;
import com.medicines.distribution.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "api/purchaseorder")
public class PurchaseOrderController {

    @Autowired
    PurchaseOrderService purchaseOrderService;
    @Autowired
    OrderEquipmentService orderEquipmentService;

    @Autowired
    CompanyAdminRepository companyAdminRepository;

    @Autowired
    BasicUserRepository basicUserRepository;

    @Autowired
    Appointmentrepository appointmentRepository;

    @Autowired
    PurchaseOrderRepository purchaseOrderRepository;




    @PostMapping("/add")
    public ResponseEntity<PurchaseOrderDTO> addPurchaseOrder(@RequestBody PurchaseOrderDTO purchaseOrderDTO) {
        PurchaseOrder purchaseOrder = purchaseOrderService.addPurchaseOrder(purchaseOrderDTO);
        PurchaseOrderDTO responseDTO = new PurchaseOrderDTO(purchaseOrder);
        return ResponseEntity.ok(responseDTO);
    }
    @GetMapping("/byuser/{userId}")
    public ResponseEntity<List<PurchaseOrderDTO>> getPurchaseOrdersByUserId(@PathVariable Integer userId) {
        Set<PurchaseOrder> purchaseOrders = purchaseOrderService.getPurchaseOrdersByUserId(userId);
        if (purchaseOrders != null && !purchaseOrders.isEmpty()) {
            List<PurchaseOrderDTO> purchaseOrderDTOs = new ArrayList<>();
            for (PurchaseOrder p : purchaseOrders) {
                purchaseOrderDTOs.add(new PurchaseOrderDTO(p));
            }
            return new ResponseEntity<>(purchaseOrderDTOs, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/cancel/{userId}")
    public ResponseEntity<PurchaseOrderDTO> cancelByUser(@PathVariable Integer userId, @RequestBody PurchaseOrderDTO purchaseOrderDTO) {
        try {
            PurchaseOrder purchaseOrder = new PurchaseOrder(purchaseOrderDTO.getId(), null, null, null, null, PurchaseOrder.Status.CANCELLED);
            PurchaseOrder canceledPurchaseOrder = purchaseOrderService.cancelByUser(userId, purchaseOrder);
            PurchaseOrderDTO canceledPurchaseOrderDTO = new PurchaseOrderDTO(canceledPurchaseOrder);
            return new ResponseEntity<>(canceledPurchaseOrderDTO, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping(value = "/bycompanyadmin/{id}")
    public ResponseEntity<List<PurchaseOrderDTO>> getPurchaseOrdersForCompany(@PathVariable Integer id){

        Set<PurchaseOrder> purchaseOrders = purchaseOrderService.getCompanyAdminsOrdersOnHold(id);
        if(purchaseOrders != null){
            List<PurchaseOrderDTO> purchaseOrderDTOs = new ArrayList<>();
            for(PurchaseOrder p : purchaseOrders){
                purchaseOrderDTOs.add(new PurchaseOrderDTO(p));
            }
            return new ResponseEntity<>(purchaseOrderDTOs, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/changestatus/{id}")
    public ResponseEntity<PurchaseOrderDTO> changePassword(@PathVariable Integer id, @RequestBody PurchaseOrderDTO updatedPurchaseOrder){
        return new ResponseEntity<>(new PurchaseOrderDTO(purchaseOrderService.markAsCompleted(id)), HttpStatus.OK);
    }

    @GetMapping("/{companyId}/customers")
    public ResponseEntity<List<BasicUserDTO>> getCustomers(@PathVariable Integer companyId){
        Set<BasicUser> customers = purchaseOrderService.GetAllCustomersForActiveCompanyOrders(companyId);
        List<BasicUserDTO> basicUserDTOS = new ArrayList<BasicUserDTO>();
        for(BasicUser customer : customers){
            basicUserDTOS.add(new BasicUserDTO(customer));
        }
        return new ResponseEntity<>(basicUserDTOS,HttpStatus.OK);
    }


}
