package com.gergelygazso.scoretracker.appuser;

import java.util.Collection;
import java.util.Collections;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//implementing UserDetails is a requirement for using Spring Security
@Getter //lombok generates getters for all of the properties
@Setter //lombok generates setters for all of the properties
@EqualsAndHashCode //lombok: https://javabydeveloper.com/lombok-equalsandhashcode-examples/
@NoArgsConstructor //lombok generates noArgConstructor for all of the properties
@Entity //this will be a table in db
public class AppUser implements UserDetails{
	
	//mysql doesn't suppoert sequence. IDENTITY is perfect for MySQL. https://thorben-janssen.com/5-things-you-need-to-know-when-using-hibernate-with-mysql/
	/*@SequenceGenerator(
			name = "student_sequence",
			sequenceName = "student_sequence",
			allocationSize = 1
	)*/
	
	/*@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "student_sequence"
	)*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	@Enumerated(EnumType.STRING) //because AppUserRole is an enum
	private AppUserRole appUserRole;
	private boolean locked = false;
	private boolean enabled = true;
	
	
	public AppUser(String firstName,
			String lastName,
			String email,
			String password, 
			AppUserRole appUserRole) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.appUserRole = appUserRole;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority authority = 
				new SimpleGrantedAuthority(appUserRole.name());
		return Collections.singletonList(authority);
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}
	
	public String getFirtstName() {
		return firstName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !locked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	

	
}
