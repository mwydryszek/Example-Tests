package com.example.demo.repository;

import com.example.demo.model.TokenBlackListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenBlackListRepository extends JpaRepository<TokenBlackListEntity, String> {

}
