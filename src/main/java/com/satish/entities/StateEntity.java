package com.satish.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="state_master")
@Data
public class StateEntity {
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="state_id")
	private Integer stateId;
	
	@Column(name="state_name")
	private String stateName;
	
	@Column(name="country_id")
	private Integer countryId;
	

}
