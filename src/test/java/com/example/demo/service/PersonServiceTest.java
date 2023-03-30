package com.example.demo.service;

import com.example.demo.model.PersonEntity;
import com.example.demo.model.dtos.PersonDTO;
import com.example.demo.model.mappers.PersonMapper;
import com.example.demo.repository.PersonRepository;
import com.example.demo.service.impl.PersonServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @Mock
    PersonRepository personRepository;

    @Mock
    PersonMapper personMapper;

    @InjectMocks
    PersonServiceImpl personService;




    @Test
    @DisplayName("Should return that size of list equals 3")
    public void getPersons(){


        Mockito.when(personRepository.findAll()).thenReturn(prepareMockData());

        List<PersonDTO> result = personService.getAllPersons();

        //then
        Assertions.assertTrue(result.size() == 3);
    }



//    @Test
//    @DisplayName("Should save person to list")
//    public void testSaveUser() {
//
//        //given
//        PersonEntity person = PersonEntity.builder()
//                .firstName("Marek")
//                .lastName("Nowy")
//                .birthDate(LocalDate.of(2000, 1,1))
//                .build();
//
//        Mockito.when(personRepository.save(person)).thenReturn(person);
//
//
//        PersonDTO returnedPerson = personService.addPerson(PersonDTO.builder()
//                .firstName("Marek")
//                .lastName("Nowy")
//                .birthDate(LocalDate.of(2000, 1,1))
//                .build());
//
//        assertAll(
//                ()-> assertEquals(person.getFirstName(),returnedPerson.getFirstName()),
//                ()-> assertEquals(person.getLastName(),returnedPerson.getLastName()),
//                ()-> assertEquals(person.getBirthDate(),returnedPerson.getBirthDate())
//        );
//    }


    @Test
    @DisplayName("Should delete user by id")
    public void testDeleteUserById() {

        Mockito.doNothing().when(personRepository).deleteById(1L);

        personService.deletePerson(1L);
        Mockito.verify(personRepository, Mockito.times(1)).deleteById(1L);
    }


    private List<PersonEntity> prepareMockData(){
        return List.of(
                PersonEntity.builder()
                        .firstName("Matesz")
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


}