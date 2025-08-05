package com.examly.springapp.service;

import com.examly.springapp.exception.WorkoutNotFoundException;
import com.examly.springapp.model.Workout;
import com.examly.springapp.repository.WorkoutRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class WorkoutService {

    private final WorkoutRepository repository;

    public WorkoutService(WorkoutRepository repository) {
        this.repository = repository;
    }

    public List<Workout> getAllWorkouts() {
        return repository.findAll();
    }

    public Workout getWorkoutById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new WorkoutNotFoundException(id));
    }

    public Workout createWorkout(Workout workout) {
        return repository.save(workout);
    }
}
