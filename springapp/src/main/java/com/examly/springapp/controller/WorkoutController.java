package com.examly.springapp.controller;

import com.examly.springapp.exception.WorkoutNotFoundException;
import com.examly.springapp.model.Workout;
import com.examly.springapp.service.WorkoutService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {

    private final WorkoutService service;

    public WorkoutController(WorkoutService service) {
        this.service = service;
    }

    @GetMapping
    public List<Workout> getAllWorkouts() {
        return service.getAllWorkouts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getWorkoutById(@PathVariable Long id) {
        try {
            Workout workout = service.getWorkoutById(id);
            return ResponseEntity.ok(workout);
        } catch (WorkoutNotFoundException ex) {
            return ResponseEntity.status(404).body(new ErrorMessage(ex.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<Workout> createWorkout(@Valid @RequestBody Workout workout) {
        Workout savedWorkout = service.createWorkout(workout);
        return ResponseEntity.status(201).body(savedWorkout);
    }
}
