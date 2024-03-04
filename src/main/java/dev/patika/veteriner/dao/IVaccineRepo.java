package dev.patika.veteriner.dao;

import dev.patika.veteriner.entity.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface IVaccineRepo extends JpaRepository<Vaccine, Long> {


    //List<Vaccine> findByNameAndCodeAndProtectionFinishDateGreaterThanEqual(String name, String code, LocalDate protectionStartDate);

    Optional<Vaccine> findByNameAndCodeAndAnimalIdAndProtectionFinishDateGreaterThanEqual(String name, String code, Long id, LocalDate protectionStartDate);

    List<Vaccine> findByAnimalId(Long id);

    List<Vaccine> findByProtectionFinishDateBetween(LocalDate startDate, LocalDate endDate);

/*    List<Vaccine> findByNameAndCodeAndAnimalId();

    List<Vaccine> findByNameAndCodeAndAnimalId(String name, String code, Long animalId);*/
}
