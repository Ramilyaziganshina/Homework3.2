package ru.hogwarts.school.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {StudentServiceImpl.class})
@ExtendWith(SpringExtension.class)
class StudentServiceTest {

    @Autowired
    public StudentService studentService;

    @MockBean
    public StudentRepository studentRepository;

    @Test
    void addStudent_success() {
        long id = 1;
        String name = "Harry Potter";
        int age = 11;
        Student harry = new Student(id, name, age);

        when(studentRepository.save(harry)).thenReturn(harry);

        Student actualResult = studentService.addStudent(harry);

        assertEquals(harry, actualResult);
    }

    @Test
    void findStudent_success() {
        long id = 1;
        Student harry = new Student(1, "Harry Potter", 11);

        when(studentRepository.findById(id)).thenReturn(Optional.of(harry));

        Student actualResult = studentService.findStudent(id);

        assertEquals(harry, actualResult);
    }

    @Test
    void editStudent_success() {
        Student harry = new Student(1, "Harry Potter", 11);

        when(studentRepository.save(harry)).thenReturn(harry);

        Student actualResult = studentService.editStudent(harry);
        assertEquals(harry, actualResult);
    }

    @Test
    void getAll_success() {
        Student harry = new Student(1, "Harry Potter", 11);
        Student draco = new Student(2, "Draco Malfoy", 12);
        List<Student> expectedResult = List.of(harry, draco);

        when(studentRepository.findAll()).thenReturn(List.of(harry, draco));
        List<Student> actualResult = (List<Student>) studentService.getAll();
        assertEquals(expectedResult, actualResult);
    }
}