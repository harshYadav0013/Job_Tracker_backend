package com.Harsh.jpaTutorial.repository;

import com.Harsh.jpaTutorial.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
}
