package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.List;

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
    public Student createStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
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
    public ResponseEntity<?> deleteStudent(@PathVariable long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{age}/{age2}")
    public ResponseEntity<Collection<Student>> findStudentsByAgeBetween(
            @PathVariable(required = false) Integer age,
            @PathVariable(required = false) Integer age2) {
        if (age == null || age2 == null) {
            return ResponseEntity.ok(studentService.getAll());
        }
        return ResponseEntity.ok(studentService.findByAgeBetween(age, age2));
    }

    @GetMapping("/{studentId}/students")
    public Faculty findFacultyByStudentId(@PathVariable(required = false) long studentId) {
        return studentService.findFacultyByStudent(studentId);
    }

    @GetMapping("/average_age")
    public double getAverageAge() {
        return studentService.getAverageAge();
    }

    @GetMapping("/get_count_of_students")
    public int getCountOfStudents() {
        return studentService.getCountOfStudents();
    }

    @GetMapping("/get_five_last_students")
    public Collection<Student> getFiveLastStudents() {
        return studentService.getFiveLastStudens();
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Collection<Student>> getStudentsByName(@PathVariable("name") String name) {
        Collection<Student> students = studentService.getStudentsByName(name);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/Aname")
    public ResponseEntity<List<String>> getStudentsWithNamesStartedA() {
        List<String> students = studentService.getStudentsWithNamesStartedA();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/averageAge")
    public double getAverageAgeOfStudents() {
        return studentService.averageAgeOfStudents();
    }

    @GetMapping("/printAll")
    public void printAll() {
        studentService.printAll();
    }
}