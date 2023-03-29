package com.example.demo.model.validators;

import com.example.demo.exception.ValidationException;
import com.example.demo.model.dtos.PersonDTO;
import com.example.demo.utils.MessageCollector;
import com.example.demo.utils.TimeProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
class PersonValidatorTest {

    @InjectMocks
    PersonValidator personValidator;

    @Spy
    TimeProvider timeProvider;

    @Spy
    MessageCollector messageCollector;

    @Test
    @DisplayName("Should throw ValidationException")
    public void validate() {

        //given

        LocalDate localDate = LocalDate.of(2010, 3, 23);
        Mockito.when(timeProvider.getLocalDate()).thenReturn(LocalDate.now());

        PersonDTO person = PersonDTO.builder()
                .firstName("Mateusz")
                .lastName("Wydryszek")
                .birthDate(localDate)
                .build();

        Assertions.assertThrows(ValidationException.class,() -> personValidator.validate(person, messageCollector));

    }




}