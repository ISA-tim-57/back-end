package com.medicines.distribution.repository;

import com.medicines.distribution.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Appointmentrepository extends JpaRepository<Appointment,Integer> {

}
