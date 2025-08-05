package com.examly.springapp.controller;

import com.examly.springapp.model.ExerciseLibrary;
import com.examly.springapp.service.ExerciseLibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exercise-library")
public class ExerciseLibraryController {

    @Autowired
    private ExerciseLibraryService libraryService;

    @GetMapping
    public List<ExerciseLibrary> getAllExercises() {
        return libraryService.getAllExercises();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExerciseLibrary> getExerciseById(@PathVariable Long id) {
        return libraryService.getExerciseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{creatorId}")
    public ExerciseLibrary createExercise(@RequestBody ExerciseLibrary exercise, @PathVariable Long creatorId) {
        return libraryService.createExercise(exercise, creatorId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExerciseLibrary> updateExercise(@PathVariable Long id, @RequestBody ExerciseLibrary exercise) {
        return ResponseEntity.ok(libraryService.updateExercise(id, exercise));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExercise(@PathVariable Long id) {
        libraryService.deleteExercise(id);
        return ResponseEntity.noContent().build();
    }
}
