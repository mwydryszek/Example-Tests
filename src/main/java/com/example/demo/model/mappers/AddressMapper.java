package com.example.demo.model.mappers;

import com.example.demo.model.AddressEntity;
import com.example.demo.model.dtos.AddressDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressEntity mapToEntity(AddressDTO addressDTO);

    AddressDTO mapToDTO(AddressEntity addressEntity);

}
