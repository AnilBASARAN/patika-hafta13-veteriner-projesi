package dev.patika.veteriner.business.concretes;

import dev.patika.veteriner.business.abstracts.IAnimalService;
import dev.patika.veteriner.dao.IAnimalRepo;
import dev.patika.veteriner.dto.request.animal.AnimalRequest;
import dev.patika.veteriner.entity.Animal;
import dev.patika.veteriner.entity.Customer;
import dev.patika.veteriner.mapper.IAnimalMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnimalManager implements IAnimalService {

    private final IAnimalRepo animalRepo;
    private final IAnimalMapper animalMapper;

    public List<Animal> findAll (){
        return animalRepo.findAll();
    }

    public Animal findById (Long id){
        return animalRepo.findById(id).orElseThrow(() -> new RuntimeException("id:" + id + " animal could not found!!!"));
    }
    public List<Animal> findByName(String name) {
        return animalRepo.findByNameContaining(name);
    }
    public List<Animal> findByCustomer(Long id) {
        return animalRepo.findByCustomerId(id);
    }

    public Animal create(AnimalRequest animalRequest){
        Optional<Animal> existAnimalWithSameSpecs = animalRepo.findByNameAndSpeciesAndGenderAndDateOfBirth(animalRequest.getName(),animalRequest.getSpecies(),animalRequest.getGender(),animalRequest.getDateOfBirth());

        if (existAnimalWithSameSpecs.isPresent()){
            throw new RuntimeException("The animal has already been saved.");
        }

        Animal newAnimal = this.animalMapper.requestToEntity(animalRequest);
        return animalRepo.save(newAnimal);
    }

    public Animal update (Long id, AnimalRequest animalRequest){
        Optional<Animal> animalFromDb = animalRepo.findById(id);
        Optional<Animal> existOtherAnimalFromRequest = animalRepo.findByNameAndSpeciesAndGenderAndDateOfBirth(animalRequest.getName(),animalRequest.getSpecies(),animalRequest.getGender(),animalRequest.getDateOfBirth());

        if (animalFromDb.isEmpty()){
            throw new RuntimeException("id:" + id + "Customer could not found!!!");
        }

        if (existOtherAnimalFromRequest.isPresent() && !existOtherAnimalFromRequest.get().getId().equals(id)){
            throw new RuntimeException("This Customer has already been registered. That's why this request causes duplicate data");
        }

        //Animal updatedAnimal = animalFromDb.get();
        Animal updatedAnimal = this.animalMapper.requestToEntity(animalRequest);
        updatedAnimal.setId(id);

        return animalRepo.save(updatedAnimal);
    }

    public String delete (Long id){
        Optional<Animal> animalFromDb = animalRepo.findById(id);
        if (animalFromDb.isEmpty()){
            throw new RuntimeException("This doctor could not found!!!");
        }
        else {
            animalRepo.delete(animalFromDb.get());
            return "Animal deleted.";
        }
    }


}