package com.example.demo;

import lombok.*;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionType;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EntityRevision<T> {

    private T entity;
    private DefaultRevisionEntity userEntityRevision;
    private RevisionType revisionType;
}
