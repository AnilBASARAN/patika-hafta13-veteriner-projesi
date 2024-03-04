package dev.patika.veteriner.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "appointment_table")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id",columnDefinition = "serial")
    private Long id;


    private LocalDateTime date;

    @ManyToOne (fetch = FetchType.EAGER) //Appointment sildiğimizde veya güncellediğimizde doctoru silmemize yada güncellememize gerek olmadığından cascade yok.
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "animal_id")
    private Animal animal;




}
