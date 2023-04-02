package com.example.demo.controller;

import com.example.demo.model.dtos.PersonDTO;
import com.example.demo.model.dtos.PersonListResponse;
import com.example.demo.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/person")
public class PersonController {

    private final PersonService personService;

    @GetMapping("/all")
    public PersonListResponse getAllPersons() {
        return new PersonListResponse(personService.getAllPersons());
    }

    @GetMapping
    public ResponseEntity<PersonDTO> getPersonById(@RequestParam(name = "id", required = false) Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personService.getPersonById(id));
    }

    @PostMapping
    public ResponseEntity<PersonDTO> addPerson(@RequestBody PersonDTO personDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(personService.addPerson(personDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonDTO> updatePerson(@PathVariable("id") Long id, @RequestBody PersonDTO personDTO) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personService.updatePerson(id, personDTO));
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable("id") Long id) {
        personService.deletePerson(id);
    }


}
