package dev.patika.veteriner.business.abstracts;

import dev.patika.veteriner.dto.request.appointment.AppointmentRequest;
import dev.patika.veteriner.entity.Appointment;
import dev.patika.veteriner.entity.AvailableDate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IAppointmentService {
    List<Appointment> findAll();

    Appointment findById(Long id);

    List<Appointment> findByDoctorIdAndDateRange(Long doctorId, LocalDate startDate, LocalDate endDate);

    List<Appointment> findByAnimalIdAndDateRange(Long animalId, LocalDate startDate, LocalDate endDate);

    Appointment create(AppointmentRequest appointmentRequest);


    Appointment update(Long id, AppointmentRequest appointmentRequest);


    String delete(Long id);

}
