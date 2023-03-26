package com.example.demo.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class PersonDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private AddressDTO defaultAddress;
    private List<AddressDTO> addresses;

}
