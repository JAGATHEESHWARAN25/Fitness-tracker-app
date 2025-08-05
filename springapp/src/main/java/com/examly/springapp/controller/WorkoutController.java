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

    // GET all workouts
    @GetMapping
    public List<Workout> getAll() {
        return service.getAllWorkouts();
    }

    // GET workout by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Workout workout = service.getWorkoutById(id);
        return ResponseEntity.ok(workout);
    }

    // POST create workout
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Workout workout) {
        return ResponseEntity.status(201).body(service.createWorkout(workout));
    }

    // PUT update workout
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Workout workout) {
        return ResponseEntity.ok(service.updateWorkout(id, workout));
    }

    // DELETE workout
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.deleteWorkout(id);
        return ResponseEntity.noContent().build();
    }

    // GET workouts by type (JPA method)
    @GetMapping("/type/{type}")
    public List<Workout> getByType(@PathVariable String type) {
        return service.getWorkoutsByType(type);
    }

    // GET workouts with minimum calories burned (JPA method)
    @GetMapping("/calories/{min}")
    public List<Workout> getByMinCalories(@PathVariable int min) {
        return service.getWorkoutsWithMinCalories(min);
    }

    // GET recent workouts using JPQL query
    @GetMapping("/recent/{days}")
    public List<Workout> getRecentWorkouts(@PathVariable int days) {
        return service.getRecentWorkouts(days);
    }
}
