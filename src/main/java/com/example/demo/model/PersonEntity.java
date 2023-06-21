package com.example.demo.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PERSON")
@SequenceGenerator(name = "generator_seq",
        sequenceName = "person_id_seq",
        allocationSize = 1,
        initialValue = 1)
public class PersonEntity extends BaseEntity {
    

    @Column(length = 50, nullable = false)
    private String firstName;
    @Column(length = 50, nullable = false)
    private String lastName;
    @Column(nullable = false)
    private LocalDate birthDate;

    @OneToMany(mappedBy = "personEntity")
    private List<AddressEntity> addresses = new ArrayList<>();

}
