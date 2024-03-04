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
@Table(name = "customer_table")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id",columnDefinition = "serial")
    private Long id;
    private String name;
    private String phone;
    private String mail;
    private String address;
    private String city;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Animal> animals ;
}
