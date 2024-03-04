package dev.patika.veteriner.business.abstracts;

import dev.patika.veteriner.dto.request.customer.CustomerRequest;
import dev.patika.veteriner.entity.AvailableDate;
import dev.patika.veteriner.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ICustomerService {
    List<Customer> findAll();

    Customer findById (Long id);

    List<Customer> findByName(String name);

    Customer create(CustomerRequest customerRequest);

    Customer update(Long id, CustomerRequest customerRequest);


    String delete(Long id);
}
