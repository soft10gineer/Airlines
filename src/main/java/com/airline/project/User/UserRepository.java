package com.airline.project.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<UserOnboarding, String> {
	@Query(value = "SELECT u FROM UserOnboarding u WHERE u.userFirstName ILIKE :name") UserOnboarding findByUserName(@Param("name") String name);
	@Query(value = "SELECT u FROM UserOnboarding u WHERE u.userEmail = :email") UserOnboarding findByEmail(@Param("email") String email);
	
}
