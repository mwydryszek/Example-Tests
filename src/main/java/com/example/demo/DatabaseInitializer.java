package com.example.demo;

import com.example.demo.model.AddressEntity;
import com.example.demo.model.PersonEntity;
import com.example.demo.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@AllArgsConstructor
@Component
public class DatabaseInitializer {

    private final AddressRepository addressRepository;
    private final PersonRepository personRepository;

    @PostConstruct
    public void initDatabase(){

        PersonEntity personEntity = PersonEntity.builder()
                .firstName("Kamil")
                .lastName("Kowal")
                .birthDate(LocalDate.of(2009, 1,1))
                .build();

        personRepository.save(personEntity);

        AddressEntity addressEntity = AddressEntity.builder()
                .streetName("Morska")
                .city("Koszalin")
                .communeCode("75-123")
                .houseNumber("1")
                .flatNumber("20")
                .isDefault(true)
                .personEntity(personEntity)
                .build();


        AddressEntity addressEntity1 = AddressEntity.builder()
                .streetName("Koszalińska")
                .city("Koszalin")
                .communeCode("75-123")
                .houseNumber("1")
                .flatNumber("20")
                .isDefault(false)
                .personEntity(personEntity)
                .build();


        AddressEntity addressEntity2= AddressEntity.builder()
                .streetName("Nowa")
                .city("Koszalin")
                .communeCode("75-123")
                .houseNumber("1")
                .flatNumber("20")
                .isDefault(false)
                .personEntity(personEntity)
                .build();

        addressRepository.save(addressEntity);
        addressRepository.save(addressEntity1);
        addressRepository.save(addressEntity2);


    }

}
