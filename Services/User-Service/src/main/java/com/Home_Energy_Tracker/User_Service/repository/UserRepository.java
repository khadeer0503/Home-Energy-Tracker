package com.Home_Energy_Tracker.User_Service.repository;

import com.Home_Energy_Tracker.User_Service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
