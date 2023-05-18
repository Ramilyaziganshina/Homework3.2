package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

public interface StudentService {
    Student addStudent(Student student);
    Student findStudent(long id);
    Student editStudent(Student student);
    void deleteStudent(long id);
    Collection<Student> findByAge(int age);
    Collection<Student> getAll();
    Collection<Student> findByAgeBetween(Integer age, Integer age2);
    Faculty findFacultyByStudent(long studentId);
    double getAverageAge();
    int getCountOfStudents();
    Collection<Student> getFiveLastStudens();
    Collection<Student> getStudentsByName(String name);
    List<String> getStudentsWithNamesStartedA();
    double averageAgeOfStudents();
    void printAll();
}