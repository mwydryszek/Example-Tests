package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "ADDRESS")
@SequenceGenerator(name = "generator_seq",
        sequenceName = "address_id_seq",
        allocationSize = 1,
        initialValue = 1)
@Audited
@EntityListeners(AuditingEntityListener.class)
public class AddressEntity extends BaseEntity {

    @CreatedBy
    private String modified;
    @LastModifiedBy
    private String created;

    @Column(length = 100, nullable = false)
    private String streetName;

    @Column(length = 6)
    private String communeCode;

    @Column(length = 25)
    private String houseNumber;

    @Column(length = 25)
    private String flatNumber;

    @Column(length = 100, nullable = false)
    private String city;

    @Column(nullable = false)
    private boolean isDefault;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne
    @JoinColumn(name="person_id", referencedColumnName = "id")
    private PersonEntity personEntity;

}
