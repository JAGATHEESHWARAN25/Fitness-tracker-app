package com.examly.springapp.service;

import com.examly.springapp.model.ExerciseLibrary;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.ExerciseLibraryRepository;
import com.examly.springapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseLibraryService {

    private final ExerciseLibraryRepository exerciseLibraryRepository;
    private final UserRepository userRepository;

    @Autowired
    public ExerciseLibraryService(ExerciseLibraryRepository exerciseLibraryRepository, UserRepository userRepository) {
        this.exerciseLibraryRepository = exerciseLibraryRepository;
        this.userRepository = userRepository;
    }

    // Create ExerciseLibrary entry
    public ExerciseLibrary createExercise(ExerciseLibrary exercise, Long userId) {
        Optional<User> creator = userRepository.findById(userId);
        if (creator.isPresent()) {
            exercise.setCreatedBy(creator.get());
            return exerciseLibraryRepository.save(exercise);
        } else {
            throw new IllegalArgumentException("User with ID " + userId + " not found");
        }
    }

    // Get all exercises in the library
    public List<ExerciseLibrary> getAllExercises() {
        return exerciseLibraryRepository.findAll();
    }

    // Get exercise by ID
    public Optional<ExerciseLibrary> getExerciseById(Long id) {
        return exerciseLibraryRepository.findById(id);
    }

    // Update an existing exercise
    public ExerciseLibrary updateExercise(Long id, ExerciseLibrary updatedExercise) {
        return exerciseLibraryRepository.findById(id).map(existing -> {
            existing.setName(updatedExercise.getName());
            existing.setDescription(updatedExercise.getDescription());
            existing.setTargetMuscles(updatedExercise.getTargetMuscles());
            existing.setEquipmentNeeded(updatedExercise.getEquipmentNeeded());
            return exerciseLibraryRepository.save(existing);
        }).orElseThrow(() -> new IllegalArgumentException("ExerciseLibrary not found with id: " + id));
    }

    // Delete an exercise
    public void deleteExercise(Long id) {
        exerciseLibraryRepository.deleteById(id);
    }
}
