package dev.patika.veteriner.business.abstracts;

import dev.patika.veteriner.dto.request.animal.AnimalRequest;
import dev.patika.veteriner.entity.Animal;
import org.springframework.stereotype.Service;
import java.util.List;


public interface IAnimalService {
    List<Animal> findAll ();
    Animal findById (Long id);
    List<Animal> findByName(String name);
    public List<Animal> findByCustomer(Long id);
    Animal create(AnimalRequest animalRequest);
    Animal update (Long id, AnimalRequest animalRequest);
    String delete (Long id);




}
