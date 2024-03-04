package dev.patika.veteriner.dao;

import dev.patika.veteriner.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IDoctorRepo extends JpaRepository<Doctor, Long> {


    Optional<Doctor> findByNameAndEmailAndPhoneAndAddressAndCity(String name, String email, String phone, String address, String city);


}
