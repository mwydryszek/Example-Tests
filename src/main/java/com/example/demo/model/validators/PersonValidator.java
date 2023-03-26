package com.example.demo.model.validators;

import com.example.demo.utils.TimeProvider;
import com.example.demo.exception.ValidationException;
import com.example.demo.model.dtos.PersonDTO;
import com.example.demo.utils.MessageCollector;
import lombok.RequiredArgsConstructor;

import java.time.Period;

@RequiredArgsConstructor
public class PersonValidator {

    private final TimeProvider timeProvider;

    public void validate(PersonDTO personDTO, MessageCollector messageCollector) {
        validateBirthDate(personDTO, messageCollector);

        if(messageCollector.hasErrors()){
            throw new ValidationException(messageCollector);
        }
    }


    private void validateBirthDate(PersonDTO personDTO, MessageCollector messageCollector) {

        Period age = Period.between(personDTO.getBirthDate(), timeProvider.getLocalDate());

        if(age.getYears() < 18){
            messageCollector.addError("Person is not an adult");
        }
    }

}
