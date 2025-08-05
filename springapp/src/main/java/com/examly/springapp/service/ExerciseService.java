package com.examly.springapp.service;

import com.examly.springapp.model.Exercise;
import com.examly.springapp.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseService {
    @Autowired
    private ExerciseRepository exerciseRepository;

    public Exercise createExercise(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }

    public Optional<Exercise> getExerciseById(Long id) {
        return exerciseRepository.findById(id);
    }

    public Exercise updateExercise(Long id, Exercise updated) {
        return exerciseRepository.findById(id).map(exercise -> {
            exercise.setName(updated.getName());
            exercise.setDescription(updated.getDescription());
            exercise.setReps(updated.getReps());
            exercise.setSets(updated.getSets());
            return exerciseRepository.save(exercise);
        }).orElseThrow(() -> new RuntimeException("Exercise not found"));
    }

    public void deleteExercise(Long id) {
        exerciseRepository.deleteById(id);
    }
}
