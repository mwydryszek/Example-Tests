package com.example.demo.model.mappers;

import com.example.demo.model.AddressEntity;
import com.example.demo.model.dtos.AddressDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    @Mapping(source = "default", target = "isDefault")
    AddressEntity mapToEntity(AddressDTO addressDTO);

    @Mapping(source = "default", target = "isDefault")
    AddressDTO mapToDTO(AddressEntity addressEntity);

}
