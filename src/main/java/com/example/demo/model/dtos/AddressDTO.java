package com.example.demo.model.dtos;


import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

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
    private String modified;
    private String created;
}
