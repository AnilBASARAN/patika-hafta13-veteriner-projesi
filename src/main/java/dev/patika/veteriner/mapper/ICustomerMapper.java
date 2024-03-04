package dev.patika.veteriner.mapper;

import dev.patika.veteriner.dto.request.customer.CustomerRequest;
import dev.patika.veteriner.dto.response.CustomerResponse;
import dev.patika.veteriner.entity.Customer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ICustomerMapper {
    CustomerResponse entityToResponse(Customer customer);
    List<CustomerResponse> entityToResponse(List<Customer> customerList);
    Customer requestToEntity(CustomerRequest customerRequest);

}
