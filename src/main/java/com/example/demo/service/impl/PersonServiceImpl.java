package com.example.demo.service.impl;

import com.example.demo.exception.PersonNotFoundException;
import com.example.demo.model.PersonEntity;
import com.example.demo.model.dtos.PersonDTO;
import com.example.demo.model.mappers.PersonMapper;
import com.example.demo.repository.PersonRepository;
import com.example.demo.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    @Override
    public PersonDTO getPersonById(Long id) {
        return personRepository.findById(id).map(personMapper::mapToDTO).orElseThrow(PersonNotFoundException::new);
    }

    @Override
    public List<PersonDTO> getAllPersons() {
        return personRepository.findAll().stream().map(personMapper::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public PersonDTO addPerson(PersonDTO personDTO) {
        PersonEntity person = personRepository.save(personMapper.mapToEntity(personDTO));
        return personMapper.mapToDTO(person);
    }

    @Override
    public PersonDTO updatePerson(Long id, PersonDTO personDTO) {
        personDTO.setId(id);
        PersonEntity person = personRepository.save(personMapper.mapToEntity(personDTO));
        return personMapper.mapToDTO(person);
    }


    @Override
    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }
}
