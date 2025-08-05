package com.examly.springapp.repository;

import com.examly.springapp.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {

    // Spring Data JPA derived queries
    List<Workout> findByType(String type);

    List<Workout> findByCaloriesBurnedGreaterThanEqual(int minCalories);

    // Custom JPQL query: Find workouts from the past N days
    @Query("SELECT w FROM Workout w WHERE w.date >= CURRENT_DATE - :days")
    List<Workout> findRecentWorkouts(int days);
}
