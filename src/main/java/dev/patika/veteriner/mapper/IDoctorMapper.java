package dev.patika.veteriner.mapper;

import dev.patika.veteriner.dto.request.doctor.DoctorRequest;
import dev.patika.veteriner.dto.response.DoctorResponse;
import dev.patika.veteriner.entity.Doctor;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface IDoctorMapper {
    DoctorResponse entityToResponse(Doctor doctor);
    List<DoctorResponse> entityToResponse(List<Doctor> doctorList);
    Doctor requestToEntity(DoctorRequest doctorRequest);

}
