package com.chiletel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chiletel.entity.Da�o;

@Repository
public interface IDa�oRepository extends JpaRepository<Da�o, Integer> {

}
