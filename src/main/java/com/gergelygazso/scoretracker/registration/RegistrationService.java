package com.gergelygazso.scoretracker.registration;

import org.springframework.stereotype.Service;

import com.gergelygazso.scoretracker.appuser.AppUser;
import com.gergelygazso.scoretracker.appuser.AppUserRole;
import com.gergelygazso.scoretracker.appuser.AppUserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RegistrationService {
	
	private final AppUserService appUserService;
	private final EmailValidator emailValidator;

	public String register(RegistrationRequest request) {
		boolean isValidEmail = emailValidator.test(request.getEmail());
		
		if(!isValidEmail) {
			throw new IllegalStateException("email not valid");
		}
		return appUserService.signUpUser(
				new AppUser(
						request.getFirstName(),
						request.getLastName(),
						request.getEmail(),
						request.getPassword(),
						AppUserRole.USER)
				);
	}

}
