package com.kchmielewski.sda.java.spring01java.repository;

import com.kchmielewski.sda.java.spring01java.entity.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerEntity,Integer> {

}
