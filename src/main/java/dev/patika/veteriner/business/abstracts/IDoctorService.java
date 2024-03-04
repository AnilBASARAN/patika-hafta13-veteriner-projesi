package dev.patika.veteriner.business.abstracts;

import dev.patika.veteriner.dto.request.doctor.DoctorRequest;
import dev.patika.veteriner.entity.Doctor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface IDoctorService {
    List<Doctor> findAll();

    Doctor findById (Long id);

    Doctor create(DoctorRequest doctorRequest);

    Doctor update(Long id, DoctorRequest doctorRequest);

    String delete(Long id);

}
