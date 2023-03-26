package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "ADDRESS")
@SequenceGenerator(name = "generator_seq",
        sequenceName = "address_id_seq",
        allocationSize = 1,
        initialValue = 1)
public class AddressEntity extends BaseEntity {

    @Column(length = 100, nullable = false)
    private String streetName;

    @Column(length = 6)
    private String communeCode;

    @Column(length = 25)
    private String houseNumber;

    @Column(length = 25)
    private String flatNumber;

    @Column(length = 100, nullable = false)
    private String city;

    @Column(nullable = false)
    private boolean isDefault;

    @ManyToOne
    @JoinColumn(name="person_id", referencedColumnName = "id")
    private PersonEntity personEntity;

}
