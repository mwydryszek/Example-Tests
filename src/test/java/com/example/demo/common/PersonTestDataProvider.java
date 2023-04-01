package com.example.demo.common;

import com.example.demo.model.PersonEntity;
import com.example.demo.model.dtos.PersonDTO;
import com.example.demo.model.dtos.PersonListResponse;

import java.time.LocalDate;
import java.util.List;

public class PersonTestDataProvider {

    public static List<PersonEntity> prepareMockData(){
        return List.of(
                PersonEntity.builder()
                        .firstName("Mateusz")
                        .lastName("Wydryszek")
                        .birthDate(LocalDate.of(2010, 1,1))
                        .build(),
                PersonEntity.builder()
                        .firstName("Jan")
                        .lastName("Kowalski")
                        .birthDate(LocalDate.of(2009, 1,1))
                        .build(),
                PersonEntity.builder()
                        .firstName("Piotr")
                        .lastName("Nowak")
                        .birthDate(LocalDate.of(2008, 1,1))
                        .build());
    }

    public static PersonListResponse preparePersonListResponse() {
        return new PersonListResponse(
                List.of(
                PersonDTO.builder()
                        .firstName("Mateusz")
                        .lastName("Wydryszek")
                        .birthDate(LocalDate.of(2010, 1,1))
                        .build(),
                PersonDTO.builder()
                        .firstName("Jan")
                        .lastName("Kowalski")
                        .birthDate(LocalDate.of(2009, 1,1))
                        .build(),
                PersonDTO.builder()
                        .firstName("Piotr")
                        .lastName("Nowak")
                        .birthDate(LocalDate.of(2008, 1,1))
                        .build())
        );
    }
}
