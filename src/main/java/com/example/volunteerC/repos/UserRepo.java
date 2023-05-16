package com.example.volunteerC.repos;

import com.example.volunteerC.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findById(long n);
}
