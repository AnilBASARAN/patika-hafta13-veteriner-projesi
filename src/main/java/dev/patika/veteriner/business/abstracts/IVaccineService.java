package dev.patika.veteriner.business.abstracts;

import dev.patika.veteriner.dto.request.vaccine.VaccineRequest;
import dev.patika.veteriner.dto.request.vaccine.VaccineWithoutCustomerRequest;
import dev.patika.veteriner.entity.Vaccine;

import java.time.LocalDate;
import java.util.List;

public interface IVaccineService {
    List<Vaccine> findAll();

    Vaccine findById (Long id);

    List<Vaccine> findByAnimal(Long id);

    List<Vaccine> findByVaccineProtectionFinishDateRange(LocalDate startDate, LocalDate endDate);


    Vaccine update(Long id, VaccineRequest vaccineRequest);

    Vaccine create(VaccineWithoutCustomerRequest vaccineWithoutCustomerRequest);
    String delete(Long id);

}
