package com.examly.springapp.controller;

import com.examly.springapp.exception.WorkoutNotFoundException;
import com.examly.springapp.model.Workout;
import com.examly.springapp.service.WorkoutService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            return ResponseEntity.ok(service.getWorkoutById(id));
        } catch (WorkoutNotFoundException ex) {
            return ResponseEntity.status(404).body(new ErrorMessage(ex.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> createWorkout(@Valid @RequestBody Workout workout) {
        return ResponseEntity.status(201).body(service.createWorkout(workout));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));
        return ResponseEntity.badRequest().body(new ErrorMessage("Validation failed", errors));
    }
}
