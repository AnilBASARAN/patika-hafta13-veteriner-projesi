package dev.patika.veteriner.business.concretes;

import dev.patika.veteriner.business.abstracts.IVaccineService;
import dev.patika.veteriner.dao.IVaccineRepo;
import dev.patika.veteriner.dto.request.vaccine.VaccineRequest;
import dev.patika.veteriner.dto.request.vaccine.VaccineWithoutCustomerRequest;
import dev.patika.veteriner.entity.Vaccine;
import dev.patika.veteriner.mapper.IVaccineMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VaccineManager implements IVaccineService {

    private final IVaccineRepo vaccineRepo;
    private final IVaccineMapper vaccineMapper;
    //private final ModelMapper modelMapper;

    public List<Vaccine> findAll(){
        return vaccineRepo.findAll();
    }

    public Vaccine findById (Long id){
        return vaccineRepo.findById(id).orElseThrow(() -> new RuntimeException("id:" + id + "Vaccine could not found!!!"));
    }

    public List<Vaccine> findByAnimal(Long id) {
        return vaccineRepo.findByAnimalId(id);
    }

    public List<Vaccine> findByVaccineProtectionFinishDateRange(LocalDate startDate, LocalDate endDate) {
        return vaccineRepo.findByProtectionFinishDateBetween(startDate,endDate);
    }

//////////////////
/*    public Doctor create(DoctorRequest doctorRequest){
        Optional<Doctor> existDoctorWithSameSpecs = doctorRepo.findByNameAndEmailAndPhoneAndAdress(doctorRequest.getName(), doctorRequest.getEmail(),doctorRequest.getPhone(),doctorRequest.getAddress());

        if (existDoctorWithSameSpecs.isPresent()){
            throw new RuntimeException("The doctor has already been saved.");
        }
        Doctor newDoctor = this.doctorMapper.requestToEntity(doctorRequest);
        return doctorRepo.save(newDoctor);
    }*/
///////////////
    public Vaccine create(VaccineWithoutCustomerRequest vaccineWithoutCustomerRequest){
        Optional<Vaccine> vaccineExist  =
                this.vaccineRepo.findByNameAndCodeAndAnimalIdAndProtectionFinishDateGreaterThanEqual(vaccineWithoutCustomerRequest.getName(), vaccineWithoutCustomerRequest.getCode(),vaccineWithoutCustomerRequest.getAnimal2Request().getId(), vaccineWithoutCustomerRequest.getProtectionStartDate());

        if (vaccineExist.isPresent()){
            throw new RuntimeException("The vaccine you want to save is still protective for this animal.");
        }

        //Vaccine newVaccine = modelMapper.map(vaccineWithoutCustomerRequestDto, Vaccine.class);
        Vaccine newVaccine = this.vaccineMapper.requestToEntity(vaccineWithoutCustomerRequest);

        return vaccineRepo.save(newVaccine);
    }

    ////////////////////
    public Vaccine update(Long id, VaccineRequest vaccineRequest){
        Optional<Vaccine> vaccineFromDb = vaccineRepo.findById(id);
        Optional<Vaccine> existOtherValidVaccineFromRequest =
                vaccineRepo.findByNameAndCodeAndAnimalIdAndProtectionFinishDateGreaterThanEqual(vaccineRequest.getName(), vaccineRequest.getCode(),vaccineRequest.getAnimal().getId(), vaccineRequest.getProtectionStartDate());

        if (vaccineFromDb.isEmpty()){
            throw new RuntimeException("id:" + id + "Vaccine could not found!!!");
        }

        if (existOtherValidVaccineFromRequest.isPresent() && this.vaccineRepo.findById(id).isPresent()){
            throw new RuntimeException("This Vaccine has already been registered. That's why this request causes duplicate data");
        }

        if (!existOtherValidVaccineFromRequest.isEmpty()){
            throw new RuntimeException("The vaccine you want to update is still protective for this animal.");
        }

        //Vaccine updatedVaccine = vaccineFromDb.get();
        //modelMapper.map(vaccineWithoutCustomerRequestDto, updatedVaccine); // VaccineRequestDto -> Vaccine

        Vaccine updatedVaccine = this.vaccineMapper.requestToEntity(vaccineRequest);
        updatedVaccine.setId(id);
        return vaccineRepo.save(updatedVaccine);
    }

    public String delete(Long id){
        Optional<Vaccine> vaccineFromDb = vaccineRepo.findById(id);
        if (vaccineFromDb.isEmpty()){
            throw new RuntimeException("This vaccine could not found!!!");
        }
        else {
            vaccineRepo.delete(vaccineFromDb.get());
            return "Vaccine deleted.";
        }
    }
}