package com.medicines.distribution.service;

import com.medicines.distribution.model.Address;
import com.medicines.distribution.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class AddressService {
    
    @Autowired
    AddressRepository addressRepository;

    public Address findOne(Integer id){
        return addressRepository.findById(id).orElseGet(null);
    }

    public List<Address> findAll(){
        return addressRepository.findAll();
    }

    @Transactional(readOnly = false)
    public Address save(Address address){
        return addressRepository.save(address);
    }

    @Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ)
    public void remove(Integer id) {
        addressRepository.deleteById(id);
    }

    @Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ)
    public Address update(Integer id,Address address){return addressRepository.update(id,address);}
}
