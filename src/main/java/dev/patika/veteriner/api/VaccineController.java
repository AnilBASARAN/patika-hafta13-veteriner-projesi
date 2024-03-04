package dev.patika.veteriner.api;

import dev.patika.veteriner.business.abstracts.IVaccineService;
import dev.patika.veteriner.dto.request.vaccine.VaccineRequest;
import dev.patika.veteriner.dto.request.vaccine.VaccineWithoutCustomerRequest;
import dev.patika.veteriner.entity.Vaccine;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/vaccines")
@RequiredArgsConstructor
public class VaccineController {

    private final IVaccineService vaccineService;

    @GetMapping
    public ResponseEntity<List<Vaccine>> findAll(){
        List<Vaccine> vaccineList = vaccineService.findAll();

        return ResponseEntity.ok().body(vaccineList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vaccine> findById (@PathVariable Long id){
        Vaccine vaccine = vaccineService.findById(id);
        if (vaccine != null){
            return ResponseEntity.ok().body(vaccine);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/searchByVaccinationRange")
    public ResponseEntity<List<Vaccine>> findByVaccineProtectionFinishDateRange (@RequestParam LocalDate startDate, @RequestParam LocalDate endDate ){
        List<Vaccine> vaccineListSearchByVaccineProtectionFinishDateRange = vaccineService.findByVaccineProtectionFinishDateRange(startDate, endDate);
        return ResponseEntity.ok().body(vaccineListSearchByVaccineProtectionFinishDateRange);
    }
    @GetMapping("/searchByAnimal")
    public ResponseEntity<List<Vaccine>> findByAnimalId (@RequestParam Long id){
        List<Vaccine> vaccineListSearchByAnimal = vaccineService.findByAnimal(id);
        return ResponseEntity.ok().body(vaccineListSearchByAnimal);
    }

    @PostMapping
    public ResponseEntity<Vaccine> create(@RequestBody VaccineWithoutCustomerRequest vaccineWithoutCustomerRequest){

        Vaccine createdVaccine = vaccineService.create(vaccineWithoutCustomerRequest);
        if (createdVaccine != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(createdVaccine);
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vaccine> update(@PathVariable Long id, @RequestBody VaccineRequest vaccineRequest){
        Vaccine updatedVaccine = vaccineService.update(id,vaccineRequest);
        if (updatedVaccine != null){
            return ResponseEntity.status(HttpStatus.OK).body(updatedVaccine);
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        return vaccineService.delete(id);
    }

}