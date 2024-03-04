package dev.patika.veteriner.dto.request.appointment;

import dev.patika.veteriner.entity.Animal;
import dev.patika.veteriner.entity.Doctor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppointmentRequest {

    private LocalDateTime date;
    private Doctor doctor;
    private Animal animal;
}
