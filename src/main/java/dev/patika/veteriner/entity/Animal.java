package dev.patika.veteriner.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "animal_table")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "animal_id",columnDefinition = "serial")
    private Long id;

    private String name;
    private String species;
    private String breed;
    private String gender;
    private String colour;
    private LocalDate dateOfBirth;



     @OneToMany(mappedBy = "animal", cascade = CascadeType.REMOVE)
     @JsonIgnore
    private List<Vaccine> vaccineList;

    @OneToMany(mappedBy = "animal")
    @JsonIgnore
   private List<Appointment> appointmentList;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")

    private Customer customer;


}
