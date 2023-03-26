package com.example.demo.service;

import com.example.demo.model.dtos.PersonDTO;

import java.util.List;

public interface PersonService {

    PersonDTO getPersonById(Long id);

    List<PersonDTO> getAllPersons();

    PersonDTO addPerson(PersonDTO personDTO);

    PersonDTO updatePerson(Long id, PersonDTO personDTO);

    void deletePerson(Long id);

}
