package dev.patika.veteriner.mapper;

import dev.patika.veteriner.dto.request.appointment.AppointmentRequest;
import dev.patika.veteriner.dto.request.availabledate.AvailableDateRequest;
import dev.patika.veteriner.dto.response.AppointmentResponse;
import dev.patika.veteriner.dto.response.AvailableDateResponse;
import dev.patika.veteriner.entity.Appointment;
import dev.patika.veteriner.entity.AvailableDate;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface IAvailableDateMapper {
    AvailableDateResponse entityToResponse(AvailableDate availableDate);
    List<AvailableDateResponse> entityToResponse(List<AvailableDate> availableDateList);
    AvailableDate requestToEntity(AvailableDateRequest availableDateRequest);

}
