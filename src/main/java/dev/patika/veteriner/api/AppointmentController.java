package dev.patika.veteriner.api;


import dev.patika.veteriner.business.abstracts.IAppointmentService;
import dev.patika.veteriner.dto.request.appointment.AppointmentRequest;
import dev.patika.veteriner.entity.Appointment;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final IAppointmentService appointmentService;

    @GetMapping
    public ResponseEntity<List<Appointment>> findAll(){
        List<Appointment> appointmentList = appointmentService.findAll();

        return ResponseEntity.ok().body(appointmentList);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Appointment> findById (@PathVariable Long id){
        Appointment appointment = appointmentService.findById(id);
        if (appointment != null){
            return ResponseEntity.ok().body(appointment);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/searchByDoctorAndDateRange")
    public ResponseEntity<List<Appointment>> findByDoctorIdAndDateRange (@RequestParam(value = "id", required = false) Long id, @RequestParam LocalDate startDate, @RequestParam LocalDate endDate){
        List<Appointment> appointmentListSearchByDoctorAndDateRange = appointmentService.findByDoctorIdAndDateRange(id, startDate, endDate);
        if (appointmentListSearchByDoctorAndDateRange != null){
            return ResponseEntity.ok().body(appointmentListSearchByDoctorAndDateRange);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/searchByAnimalAndDateRange")
    public ResponseEntity<List<Appointment>> findByAnimalIdAndDateRange (@RequestParam(value = "id", required = false) Long id,@RequestParam LocalDate startDate,@RequestParam LocalDate endDate){
        List<Appointment> appointmentListSearchByAnimalAndDateRange = appointmentService.findByAnimalIdAndDateRange(id, startDate, endDate);
        if (appointmentListSearchByAnimalAndDateRange != null){
            return ResponseEntity.ok().body(appointmentListSearchByAnimalAndDateRange);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Appointment> create(@RequestBody AppointmentRequest appointmentRequest){
        Appointment createdAppointment = appointmentService.create(appointmentRequest);
        if (createdAppointment != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(createdAppointment);
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Appointment> update(@PathVariable Long id, @RequestBody AppointmentRequest appointmentRequest){
        Appointment updatedAppointment = appointmentService.update(id,appointmentRequest);
        if (updatedAppointment != null){
            return ResponseEntity.status(HttpStatus.OK).body(updatedAppointment);
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        return appointmentService.delete(id);
    }


}

