package com.medicines.distribution.service;

import com.medicines.distribution.model.Address;
import com.medicines.distribution.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    
    @Autowired
    AddressRepository addressRepository;

    public Address findOne(Integer id){
        return addressRepository.findById(id).orElseGet(null);
    }

    public List<Address> findAll(){
        return addressRepository.findAll();
    }

    public Address save(Address address){
        return addressRepository.save(address);
    }

    public void remove(Integer id) {
        addressRepository.deleteById(id);
    }

    public Address update(Integer id,Address address){return addressRepository.update(id,address);}
}
