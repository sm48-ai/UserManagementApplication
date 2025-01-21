package com.satish.services;

import java.util.Map;

import com.satish.dto.LoginFormDTO;
import com.satish.dto.RegisterFormDTO;
import com.satish.dto.ResetPwdFormDTO;
import com.satish.dto.UserDTO;

public interface UserService {
	public Map<Integer , String> getCountries();
	
	public Map<Integer, String> getStates(Integer countryId);
	
	public Map<Integer, String> getCities(Integer stateId);
	
	public boolean duplicateEmailCheck(String email);
	
	public boolean saveUser(RegisterFormDTO registerFormDTO);
	
	public UserDTO login(LoginFormDTO loginFormDTO);
	
	public boolean resetPwd(ResetPwdFormDTO resetPwdDTO);
	
	
	

}
