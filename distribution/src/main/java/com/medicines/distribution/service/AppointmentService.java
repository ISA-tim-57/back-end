package com.medicines.distribution.service;

import com.medicines.distribution.model.Appointment;
import com.medicines.distribution.repository.Appointmentrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

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

    public boolean checkIfAdminIsFree(Appointment appointment, Integer adminUserId){
        Set<Appointment> appointments  = appointmentRepository.findAllByAdminUserId(adminUserId);
        boolean isFree = true;

        for(Appointment appoint : appointments){
            //Pocetak termina izmedju pocetka i kraja postojeceg trermina
            if(appointment.getDateAndTime().isAfter(appoint.getDateAndTime()) && appoint.getDateAndTime().isBefore(appoint.getDateAndTime().plusMinutes(appoint.getDuration()))){
                isFree =  false;
            }
            //kraj termina izmedju pocetka i kraja postojeceg termina
            else if(appointment.getDateAndTime().plusMinutes(appointment.getDuration()).isAfter(appoint.getDateAndTime()) && appointment.getDateAndTime().plusMinutes(appointment.getDuration()).isBefore(appoint.getDateAndTime().plusMinutes(appoint.getDuration()))){
                isFree = false;
            }
            //Postojeci termin izmedju pocetka i kraja novog termina
            else if(appointment.getDateAndTime().isBefore(appoint.getDateAndTime()) && appointment.getDateAndTime().plusMinutes(appointment.getDuration()).isAfter(appoint.getDateAndTime().plusMinutes(appoint.getDuration()))){
                isFree = false;
            }
            else{
                continue;
            }
        }
        return isFree;
    }
}
