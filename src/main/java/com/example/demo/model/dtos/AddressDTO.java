package com.example.demo.model.dtos;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class AddressDTO {
    private Long id;
    private String streetName;
    private String communeCode;
    private String houseNumber;
    private String flatNumber;
    private String city;
    private boolean isDefault;
}
