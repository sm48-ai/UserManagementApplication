package com.satish.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.satish.entities.CityEntity;
import com.satish.entities.StateEntity;

public interface CityRepo extends JpaRepository<CityEntity, Integer>{
	
	public List<CityEntity> findByStateId(Integer stateId);

}
