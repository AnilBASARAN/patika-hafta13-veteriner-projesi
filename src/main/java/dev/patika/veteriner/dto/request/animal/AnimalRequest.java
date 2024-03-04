package dev.patika.veteriner.dto.request.animal;

import dev.patika.veteriner.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AnimalRequest {
    private String name;
    private String species;
    private String breed;
    private String gender;
    private LocalDate dateOfBirth;
    private String colour;
    private Customer customer;
}
