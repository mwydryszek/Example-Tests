package com.example.demo.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.RevisionType;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class AddressRevisionDTO {

    private Long id;
    private String streetName;
    private String communeCode;
    private String houseNumber;
    private String flatNumber;
    private String city;
    private boolean isDefault;
    private Integer revNumber;
    private RevisionType revisionType;
    private String modified;
    private String created;


}
