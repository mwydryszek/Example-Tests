package com.example.demo.controller;

import com.example.demo.common.BaseIT;
import com.example.demo.common.PersonTestDataProvider;
import com.example.demo.model.dtos.PersonDTO;
import com.example.demo.model.dtos.PersonListResponse;
import com.example.demo.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class PersonControllerTest extends BaseIT {

    @Autowired
    private PersonRepository personRepository;

    private static final String PERSON_CONTROLLER_PATH = "/api/person";

    @Test
    void testGetAllPersonsShouldReturn() throws Exception {

        //given
        personRepository.saveAll(PersonTestDataProvider.prepareMockData());

        //when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(PERSON_CONTROLLER_PATH + "/all"));

        //then
        resultActions
                .andExpect(MockMvcResultMatchers.jsonPath("$.persons").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.persons[0].firstName").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.persons[0].firstName").value("Mateusz"));
    }

    @Test
    void testGetAllPersonsShouldReturn2() throws Exception {

        //given
        personRepository.saveAll(PersonTestDataProvider.prepareMockData());

        //when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(PERSON_CONTROLLER_PATH + "/all"));

        //then
        PersonListResponse personListResponse = asObject(resultActions, PersonListResponse.class);

        Assertions.assertAll(
                () -> Assertions.assertEquals(resultActions.andReturn().getResponse().getStatus(), 200),
                () -> Assertions.assertNotNull(personListResponse),
                () -> Assertions.assertEquals(personListResponse.getPersons().size(), 3),
                () -> Assertions.assertNotNull(personListResponse.getPersons().get(0).getFirstName(), "Mateusz"));
    }

    @Test
    void testGetAllPersonsShouldReturn3() throws Exception {

        //given
        personRepository.saveAll(PersonTestDataProvider.prepareMockData());
        PersonListResponse expectedResponse = PersonTestDataProvider.preparePersonListResponse();

        //when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(PERSON_CONTROLLER_PATH + "/all"));

        //then
        PersonListResponse response = asObject(resultActions, PersonListResponse.class);


        assertThat(response)
                .usingRecursiveComparison()
                .ignoringFields("persons.id")
                .isEqualTo(expectedResponse);
    }

    @Test
    void testGetPersonByIdShouldReturnAddedPerson() throws Exception {

        //given
        personRepository.saveAll(PersonTestDataProvider.prepareMockData());

        //when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .get(PERSON_CONTROLLER_PATH)
                .param("id", "1"));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());

    }

    @Test
    void testPostPersonShouldReturnAddedPerson() throws Exception {

        //given
        PersonDTO newPerson = PersonDTO.builder()
                .id(1L)
                .firstName("Mateusz")
                .lastName("Wydryszek")
                .birthDate(LocalDate.of(2008, 1, 1))
                .build();

        //when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .post(PERSON_CONTROLLER_PATH)
                .content(asJson(newPerson))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        //then
        resultActions
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value(newPerson.getFirstName()));

    }

    @Test
    void testPutPersonShouldReturnUpdatedPerson() throws Exception {

        //given
        personRepository.saveAll(PersonTestDataProvider.prepareMockData());

        PersonDTO newPerson = PersonDTO.builder()
                .id(1L)
                .firstName("Filip")
                .lastName("Kowalski")
                .birthDate(LocalDate.of(2008, 1, 1))
                .build();

        //when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .put(PERSON_CONTROLLER_PATH + "/{id}", 1)
                .content(asJson(newPerson))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value(newPerson.getFirstName()));


    }

    @Test
    void testDeletePersonShouldReturnStatusOk() throws Exception {

        //given
        personRepository.saveAll(PersonTestDataProvider.prepareMockData());

        //when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .delete(PERSON_CONTROLLER_PATH + "/{id}", 1));

        //then
        resultActions
                .andExpect(status().isOk());
    }

}