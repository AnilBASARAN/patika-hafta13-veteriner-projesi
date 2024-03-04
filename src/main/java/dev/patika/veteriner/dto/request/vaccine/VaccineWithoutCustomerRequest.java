package dev.patika.veteriner.dto.request.vaccine;


import dev.patika.veteriner.dto.request.animal.Animal2Request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VaccineWithoutCustomerRequest {
    private String name;
    private String code;
    private LocalDate protectionStartDate;
    private LocalDate protectionFinishDate;
    private Animal2Request animal2Request;

}
