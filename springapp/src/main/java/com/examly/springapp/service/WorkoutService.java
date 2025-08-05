package com.examly.springapp.service;

import com.examly.springapp.exception.WorkoutNotFoundException;
import com.examly.springapp.model.Workout;
import com.examly.springapp.repository.WorkoutRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public Workout updateWorkout(Long id, Workout updatedWorkout) {
        Workout existing = getWorkoutById(id);
        existing.setType(updatedWorkout.getType());
        existing.setDuration(updatedWorkout.getDuration());
        existing.setCaloriesBurned(updatedWorkout.getCaloriesBurned());
        existing.setDate(updatedWorkout.getDate());
        existing.setNotes(updatedWorkout.getNotes());
        return repository.save(existing);
    }

    public void deleteWorkout(Long id) {
        Workout existing = getWorkoutById(id);
        repository.delete(existing);
    }

    public List<Workout> getWorkoutsByType(String type) {
        return repository.findByType(type);
    }

    public List<Workout> getWorkoutsWithMinCalories(int minCalories) {
        return repository.findByCaloriesBurnedGreaterThanEqual(minCalories);
    }

    public List<Workout> getRecentWorkouts(int days) {
        LocalDate dateFrom = LocalDate.now().minusDays(days);
        return repository.findRecentWorkouts(dateFrom);
    }
}
