package com.medicines.distribution.controller;

import com.medicines.distribution.dto.AppointmentDTO;
import com.medicines.distribution.dto.EquipmentDTO;
import com.medicines.distribution.model.Appointment;
import com.medicines.distribution.model.Equipment;
import com.medicines.distribution.repository.Appointmentrepository;
import com.medicines.distribution.service.AppointmentService;
import com.medicines.distribution.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "api/appointments")
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;
    @Autowired
    CompanyService companyService;



    @PostMapping(consumes = "application/json")
    public ResponseEntity<AppointmentDTO> saveAppointment(@RequestBody AppointmentDTO appointmentDTO){

        Appointment appointment =  new Appointment();
        appointment.setDateAndTime(appointmentDTO.getDateAndTime());
        appointment.setDuration(appointmentDTO.getDuration());
        appointment.setFree(appointmentDTO.isFree());
        appointment.setAdministratorName(appointmentDTO.getAdministratorName());
        appointment.setAdministratorSurname(appointmentDTO.getAdministratorSurname());
        appointment.setAdminUserId(appointmentDTO.getAdminUserId());
        appointment.setCompany(companyService.findOne(appointmentDTO.getCompanyId()));

        appointment = appointmentService.save(appointment);

        return new ResponseEntity<>(new AppointmentDTO(appointment), HttpStatus.CREATED);
    }

    @PostMapping(value = "/check-admin-free",consumes = "application/json")
    public ResponseEntity<Boolean> checkIfAdminIsFree(@RequestBody AppointmentDTO appointmentDTO){

        Appointment appointment =  new Appointment();
        appointment.setDateAndTime(appointmentDTO.getDateAndTime());
        appointment.setDuration(appointmentDTO.getDuration());
        appointment.setFree(appointmentDTO.isFree());
        appointment.setAdministratorName(appointmentDTO.getAdministratorName());
        appointment.setAdministratorSurname(appointmentDTO.getAdministratorSurname());
        appointment.setAdminUserId(appointmentDTO.getAdminUserId());
        appointment.setCompany(companyService.findOne(appointmentDTO.getCompanyId()));

        boolean isFree = appointmentService.checkIfAdminIsFree(appointment,appointmentDTO.getAdminUserId());
        return new ResponseEntity<Boolean>(isFree, HttpStatus.CREATED);
    }
}
