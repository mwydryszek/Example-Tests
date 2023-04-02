package com.example.demo.model.mappers;

import com.example.demo.model.AddressEntity;
import com.example.demo.model.PersonEntity;
import com.example.demo.model.dtos.AddressDTO;
import com.example.demo.model.dtos.PersonDTO;
import liquibase.util.CollectionUtil;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.function.Predicate.not;

@Mapper(uses = {AddressMapper.class}, componentModel = "spring")
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    PersonEntity mapToEntity(PersonDTO personDTO);

    @Mapping(source = "personEntity", target = "defaultAddress", qualifiedByName = "defaultAddress")
    @Mapping(source = "personEntity", target = "addresses", qualifiedByName = "addresses")
    PersonDTO mapToDTO(PersonEntity personEntity);


    @Named("defaultAddress")
    default AddressDTO getDefaultAddress(PersonEntity personEntity) {
        if(CollectionUtils.isEmpty(personEntity.getAddresses())){
            return null;
        }
        return personEntity.getAddresses().stream()
                .filter(AddressEntity::isDefault)
                .map(AddressMapper.INSTANCE::mapToDTO)
                .findFirst()
                .orElse(null);
    }

    @Named("addresses")
    default List<AddressDTO> getAddresses(PersonEntity personEntity) {

        if(CollectionUtils.isEmpty(personEntity.getAddresses())){
            return null;
        }
        return personEntity.getAddresses().stream()
                .filter(not(AddressEntity::isDefault))
                .map(AddressMapper.INSTANCE::mapToDTO)
                .toList();
    }

}
