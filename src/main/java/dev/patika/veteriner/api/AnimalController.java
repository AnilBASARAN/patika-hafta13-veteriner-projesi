package dev.patika.veteriner.api;


import dev.patika.veteriner.business.abstracts.IAnimalService;
import dev.patika.veteriner.dto.request.animal.AnimalRequest;
import dev.patika.veteriner.entity.Animal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/animal")
@RequiredArgsConstructor
public class AnimalController {

    private final IAnimalService animalService;

    @GetMapping
    public ResponseEntity<List<Animal>> findAll(){
        List<Animal> animalList = animalService.findAll();
        return ResponseEntity.ok().body(animalList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Animal> findById (@PathVariable Long id){
        Animal animal = animalService.findById(id);
        if (animal != null){
            return ResponseEntity.ok().body(animal);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/searchByName")
    public ResponseEntity<List<Animal>> findByName (@RequestParam String name){
        List<Animal> animalListSearchByName = animalService.findByName(name);
        return ResponseEntity.ok().body(animalListSearchByName);
    }

    @GetMapping("/searchByCustomer")
    public ResponseEntity<List<Animal>> findByCustomer (@RequestParam Long id){
        List<Animal> animalListSearchByCustomer = animalService.findByCustomer(id);
        return ResponseEntity.ok().body(animalListSearchByCustomer);
    }

    @PostMapping
    public ResponseEntity<Animal> create(@RequestBody AnimalRequest animalRequest){
        Animal createdAnimal = animalService.create(animalRequest);
        if (createdAnimal != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(createdAnimal);
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Animal> update(@PathVariable Long id, @RequestBody AnimalRequest animalRequest){
        Animal updatedAnimal = animalService.update(id,animalRequest);
        if (updatedAnimal != null){
            return ResponseEntity.status(HttpStatus.OK).body(updatedAnimal);
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        return animalService.delete(id);
    }

}
