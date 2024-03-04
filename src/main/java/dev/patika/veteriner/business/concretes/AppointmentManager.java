package dev.patika.veteriner.business.concretes;

import dev.patika.veteriner.business.abstracts.IAppointmentService;
import dev.patika.veteriner.business.abstracts.IAvailableDateService;
import dev.patika.veteriner.dao.IAppointmentRepo;
import dev.patika.veteriner.dto.request.appointment.AppointmentRequest;
import dev.patika.veteriner.entity.Animal;
import dev.patika.veteriner.entity.Appointment;
import dev.patika.veteriner.entity.AvailableDate;
import dev.patika.veteriner.mapper.IAppointmentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentManager implements IAppointmentService {

    private final IAppointmentRepo appointmentRepo;
    private final IAvailableDateService availableDateService;
    private final IAppointmentMapper appointmentMapper;

    public List<Appointment> findAll() {
        return appointmentRepo.findAll();
    }

    public Appointment findById (Long id){
        return appointmentRepo.findById(id).orElseThrow(() -> new RuntimeException("id:" + id + "Appointment could not found!!!"));
    }

    public List<Appointment> findByDoctorIdAndDateRange(Long doctorId, LocalDate startDate, LocalDate endDate) {
        if (doctorId==null){
            return appointmentRepo.findByDateBetween(startDate.atStartOfDay(),endDate.atStartOfDay());
        }
        return appointmentRepo.findByDoctorIdAndDateBetween(doctorId,startDate.atStartOfDay(),endDate.atStartOfDay());
    }

    public List<Appointment> findByAnimalIdAndDateRange(Long animalId, LocalDate startDate, LocalDate endDate) {
        if (animalId==null){
            return appointmentRepo.findByDateBetween(startDate.atStartOfDay(),endDate.atStartOfDay());
        }
        return appointmentRepo.findByAnimalIdAndDateBetween(animalId,startDate.atStartOfDay(),endDate.atStartOfDay());
    }

    public Appointment create(AppointmentRequest appointmentRequest){

        Optional<Appointment> existAppointmentWithSameSpecs =
                appointmentRepo.findByDateAndDoctorIdAndAnimalId(appointmentRequest.getDate(), appointmentRequest.getDoctor().getId(), appointmentRequest.getAnimal().getId());

        Optional<AvailableDate> existsAvailableDateByDoctorIdAndDate =
                availableDateService.findByDoctorIdAndDate(appointmentRequest.getDoctor().getId(), appointmentRequest.getDate().toLocalDate());

        Optional<Appointment> existAppointmentWithDateAndDoctorId =
                appointmentRepo.findByDateAndDoctorId(appointmentRequest.getDate(), appointmentRequest.getDoctor().getId());

        if (existAppointmentWithSameSpecs.isPresent()){
            throw new RuntimeException("The Appointment has already been saved.");
        }

        if (existsAvailableDateByDoctorIdAndDate.isEmpty()){
            throw new RuntimeException("The Doctor doesn't work this day.");
        }

        if (!existAppointmentWithDateAndDoctorId.isEmpty()){
            throw new RuntimeException("The doctor has another appointment.");
        }

        //Appointment newAppointment = modelMapper.map(appointmentRequestDto, Appointment.class);
        Appointment newAppointment = this.appointmentMapper.requestToEntity(appointmentRequest);
        return appointmentRepo.save(newAppointment);
    }

    public Appointment update(Long id, AppointmentRequest appointmentRequest){

        Optional<Appointment> appointmentFromDb = appointmentRepo.findById(id);

        if (appointmentFromDb.isEmpty()){
            throw new RuntimeException("id:" + id + " Appointment could not found!!!");
        }

        Optional<Appointment> existAppointmentWithSameSpecs =
                appointmentRepo.findByDateAndDoctorIdAndAnimalId(appointmentRequest.getDate(), appointmentRequest.getDoctor().getId(), appointmentRequest.getAnimal().getId());

        if (!existAppointmentWithSameSpecs.isEmpty()){
            throw new RuntimeException("\"This Appointment has already been registered. That's why this request causes duplicate data\"");
        }

        Optional<AvailableDate> existsAvailableDateByDoctorIdAndDate =
                availableDateService.findByDoctorIdAndDate(appointmentRequest.getDoctor().getId(), appointmentRequest.getDate().toLocalDate());

        if (existsAvailableDateByDoctorIdAndDate.isEmpty()){
            throw new RuntimeException("The Doctor doesn't work this day.");
        }

        Optional<Appointment> existAppointmentWithDateAndDoctorId =
                appointmentRepo.findByDateAndDoctorId(appointmentRequest.getDate(), appointmentRequest.getDoctor().getId());

        if (!existAppointmentWithDateAndDoctorId.isEmpty()){
            throw new RuntimeException("The doctor has another appointment.");
        }

        //Appointment updatedAppointment = appointmentFromDb.get();
        Appointment updatedAppointment = this.appointmentMapper.requestToEntity(appointmentRequest);
        updatedAppointment.setId(id);


        return appointmentRepo.save(updatedAppointment);
    }

    public String delete(Long id){
        Optional<Appointment> appointmentFromDb = appointmentRepo.findById(id);

        if (appointmentFromDb.isEmpty()){
            throw new RuntimeException("This vaccine could not found!!!");
        }
        else {
            appointmentRepo.delete(appointmentFromDb.get());
            return "Appointment deleted.";
        }
    }


}