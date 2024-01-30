package com.medicines.distribution.service;

import com.medicines.distribution.model.Appointment;
import com.medicines.distribution.repository.Appointmentrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class AppointmentService {

    @Autowired
    Appointmentrepository appointmentRepository;

    public Appointment findOne(Integer id){
        return appointmentRepository.findById(id).orElseGet(null);
    }

    public List<Appointment> findAll(){
        return appointmentRepository.findAll();
    }

    @Transactional(readOnly = false)
    public Appointment save(Appointment appointment){
        if(checkIfAppointmentIsBusy(appointment)){
            return appointmentRepository.save(appointment);
        }
        else return null;
    }

    public void remove(Integer id) {
        appointmentRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public boolean checkIfAppointmentIsBusy(Appointment appointment){
        Set<Appointment> appointments  = appointmentRepository.findAllByCompanyId(appointment.getCompany().getId());
        boolean isFree = true;

        for(Appointment appoint : appointments){
            //Pocetak termina izmedju pocetka i kraja postojeceg trermina
            if((appointment.getDateAndTime().isAfter(appoint.getDateAndTime()) || appointment.getDateAndTime().isEqual(appoint.getDateAndTime()) )&& appointment.getDateAndTime().isBefore(appoint.getDateAndTime().plusMinutes(appoint.getDuration()))){
                isFree =  false;
            }
            //kraj termina izmedju pocetka i kraja postojeceg termina
            else if(appointment.getDateAndTime().plusMinutes(appointment.getDuration()).isAfter(appoint.getDateAndTime()) && (appointment.getDateAndTime().plusMinutes(appointment.getDuration()).isBefore(appoint.getDateAndTime().plusMinutes(appoint.getDuration())) || appointment.getDateAndTime().plusMinutes(appointment.getDuration()).isEqual(appoint.getDateAndTime().plusMinutes(appoint.getDuration())))){
                isFree = false;
            }
            //Postojeci termin izmedju pocetka i kraja novog termina
            else if((appointment.getDateAndTime().isBefore(appoint.getDateAndTime()) || appointment.getDateAndTime().isEqual(appoint.getDateAndTime())) && appointment.getDateAndTime().plusMinutes(appointment.getDuration()).isAfter(appoint.getDateAndTime().plusMinutes(appoint.getDuration()))){
                isFree = false;
            }
            else{
                continue;
            }
        }
        return isFree;
    }
}
