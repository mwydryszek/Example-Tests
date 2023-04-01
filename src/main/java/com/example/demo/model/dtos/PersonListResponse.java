package com.example.demo.model.dtos;

import lombok.*;

import java.util.List;

@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Getter
@Setter
public class PersonListResponse {
    private final List<PersonDTO> persons;
}
