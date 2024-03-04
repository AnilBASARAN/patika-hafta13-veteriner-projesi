package dev.patika.veteriner.dao;

import dev.patika.veteriner.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICustomerRepo extends JpaRepository<Customer, Long> {
    Optional<Customer> findByNameAndMail(String name, String mail);

    List<Customer> findByNameContaining(String name);
}
