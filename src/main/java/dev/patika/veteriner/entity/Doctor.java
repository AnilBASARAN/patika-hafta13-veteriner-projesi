package dev.patika.veteriner.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "doctor_table")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id",columnDefinition = "serial")
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String city;

    @OneToMany (mappedBy = "doctor", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<AvailableDate> availableDates;

    @OneToMany (mappedBy = "doctor")
    @JsonIgnore
    private List<Appointment> appointments;


}
