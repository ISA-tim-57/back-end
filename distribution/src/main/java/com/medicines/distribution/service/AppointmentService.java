package com.medicines.distribution.service;

import com.medicines.distribution.model.Appointment;
import com.medicines.distribution.repository.Appointmentrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    Appointmentrepository appointmentRepository;

    public Appointment findOne(Integer id){
        return appointmentRepository.findById(id).orElseGet(null);
    }

    public List<Appointment> findAll(){
        return appointmentRepository.findAll();
    }

    public Appointment save(Appointment appointment){
        return appointmentRepository.save(appointment);
    }

    public void remove(Integer id) {
        appointmentRepository.deleteById(id);
    }
}
