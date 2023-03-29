package com.example.demo.model.mappers;

import com.example.demo.model.AddressEntity;
import com.example.demo.model.PersonEntity;
import com.example.demo.model.dtos.PersonDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonMapperTest {

    @Test
    @DisplayName("Should properly map Person to DTO")
    void mapToDTO(){

        //given
        String expectedFirstName = "Mateusz";
        String expectedLastName = "Wydryszek";
        LocalDate expectedBirthDate = LocalDate.of(2009, 1,1);
        List<AddressEntity> expectedAddresses = List.of(
                AddressEntity.builder()
                        .streetName("Morska")
                        .city("Koszalin")
                        .communeCode("75-123")
                        .houseNumber("1")
                        .flatNumber("20")
                        .isDefault(true)
                    .build(),
                AddressEntity.builder()
                        .streetName("Koszalińska")
                        .city("Koszalin")
                        .communeCode("75-123")
                        .houseNumber("1")
                        .flatNumber("20")
                        .isDefault(false)
                  .build());

        PersonEntity person = PersonEntity.builder()
                .firstName(expectedFirstName)
                .lastName(expectedLastName)
                .birthDate(expectedBirthDate)
                .addresses(expectedAddresses)
                .build();

        //when
        PersonDTO result = PersonMapper.INSTANCE.mapToDTO(person);

        //then
        assertAll(
                () -> assertEquals(expectedFirstName, result.getFirstName()),
                () -> assertEquals(expectedLastName, result.getLastName()),
                () -> assertEquals(expectedBirthDate, result.getBirthDate()),

                // check default address

                () -> assertEquals(expectedAddresses.get(0).getStreetName(), result.getDefaultAddress().getStreetName()),
                () -> assertEquals(expectedAddresses.get(0).getCity(), result.getDefaultAddress().getCity()),
                () -> assertEquals(expectedAddresses.get(0).getCommuneCode(), result.getDefaultAddress().getCommuneCode()),
                () -> assertEquals(expectedAddresses.get(0).getHouseNumber(), result.getDefaultAddress().getHouseNumber()),
                () -> assertEquals(expectedAddresses.get(0).getFlatNumber(), result.getDefaultAddress().getFlatNumber()),
                () -> assertEquals(expectedAddresses.get(0).isDefault(), result.getDefaultAddress().isDefault()),

                // check addresses

                () -> assertEquals(expectedAddresses.get(1).getStreetName(), result.getAddresses().get(0).getStreetName()),
                () -> assertEquals(expectedAddresses.get(1).getCity(), result.getAddresses().get(0).getCity()),
                () -> assertEquals(expectedAddresses.get(1).getCommuneCode(), result.getAddresses().get(0).getCommuneCode()),
                () -> assertEquals(expectedAddresses.get(1).getHouseNumber(), result.getAddresses().get(0).getHouseNumber()),
                () -> assertEquals(expectedAddresses.get(1).getFlatNumber(), result.getAddresses().get(0).getFlatNumber()),
                () -> assertEquals(expectedAddresses.get(1).isDefault(), result.getAddresses().get(0).isDefault()));
    }


    @Test
    @DisplayName("Should properly map DTO to Person")
    void mapToEntity() {


        //given
        String expectedFirstName = "Mateusz";
        String expectedLastName = "Wydryszek";
        LocalDate expectedBirthDate = LocalDate.of(2009, 1,1);


        PersonDTO personDTO = PersonDTO.builder()
                .firstName(expectedFirstName)
                .lastName(expectedLastName)
                .birthDate(expectedBirthDate)
                .build();

        //when
        PersonEntity result = PersonMapper.INSTANCE.mapToEntity(personDTO);

        //then
        assertAll(
                () -> assertEquals(expectedFirstName, result.getFirstName()),
                () -> assertEquals(expectedLastName, result.getLastName()),
                () -> assertEquals(expectedBirthDate, result.getBirthDate()));
    }


}