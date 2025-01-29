package com.airline.project.Login;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLoginRepository extends JpaRepository<UserLoginRegister, String> {

}
