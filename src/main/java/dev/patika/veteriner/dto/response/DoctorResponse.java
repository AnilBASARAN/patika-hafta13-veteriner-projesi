package dev.patika.veteriner.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class DoctorResponse {
    private String name;
    private String email;
    private String address;
    private String city;
    private String phone;
}
