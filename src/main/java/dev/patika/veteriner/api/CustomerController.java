package dev.patika.veteriner.api;

import dev.patika.veteriner.business.abstracts.ICustomerService;
import dev.patika.veteriner.dto.request.customer.CustomerRequest;
import dev.patika.veteriner.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    public final ICustomerService customerService;

    @GetMapping
    public List<Customer> findAll(){
        return customerService.findAll();
    }

    @GetMapping("/{id}")
    public Customer findById (@PathVariable Long id){
        return customerService.findById(id);
    }

    @GetMapping("/searchByName")
    public List<Customer> findByName (@RequestParam String name){
        return customerService.findByName(name);
    }

    @PostMapping
    public Customer create(@RequestBody CustomerRequest customerRequest){
        return customerService.create(customerRequest);
    }

    @PutMapping("/{id}")
    public Customer update(@PathVariable Long id, @RequestBody CustomerRequest customerRequest){
        return customerService.update(id,customerRequest);
    }

    @DeleteMapping("/{id}")
    public String deleteCustomer(@PathVariable Long id){
        return customerService.delete(id);
    }

}