package com.examly.springapp.repository;

import com.examly.springapp.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {

    List<Workout> findByType(String type);

    List<Workout> findByCaloriesBurnedGreaterThanEqual(int minCalories);

    // JPQL query with LocalDate passed as param
    @Query("SELECT w FROM Workout w WHERE w.date >= :dateFrom")
    List<Workout> findRecentWorkouts(@Param("dateFrom") LocalDate dateFrom);
}
