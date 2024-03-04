package dev.patika.veteriner.mapper;

import dev.patika.veteriner.dto.request.appointment.AppointmentRequest;
import dev.patika.veteriner.dto.response.AppointmentResponse;
import dev.patika.veteriner.entity.Animal;
import dev.patika.veteriner.entity.Appointment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface IAppointmentMapper {
    AppointmentResponse entityToResponse(Appointment appointment);
    List<AppointmentResponse> entityToResponse(List<Appointment> appointmentList);
    Appointment requestToEntity(AppointmentRequest appointmentRequest);

}
