package com.examly.springapp.repository;

import com.examly.springapp.model.UserPlanProgress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserPlanProgressRepository extends JpaRepository<UserPlanProgress, Long> {
    List<UserPlanProgress> findByUserId(Long userId);
    List<UserPlanProgress> findByWorkoutPlanId(Long planId);
}
