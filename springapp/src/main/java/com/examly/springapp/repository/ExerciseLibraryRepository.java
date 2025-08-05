package com.examly.springapp.repository;

import com.examly.springapp.model.ExerciseLibrary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciseLibraryRepository extends JpaRepository<ExerciseLibrary, Long> {
    List<ExerciseLibrary> findByCreatedById(Long trainerId);
    List<ExerciseLibrary> findByMuscleGroup(String muscleGroup);
    List<ExerciseLibrary> findByDifficulty(String difficulty);
}
