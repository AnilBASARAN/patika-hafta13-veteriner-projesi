package dev.patika.veteriner.business.concretes;

import dev.patika.veteriner.business.abstracts.IAvailableDateService;
import dev.patika.veteriner.dao.IAvailableDateRepo;
import dev.patika.veteriner.dto.request.availabledate.AvailableDateRequest;
import dev.patika.veteriner.entity.Appointment;
import dev.patika.veteriner.entity.AvailableDate;
import dev.patika.veteriner.mapper.IAvailableDateMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AvailableDateManager implements IAvailableDateService {

    private final IAvailableDateRepo availableDateRepo;
    private final IAvailableDateMapper availableDateMapper;
    //private final ModelMapper modelMapper;

    public List<AvailableDate> findAll(){
        return availableDateRepo.findAll();
    }

    public AvailableDate findById (Long id){
        return availableDateRepo.findById(id).orElseThrow(() -> new RuntimeException("id:" + id + "Available Date could not found!!!"));
    }

    public AvailableDate create(AvailableDateRequest availableDateRequest){
        Optional<AvailableDate> existAvailableDateWithSameSpecs = availableDateRepo.findAvailableDateAndDoctorIdOrDoctorEmail(availableDateRequest.getAvailableDate()
                ,availableDateRequest.getDoctor().getId(), availableDateRequest.getDoctor().getEmail());

        if (existAvailableDateWithSameSpecs.isPresent()){
            throw new RuntimeException("The available Date has already been saved.");
        }

        AvailableDate newAvailableDate = this.availableDateMapper.requestToEntity(availableDateRequest);
        System.out.println(newAvailableDate.toString());
        return availableDateRepo.save(newAvailableDate);
    }

    public AvailableDate update(Long id, AvailableDateRequest availableDateRequest){
        Optional<AvailableDate> availableDateFromDb = availableDateRepo.findById(id);
        Optional<AvailableDate> existOtherAvailableDateFromRequest = availableDateRepo.findAvailableDateAndDoctorIdOrDoctorEmail(availableDateRequest.getAvailableDate()
                ,availableDateRequest.getDoctor().getId(), availableDateRequest.getDoctor().getEmail());

        if (availableDateFromDb.isEmpty()){
            throw new RuntimeException("id:" + id + "available date could not found!!!");
        }

        if (existOtherAvailableDateFromRequest.isPresent() && !existOtherAvailableDateFromRequest.get().getId().equals(id)){
            throw new RuntimeException("This available date has already been registered. That's why this request causes duplicate data");
        }

        //AvailableDate updatedAvailableDate = availableDateFromDb.get();
        //modelMapper.map(availableDateRequest, updatedAvailableDate); // AvailableDateRequestDto -> AvailableDate
        AvailableDate updatedAvailableDate = this.availableDateMapper.requestToEntity(availableDateRequest);
        updatedAvailableDate.setId(id);

        return availableDateRepo.save(updatedAvailableDate);
    }

    public String delete(Long id){
        Optional<AvailableDate> availableDateFromDb = availableDateRepo.findById(id);

        if (availableDateFromDb.isEmpty()){
            throw new RuntimeException("This available date with id :" + id + " could not found!!!");
        }
        else {
            availableDateRepo.delete(availableDateFromDb.get());
            return "Available date deleted.";
        }
    }

    public Optional<AvailableDate> findByDoctorIdAndDate(Long id, LocalDate localDate) {
        return availableDateRepo.findByDoctorIdAndAvailableDate(id, localDate);
    }
}
