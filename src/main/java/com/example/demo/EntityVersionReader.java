package com.example.demo;

import com.example.demo.model.AddressEntity;
import com.example.demo.model.dtos.AddressRevisionDTO;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Address;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionType;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional
public class EntityVersionReader {

    private final EntityManager entityManager;

    public List<EntityRevision<AddressEntity>> readLogs() {
        return (List<EntityRevision<AddressEntity>>)AuditReaderFactory.get(entityManager)
                .createQuery()
                .forRevisionsOfEntity(AddressEntity.class,false, true)
                .getResultList().stream()
                .map(this::buildEntityRevision)
                .toList();
    }

    private EntityRevision<AddressEntity> buildEntityRevision(Object revision){
       Object[] revisionArray = (Object[]) revision;
       AddressEntity entity = (AddressEntity) revisionArray[0];
       DefaultRevisionEntity userEntityRevision = (DefaultRevisionEntity) revisionArray[1];
       RevisionType revisionType = (RevisionType) revisionArray[2];

       return EntityRevision.<AddressEntity>builder()
               .entity(entity)
               .userEntityRevision(userEntityRevision)
               .revisionType(revisionType)
               .build();
    }


}
