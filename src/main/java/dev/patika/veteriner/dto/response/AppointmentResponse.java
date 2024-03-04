package dev.patika.veteriner.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AppointmentResponse {
    private Long id;
    private LocalDateTime date;
}
