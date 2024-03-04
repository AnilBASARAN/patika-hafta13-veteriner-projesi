package dev.patika.veteriner.dto.request.doctor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DoctorRequest {

    private String name;
    private String email;
    private String address;
    private String city;
    private String phone;
}
