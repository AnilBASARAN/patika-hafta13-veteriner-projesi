package dev.patika.veteriner.dto.request.availabledate;

import dev.patika.veteriner.entity.Doctor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AvailableDateRequest {

    private LocalDate availableDate;
    private Doctor doctor;
}
