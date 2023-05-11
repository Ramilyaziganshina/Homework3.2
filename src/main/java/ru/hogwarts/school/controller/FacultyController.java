package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;

@RestController
@RequestMapping("faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("{id}")// GET http://localhost:8080/faculty/1
    public ResponseEntity<Faculty> getFaculty(@PathVariable long id) {
        Faculty faculty = facultyService.findFaculty(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PostMapping// POST http://localhost:8080/faculty
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.addFaculty(faculty);
    }

    @PutMapping // PUT http://localhost:8080/faculty
    public ResponseEntity<Faculty> editFaculty(@RequestBody Faculty faculty) {
        Faculty foundFaculty = facultyService.editFaculty(faculty);
        if (foundFaculty == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(faculty);
    }

    @DeleteMapping("{id}")// DELETE http://localhost:8080/faculty/1
    public ResponseEntity<?> deleteFaculty(@PathVariable long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    // GET http://localhost:8080/faculty or http://localhost:8080/faculty/red or http://localhost:8080/faculty/Griffindor
    public ResponseEntity<Collection<Faculty>> findFacultiesByColourOrName(@RequestParam(required = false) String colour,
                                                                           @RequestParam(required = false) String name) {
        if (colour != null && !colour.isBlank()) {
            return ResponseEntity.ok(facultyService.findByColourIgnoreCase(colour));
        }
        if (name != null && !name.isBlank()) {
            return ResponseEntity.ok(facultyService.findByNameIgnoreCase(name));
        }
        return ResponseEntity.ok(facultyService.getAll());
    }

    @GetMapping("/{facultyId}/faculty")// GET http://localhost:8080/faculty/1/faculty
    public Collection<Student> findStudentsByFacultyId(@PathVariable long facultyId) {
        return facultyService.findStudentsByFacultyId(facultyId);
    }
}