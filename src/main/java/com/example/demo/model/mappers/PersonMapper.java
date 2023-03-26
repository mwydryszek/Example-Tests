package com.example.demo.model.mappers;

import com.example.demo.model.PersonEntity;
import com.example.demo.model.dtos.AddressDTO;
import com.example.demo.model.dtos.PersonDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    PersonEntity mapToEntity(PersonDTO personDTO);

    PersonDTO mapToDTO(PersonEntity personEntity);

}
