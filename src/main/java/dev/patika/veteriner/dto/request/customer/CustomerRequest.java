package dev.patika.veteriner.dto.request.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerRequest {

    private String name;
    private String phone;
    private String mail;
    private String address;
    private String city;

}
