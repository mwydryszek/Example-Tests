package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Getter
@Builder
@Entity
@Table(name="token_blacklist")
@NoArgsConstructor
@AllArgsConstructor
public class TokenBlackListEntity {

    @Id
    @Column(name = "token", length = 500)
    private String token;

    @Column(name="expire_date")
    private Timestamp expireDate;

}
