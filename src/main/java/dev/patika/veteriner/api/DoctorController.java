package dev.patika.veteriner.api;


import dev.patika.veteriner.business.abstracts.IDoctorService;
import dev.patika.veteriner.dto.request.doctor.DoctorRequest;
import dev.patika.veteriner.entity.Doctor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctors")
@RequiredArgsConstructor
public class DoctorController {

    public final IDoctorService doctorService;

    @GetMapping
    public List<Doctor> findAll(){
        return doctorService.findAll();
    }

    @GetMapping("/{id}")
    public Doctor findById (@PathVariable Long id){
        return doctorService.findById(id);
    }

    @PostMapping
    public Doctor create(@RequestBody DoctorRequest doctorRequest){
        return doctorService.create(doctorRequest);
    }

    @PutMapping("/{id}")
    public Doctor update(@PathVariable Long id, @RequestBody DoctorRequest doctorRequest){
        return doctorService.update(id,doctorRequest);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        return doctorService.delete(id);
    }
}