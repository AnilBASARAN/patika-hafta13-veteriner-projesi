package dev.patika.veteriner.dao;

import dev.patika.veteriner.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface IAnimalRepo extends JpaRepository<Animal, Long> {
    Optional<Animal> findByNameAndSpeciesAndGenderAndDateOfBirth(String name, String species, String gender, LocalDate dateOfBirth);

    List<Animal> findByNameContaining(String name);

    List<Animal> findByCustomerId(Long id);
}
