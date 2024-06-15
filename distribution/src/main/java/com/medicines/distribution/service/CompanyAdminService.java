package com.medicines.distribution.service;

import com.medicines.distribution.model.BasicUser;
import com.medicines.distribution.model.Company;
import com.medicines.distribution.model.CompanyAdmin;
import com.medicines.distribution.model.PurchaseOrder;
import com.medicines.distribution.repository.BasicUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class CompanyAdminService {
    @Autowired
    private BasicUserRepository basicUserRepository;

    @Autowired
    private  PurchaseOrderService purchaseOrderService;

    public List<CompanyAdmin> getCompanyByUserForReport(Integer userId) {

       // BasicUser basicUser=basicUserRepository.findByUserId(userId);
        List<CompanyAdmin> companieAdmins=new ArrayList<>();
        Set<PurchaseOrder> purchaseOrders=purchaseOrderService.getPurchaseOrdersByUserId(userId);

        for(PurchaseOrder p:purchaseOrders)
        {
            if(p.getCustomer().getUser().getId()==userId)
            {
                companieAdmins.add(p.getCompanyAdmin());
            }

        }

        return companieAdmins;



    }
}
