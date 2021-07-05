package com.gergelygazso.scoretracker.appuser;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository //the repository interface's implementation communicates with the db
@Transactional(readOnly = true) //begin and commit transaction automatically
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
	
	Optional<AppUser> findByEmail(String email);
	
}
