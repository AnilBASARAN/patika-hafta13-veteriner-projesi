package dev.patika.veteriner.api;

import dev.patika.veteriner.business.abstracts.IAvailableDateService;
import dev.patika.veteriner.dto.request.availabledate.AvailableDateRequest;
import dev.patika.veteriner.entity.AvailableDate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/available-dates")
@RequiredArgsConstructor
public class AvailableDateController {

    public final IAvailableDateService availableDateService;

    @GetMapping
    public List<AvailableDate> findAll(){
        return availableDateService.findAll();
    }

    @GetMapping("/{id}")
    public AvailableDate findById (@PathVariable Long id){
        return availableDateService.findById(id);
    }

    @PostMapping
    public AvailableDate create(@RequestBody AvailableDateRequest availableDateRequest){
        return availableDateService.create(availableDateRequest);
    }

    @PutMapping("/{id}")
    public AvailableDate update(@PathVariable Long id, @RequestBody AvailableDateRequest availableDateRequest){
        return availableDateService.update(id,availableDateRequest);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        return availableDateService.delete(id);
    }
}