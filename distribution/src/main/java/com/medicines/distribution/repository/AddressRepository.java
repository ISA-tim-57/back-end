package com.medicines.distribution.repository;

import com.medicines.distribution.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Integer> {
    default Address update(Integer id, Address updatedAddress){
        Address address = findById(id).orElse(null);

        if(address != null){
            address.setCountry(updatedAddress.getCountry());
            address.setCity(updatedAddress.getCity());
            address.setStreet(updatedAddress.getStreet());
            address.setNumber(updatedAddress.getNumber());
            address.setZipCode(updatedAddress.getZipCode());
            save(address);
        }

        return address;
    }
}
