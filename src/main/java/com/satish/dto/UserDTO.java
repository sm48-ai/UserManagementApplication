package com.satish.dto;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
@Data
public class UserDTO {
	
	private Integer userId;

	private String email;
	
	private String pwd;
		
	private String pwdUpdate;




}
