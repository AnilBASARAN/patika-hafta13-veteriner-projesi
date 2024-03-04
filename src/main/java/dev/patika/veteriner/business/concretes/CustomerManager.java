package dev.patika.veteriner.business.concretes;

import dev.patika.veteriner.business.abstracts.ICustomerService;
import dev.patika.veteriner.dao.ICustomerRepo;
import dev.patika.veteriner.dto.request.customer.CustomerRequest;
import dev.patika.veteriner.entity.Customer;
import dev.patika.veteriner.mapper.ICustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerManager implements ICustomerService {

    private final ICustomerRepo customerRepo;
    private final ICustomerMapper customerMapper;
    //private final ModelMapper modelMapper;

    public List<Customer> findAll(){
        return customerRepo.findAll();
    }

    public Customer findById (Long id){
        return customerRepo.findById(id).orElseThrow(() -> new RuntimeException("id:" + id + "Customer could not found!!!"));
    }

    public List<Customer> findByName(String name) {
        return customerRepo.findByNameContaining(name);
    }

    public Customer create(CustomerRequest customerRequest){
        Optional<Customer> existCustomerWithSameSpecs = this.customerRepo.findByNameAndMail(customerRequest.getName(), customerRequest.getMail());

        if (existCustomerWithSameSpecs.isPresent()){
            throw new RuntimeException("The Customer has already been saved.");
        }

        Customer newCustomer = this.customerMapper.requestToEntity(customerRequest);
        return customerRepo.save(newCustomer);
    }

    public Customer update(Long id, CustomerRequest customerRequest){
        Optional<Customer> customerFromDb = customerRepo.findById(id);
        Optional<Customer> existOtherCustomerFromRequest = customerRepo.findByNameAndMail(customerRequest.getName(), customerRequest.getMail());

        if (customerFromDb.isEmpty()){
            throw new RuntimeException("id:" + id + "Customer could not found!!!");
        }

        if (existOtherCustomerFromRequest.isPresent() && !existOtherCustomerFromRequest.get().getId().equals(id)){
            throw new RuntimeException("This Customer has already been registered. That's why this request causes duplicate data");
        }

        //Customer updatedCustomer = customerFromDb.get();
        //modelMapper.map(customerRequestDto, updatedCustomer);
        Customer updatedCustomer = this.customerMapper.requestToEntity(customerRequest);
        updatedCustomer.setId(id);
        return this.customerRepo.save(updatedCustomer);
    }


    public String delete(Long id){
        Optional<Customer> customerFromDb = customerRepo.findById(id);
        if (customerFromDb.isEmpty()){
            throw new RuntimeException("This doctor could not found!!!");
        }
        else {
            customerRepo.delete(customerFromDb.get());
            return "Customer deleted.";
        }
    }



}
