package com.example.demo.model.mappers;

import com.example.demo.model.AddressEntity;
import com.example.demo.model.PersonEntity;
import com.example.demo.model.dtos.AddressDTO;
import com.example.demo.model.dtos.PersonDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(uses = {AddressMapper.class}, componentModel = "spring")
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    PersonEntity mapToEntity(PersonDTO personDTO);

    @Mapping(source = "personEntity", target = "defaultAddress", qualifiedByName = "defaultAddress")
    @Mapping(source = "personEntity", target = "addresses", qualifiedByName = "addresses")
    PersonDTO mapToDTO(PersonEntity personEntity);


    @Named("defaultAddress")
    default AddressDTO getDefaultAddress(PersonEntity personEntity) {
        return personEntity.getAddresses().stream()
                .filter(AddressEntity::isDefault)
                .map(AddressMapper.INSTANCE::mapToDTO)
                .findFirst()
                .orElse(null);
    }

    @Named("addresses")
    default List<AddressDTO> getAddresses(PersonEntity personEntity) {
        return personEntity.getAddresses().stream()
                .filter(addressEntity -> !addressEntity.isDefault())
                .map(AddressMapper.INSTANCE::mapToDTO)
                .toList();
    }

}
