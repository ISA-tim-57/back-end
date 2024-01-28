package com.medicines.distribution.controller;

import com.medicines.distribution.dto.CompanyDTO;
import com.medicines.distribution.dto.PurchaseOrderDTO;
import com.medicines.distribution.model.Company;
import com.medicines.distribution.model.CompanyAdmin;
import com.medicines.distribution.model.PurchaseOrder;
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


}
