package com.satish.entities;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="user_master")
public class UserEntity {
	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	
	@Column(name="user_name")
	private String uname;
	
	@Column(name="email")
	private String email;
	
	@Column(name="phno")
	private Long phno;
	
	@Column(name="pwd")
	private String pwd;
	
	@Column(name="pwd_update")
	private String pwdUpdate;
	
	
	@ManyToOne
	@JoinColumn(name="country_id")
	private CountryEntity country;
	
	@ManyToOne
	@JoinColumn(name="state_id")
	private StateEntity state;
	
	@ManyToOne
	@JoinColumn(name="city_id")
	private CityEntity city;
	
	
	@CreationTimestamp
	@Column(name="created_date", updatable=false)
	private LocalDate createdDate;
	
	@UpdateTimestamp
	@Column(name="updated_date" , insertable = false)
	private LocalDate updatedDate;
	
}
