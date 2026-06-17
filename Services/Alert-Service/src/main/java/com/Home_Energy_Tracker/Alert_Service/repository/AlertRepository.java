package com.Home_Energy_Tracker.Alert_Service.repository;

import com.Home_Energy_Tracker.Alert_Service.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertRepository extends JpaRepository<Alert, Long> {
}