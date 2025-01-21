package com.satish.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.satish.entities.CountryEntity;

public interface CountryRepo extends JpaRepository<CountryEntity, Integer>{

}
