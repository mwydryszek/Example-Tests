package com.example.demo.model.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class AddressDTO {
    private Long id;
    private String streetName;
    private String communeCode;
    private String houseNumber;
    private String flatNumber;
    private boolean isDefault;
}
