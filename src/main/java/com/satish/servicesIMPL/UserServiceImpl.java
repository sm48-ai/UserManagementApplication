package com.satish.servicesIMPL;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.satish.dto.LoginFormDTO;
import com.satish.dto.RegisterFormDTO;
import com.satish.dto.ResetPwdFormDTO;
import com.satish.dto.UserDTO;
import com.satish.entities.CityEntity;
import com.satish.entities.CountryEntity;
import com.satish.entities.StateEntity;
import com.satish.entities.UserEntity;
import com.satish.repo.CityRepo;
import com.satish.repo.CountryRepo;
import com.satish.repo.StateRepo;
import com.satish.repo.UserRepo;
import com.satish.services.EmailService;
import com.satish.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private CountryRepo countryRepo;
	
	@Autowired
	private StateRepo stateRepo;
	
	@Autowired
	private CityRepo cityRepo;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public Map<Integer, String> getCountries() {
		Map<Integer, String> countryMap=new HashMap<>();
		List<CountryEntity> countriesList=countryRepo.findAll();
		countriesList.forEach(c->{
			countryMap.put(c.getCountryId(), c.getCountryName());
		});
		return countryMap;
	}

	@Override
	public Map<Integer, String> getStates(Integer countryId) {
		Map<Integer, String> stateMap=new HashMap<>();
		List<StateEntity> stateList= stateRepo.findByCountryId(countryId);
		stateList.forEach(s->{
			stateMap.put(s.getStateId(), s.getStateName());
		});
		return stateMap;
	}

	@Override
	public Map<Integer, String> getCities(Integer stateId) {
		Map<Integer, String> citiesMap=new HashMap<>();
		List<CityEntity> citiesList =cityRepo.findByStateId(stateId);
		citiesList.forEach(c->{
			citiesMap.put(c.getCityId(), c.getCityName());
		});
		
		return citiesMap;
	}

	@Override
	public boolean duplicateEmailCheck(String email) {
		UserEntity byEmail=userRepo.findByEmail(email);
		if(byEmail !=null) {
			return true;
		}else {
			return false;
		}
		
	}

	@Override
	public boolean saveUser(RegisterFormDTO regFormDTO) {
		UserEntity userEntity=new UserEntity();
		BeanUtils.copyProperties(regFormDTO, userEntity);
		CountryEntity country=
				countryRepo.findById(regFormDTO.getCountryId()).orElse(null);
		userEntity.setCountry(country);
		
		StateEntity state =
				stateRepo.findById(regFormDTO.getStateId()).orElse(null);
		userEntity.setState(state);
		
		CityEntity city=
				cityRepo.findById(regFormDTO.getCityId()).orElse(null);
		userEntity.setCity(city);
		
		String randomPwd= generateRandomPwd();
		userEntity.setPwd(randomPwd);
		userEntity.setPwdUpdate("No");
		
		UserEntity savedUser = userRepo.save(userEntity);
		if(savedUser.getUserId() !=null) {
			String subject= "Your Account Created";
			String body ="Your Password To Login : " + randomPwd;
			String to= regFormDTO.getEmail();
			
			System.out.println("Sending email to: " + regFormDTO.getEmail());

			emailService.sendEmail(subject, body, to);
			return true;
		}
		return false;
	}

	@Override
	public UserDTO login(LoginFormDTO loginFormDTO) {
		UserEntity userEntity=
				userRepo.findByEmailAndPwd(loginFormDTO.getEmail(), loginFormDTO.getPwd());
		if(userEntity !=null) {
			UserDTO userDTO= new UserDTO();
			
			BeanUtils.copyProperties(userEntity, userDTO);
			
			return userDTO;
		}
		return null;
	}

	@Override
	public boolean resetPwd(ResetPwdFormDTO resetPwdDTO) {
		String email=resetPwdDTO.getEmail();
		
		UserEntity entity=userRepo.findByEmail(email);
		
		entity.setPwd(resetPwdDTO.getNewPwd());
		entity.setPwdUpdate("Yes");
		
		userRepo.save(entity);
		
		return true;
	}
	
	private String generateRandomPwd() {
		String upperCaseLetter="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String lowerCaseLetter="abcdefghijklmnopqrstuvwxyz";
		
		String alphabets=upperCaseLetter + lowerCaseLetter;
		
		Random random=new Random();
		 
		StringBuffer generatedPwd=new StringBuffer();
		
		for(int i=0; i<5; i++) {
			int randomIndex=random.nextInt(alphabets.length());
			generatedPwd.append(alphabets.charAt(randomIndex));
		}
		
		return generatedPwd.toString();
	}

}
