package com.satish.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="city_master")
@Data
public class CityEntity {
	@Id
	@Column(name="city_id")
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cityId;
	
	private String cityName;
	
	@JoinColumn(name="state_id")
	private Integer stateId;
	

}
