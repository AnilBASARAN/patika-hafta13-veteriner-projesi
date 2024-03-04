package dev.patika.veteriner.business.concretes;

import dev.patika.veteriner.business.abstracts.IDoctorService;
import dev.patika.veteriner.dao.IDoctorRepo;
import dev.patika.veteriner.dto.request.doctor.DoctorRequest;
import dev.patika.veteriner.entity.Doctor;
import dev.patika.veteriner.mapper.IDoctorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorManager implements IDoctorService {

    private final IDoctorRepo doctorRepo;
    //private final ModelMapper modelMapper;
    private final IDoctorMapper doctorMapper;

    public List<Doctor> findAll(){
        return doctorRepo.findAll();
    }

    public Doctor findById (Long id){
        return doctorRepo.findById(id).orElseThrow(() -> new RuntimeException("id:" + id + "Doctor could not found!!!"));
    }

    public Doctor create(DoctorRequest doctorRequest){
        Optional<Doctor> existDoctorWithSameSpecs = this.doctorRepo.findByNameAndEmailAndPhoneAndAddressAndCity(doctorRequest.getName(), doctorRequest.getEmail(),doctorRequest.getPhone(),doctorRequest.getAddress(),doctorRequest.getCity());

        if (existDoctorWithSameSpecs.isPresent()){
            throw new RuntimeException("The doctor has already been saved.");
        }
        Doctor newDoctor = this.doctorMapper.requestToEntity(doctorRequest);
         this.doctorRepo.save(newDoctor);
        return newDoctor;
    }

    public Doctor update(Long id, DoctorRequest doctorRequest){
        Optional<Doctor> doctorFromDb = this.doctorRepo.findById(id);
        Optional<Doctor> existOtherDoctorFromRequest = this.doctorRepo.findByNameAndEmailAndPhoneAndAddressAndCity(doctorRequest.getName(), doctorRequest.getEmail(),doctorRequest.getPhone(),doctorRequest.getAddress(),doctorRequest.getCity());

        if (doctorFromDb.isEmpty()){
            throw new RuntimeException("id:" + id + "Doctor could not found!!!");
        }

        if (existOtherDoctorFromRequest.isPresent() && !existOtherDoctorFromRequest.get().getId().equals(id)){
            throw new RuntimeException("This doctor has already been registered. That's why this request causes duplicate data");
        }

        //Doctor updatedDoctor = doctorFromDb.get();
        //modelMapper.map(doctorRequest, updatedDoctor); // DoctorRequestDto -> Doctor

        Doctor updatedDoctor = this.doctorMapper.requestToEntity(doctorRequest);
        updatedDoctor.setId(id);


        return doctorRepo.save(updatedDoctor);
    }

    public String delete(Long id){
        Optional<Doctor> doctorFromDb = doctorRepo.findById(id);
        if (doctorFromDb.isEmpty()){
            throw new RuntimeException("This doctor could not found!!!");
        }
        else {
            doctorRepo.delete(doctorFromDb.get());
            return "Doctor deleted.";
        }
    }
}