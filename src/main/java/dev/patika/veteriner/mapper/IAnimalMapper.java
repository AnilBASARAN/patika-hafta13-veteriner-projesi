package dev.patika.veteriner.mapper;

import dev.patika.veteriner.dto.request.animal.AnimalRequest;
import dev.patika.veteriner.dto.response.AnimalResponse;
import dev.patika.veteriner.entity.Animal;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface IAnimalMapper {
    AnimalResponse entityToResponse(Animal animal);
    List<AnimalResponse> entityToResponse(List<Animal> animalList);
    Animal requestToEntity(AnimalRequest animalRequest);

}
