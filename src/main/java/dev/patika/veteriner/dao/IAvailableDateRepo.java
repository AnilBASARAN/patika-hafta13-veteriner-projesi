package dev.patika.veteriner.dao;

import dev.patika.veteriner.entity.AvailableDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface IAvailableDateRepo extends JpaRepository<AvailableDate, Long> {

    @Query(nativeQuery = true, value =  "SELECT available_date.id, available_date.available_date, available_date.doctor_id \n" +
            "FROM available_date \n" +
            "LEFT JOIN doctor ON doctor.id = available_date.doctor_id \n" +
            "WHERE available_date.available_date = ?1 \n" +
            "AND (available_date.doctor_id = ?2 OR doctor.email = ?3)")
    Optional<AvailableDate> findAvailableDateAndDoctorIdOrDoctorEmail(LocalDate availableDate, Long id, String email);


    Optional<AvailableDate> findByDoctorIdAndAvailableDate(Long id, LocalDate availableDate);
}
