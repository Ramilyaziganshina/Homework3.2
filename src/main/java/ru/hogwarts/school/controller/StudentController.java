package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudent(@PathVariable long id) {
        Student student = studentService.findStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PostMapping
    public Student createStudent(@RequestParam long id, @RequestParam String name, @RequestParam int age) {
        return studentService.addStudent(id, name, age);
    }

    @PutMapping
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        Student foundStudent = studentService.editStudent(student);
        if (foundStudent == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteStudent(@PathVariable long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{age}/{age2}")
    public ResponseEntity<Collection<Student>> findStudentsByAgeBetween(
            @PathVariable(required = false) Integer age,
            @PathVariable(required = false) Integer age2) {
        if (age != null && age2 != null && age > 0 && age2 > 0) {
            return ResponseEntity.ok(studentService.findByAgeBetween(age, age2));
        }
        return ResponseEntity.ok(studentService.getAll());
    }

    @GetMapping("/{studentId}/students")
    public Faculty findFacultyByStudentId(@PathVariable long studentId) {
        return studentService.findFacultyByStudent(studentId);
    }
}