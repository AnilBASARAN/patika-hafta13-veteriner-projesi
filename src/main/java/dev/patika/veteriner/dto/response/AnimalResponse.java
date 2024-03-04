package dev.patika.veteriner.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
@AllArgsConstructor
public class AnimalResponse {
    private Long id;
    private String name;

}
