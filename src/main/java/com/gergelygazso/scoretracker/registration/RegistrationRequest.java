package com.gergelygazso.scoretracker.registration;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class RegistrationRequest {
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	
}
