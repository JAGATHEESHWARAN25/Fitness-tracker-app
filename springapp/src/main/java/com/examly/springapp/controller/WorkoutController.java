package com.examly.springapp.controller;

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
    public ResponseEntity<List<Workout>> getAll() {
        return ResponseEntity.ok(service.getAllWorkouts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Workout> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getWorkoutById(id));
    }

    @PostMapping
    public ResponseEntity<Workout> create(@Valid @RequestBody Workout workout) {
        return ResponseEntity.status(201).body(service.createWorkout(workout));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Workout> update(@PathVariable Long id, @Valid @RequestBody Workout workout) {
        return ResponseEntity.ok(service.updateWorkout(id, workout));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteWorkout(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Workout>> getByType(@PathVariable String type) {
        return ResponseEntity.ok(service.getWorkoutsByType(type));
    }

    @GetMapping("/calories/{min}")
    public ResponseEntity<List<Workout>> getByMinCalories(@PathVariable int min) {
        return ResponseEntity.ok(service.getWorkoutsWithMinCalories(min));
    }

    @GetMapping("/recent/{days}")
    public ResponseEntity<List<Workout>> getRecentWorkouts(@PathVariable int days) {
        return ResponseEntity.ok(service.getRecentWorkouts(days));
    }
}
