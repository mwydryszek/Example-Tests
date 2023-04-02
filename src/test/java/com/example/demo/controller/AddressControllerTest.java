package com.example.demo.controller;

import com.example.demo.common.AddressTestDataProvider;
import com.example.demo.common.BaseIT;
import com.example.demo.common.PersonTestDataProvider;
import com.example.demo.model.PersonEntity;
import com.example.demo.model.dtos.AddressDTO;
import com.example.demo.model.dtos.AddressListResponse;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class AddressControllerTest extends BaseIT {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PersonRepository personRepository;

    private static final String ADDRESS_CONTROLLER_PATH = "/api/address";

    @Test
    void testGetAllPersonsShouldReturn() throws Exception {

        //given
        prepareData();

        //when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(ADDRESS_CONTROLLER_PATH + "/all"));

        //then
        resultActions
                .andExpect(MockMvcResultMatchers.jsonPath("$.addresses").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.addresses[0].city").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.addresses[0].city").value("Koszalin"));
    }

    @Test
    void testGetAllPersonsShouldReturn2() throws Exception {

        //given
        prepareData();

        //when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(ADDRESS_CONTROLLER_PATH + "/all"));

        //then
        AddressListResponse addressListResponse = asObject(resultActions, AddressListResponse.class);

        Assertions.assertAll(
                () -> Assertions.assertEquals(resultActions.andReturn().getResponse().getStatus(), 200),
                () -> Assertions.assertNotNull(addressListResponse),
                () -> Assertions.assertEquals(addressListResponse.getAddresses().size(), 3),
                () -> Assertions.assertNotNull(addressListResponse.getAddresses().get(0).getCity(), "Koszalin"));
    }


    @Test
    void testGetPersonByIdShouldReturnAddedPerson() throws Exception {

        //given
        prepareData();

        //when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .get(ADDRESS_CONTROLLER_PATH)
                .param("id", "1"));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());

    }

    @Test
    void testPostPersonShouldReturnAddedPerson() throws Exception {

        //given
        AddressDTO newAddress = AddressDTO.builder()
                .city("Warszawa")
                .streetName("Bajkowa")
                .flatNumber("23")
                .communeCode("00-120")
                .isDefault(false)
                .build();

        //when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .post(ADDRESS_CONTROLLER_PATH)
                .content(asJson(newAddress))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        //then
        resultActions
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.city").value(newAddress.getCity()));

    }

    @Test
    void testPutPersonShouldReturnUpdatedPerson() throws Exception {

        //given
        prepareData();

        AddressDTO newAddress = AddressDTO.builder()
                .city("Warszawa")
                .streetName("Bajkowa")
                .flatNumber("23")
                .communeCode("00-120")
                .isDefault(false)
                .build();

        //when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .put(ADDRESS_CONTROLLER_PATH + "/{id}", 1)
                .content(asJson(newAddress))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.city").value(newAddress.getCity()));


    }

    @Test
    void testDeletePersonShouldReturnStatusOk() throws Exception {

        //given
        prepareData();

        //when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .delete(ADDRESS_CONTROLLER_PATH + "/{id}", 1));

        //then
        resultActions
                .andExpect(status().isOk());
    }

    private void prepareData() {
        List<PersonEntity> personEntities = PersonTestDataProvider.prepareMockData();
        personRepository.saveAll(personEntities);
        addressRepository.saveAll(AddressTestDataProvider.prepareMockData(personEntities.get(0)));
    }


}