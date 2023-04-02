package com.example.demo.common;

import com.example.demo.model.AddressEntity;
import com.example.demo.model.PersonEntity;

import java.util.List;

public class AddressTestDataProvider {

    public static List<AddressEntity> prepareMockData(PersonEntity personEntity){
        return List.of(
                AddressEntity.builder()
                        .city("Koszalin")
                        .streetName("Morska")
                        .flatNumber("23")
                        .communeCode("75-100")
                        .isDefault(false)
                        .personEntity(personEntity)
                        .build(),
                AddressEntity.builder()
                        .city("Gdańsk")
                        .streetName("Nowa")
                        .flatNumber("53")
                        .communeCode("23-340")
                        .isDefault(true)
                        .personEntity(personEntity)
                        .build(),
                AddressEntity.builder()
                        .city("Koszalin")
                        .streetName("Zwycięstwa")
                        .flatNumber("123")
                        .communeCode("75-200")
                        .isDefault(false)
                        .personEntity(personEntity)
                        .build());
    }

}
