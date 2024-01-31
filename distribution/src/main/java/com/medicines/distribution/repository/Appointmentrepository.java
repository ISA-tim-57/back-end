package com.medicines.distribution.repository;

import com.medicines.distribution.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Set;

public interface Appointmentrepository extends JpaRepository<Appointment,Integer> {

    Set<Appointment> findAllByAdminUserId(Integer admin_user_id);
    Set<Appointment> findAllByCompanyId(Integer Id);

}
