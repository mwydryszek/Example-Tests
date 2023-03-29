package com.example.demo.controller;

import com.example.demo.model.dtos.PersonDTO;
import com.example.demo.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/person")
public class PersonController {

    private final PersonService personService;

    @GetMapping("/all")
    public List<PersonDTO> getAllPersons() {
        return personService.getAllPersons();
    }

    @GetMapping
    public PersonDTO getPersonById(@RequestParam(name = "id", required = false) Long id) {
        return personService.getPersonById(id);
    }

    @PostMapping
    public PersonDTO addPerson(@RequestBody PersonDTO personDTO) {
        return personService.addPerson(personDTO);
    }

    @PutMapping("/{id}")
    public PersonDTO updatePerson(@PathVariable("id") Long id, @RequestBody PersonDTO personDTO) {
        return personService.updatePerson(id, personDTO);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable("id") Long id) {
        personService.deletePerson(id);
    }


}
