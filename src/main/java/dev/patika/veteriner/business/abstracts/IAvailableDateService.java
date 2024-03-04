package dev.patika.veteriner.business.abstracts;

import dev.patika.veteriner.dto.request.availabledate.AvailableDateRequest;
import dev.patika.veteriner.entity.AvailableDate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface IAvailableDateService {
    List<AvailableDate> findAll();
    AvailableDate findById (Long id);

    AvailableDate create(AvailableDateRequest availableDateRequest);

    AvailableDate update(Long id, AvailableDateRequest availableDateRequest);


    public String delete(Long id);

    Optional<AvailableDate> findByDoctorIdAndDate(Long id, LocalDate localDate);


}
