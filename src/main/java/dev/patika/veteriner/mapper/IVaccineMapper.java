package dev.patika.veteriner.mapper;

import dev.patika.veteriner.dto.request.vaccine.VaccineRequest;
import dev.patika.veteriner.dto.request.vaccine.VaccineWithoutCustomerRequest;
import dev.patika.veteriner.dto.response.VaccineResponse;
import dev.patika.veteriner.entity.Vaccine;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface IVaccineMapper {
    VaccineResponse entityToResponse(Vaccine vaccine);
    List<VaccineResponse> entityToResponse(List<Vaccine> vaccineList);
    Vaccine requestToEntity(VaccineWithoutCustomerRequest vaccineWithoutCustomerRequest);

    Vaccine requestToEntity(VaccineRequest vaccineRequest);

}
