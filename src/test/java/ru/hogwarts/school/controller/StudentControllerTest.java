package ru.hogwarts.school.controller;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.AvatarRepository;
import ru.hogwarts.school.repositories.FacultyRepository;
import ru.hogwarts.school.repositories.StudentRepository;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyRepository facultyRepository;

    @MockBean
    private StudentRepository studentRepository;

    @MockBean
    private AvatarRepository avatarRepository;

    @SpyBean
    private FacultyService facultyService;

    @SpyBean
    private StudentService studentService;

    @SpyBean
    private AvatarService avatarService;

    @InjectMocks
    private FacultyController facultyController;

    @Test
    void getStudent() throws Exception {
        final String name = "Harry";
        final int age = 11;
        final long id = 1;

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);

        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));
    }

    @Test
    void createStudent() throws Exception {
        final String name = "Harry";
        final int age = 11;
        final long id = 1;
        JSONObject studentObject = new JSONObject();
        studentObject.put("name", name);
        studentObject.put("age", age);

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);

        when(studentRepository.save(any(Student.class))).thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/student")
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));
    }

    @Test
    void editStudent() throws Exception {
        final String name = "Harry";
        final int age = 11;
        final long id = 0;
        JSONObject studentObject = new JSONObject();
        studentObject.put("name", name);
        studentObject.put("age", age);

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);

        when(studentRepository.save(any(Student.class))).thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/student")
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));
    }

    @Test
    void deleteStudent() {
    }

    @Test
    void findStudentsByAgeBetween() throws Exception {
        final String name = "Harry";
        final int sage = 11;
        final long id = 1;
        final int age = 10;
        final int age2 = 12;

        Collection<Student> students = List.of(new Student[]{
                new Student(id, name, sage)});

        JSONObject studentObject = new JSONObject();
        studentObject.put("name", name);
        studentObject.put("age", sage);

        when(studentRepository.findStudentsByAgeBetween(age, age2)).thenReturn(students);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student" + age + "/" + age2)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpectAll(status().isOk());
    }

    @Test
    void findFacultyByStudentId() throws Exception {
        final String name = "Huffelpuff";
        final String colour = "yellow";
        final long id = 1;

        final long studentId = 1;
        final String studentName = "Penelopa";
        final int age = 13;

        Faculty faculty = new Faculty(id, name, colour);
        Collection<Faculty> faculties = null;
        faculties.add(faculty);

        JSONObject studentObject = new JSONObject();
        studentObject.put("id", studentId);
        studentObject.put("name", studentName);
        studentObject.put("age", age);

        JSONObject facultyObject = new JSONObject();
        facultyObject.put("id", id);
        facultyObject.put("name", name);
        facultyObject.put("colour", colour);

        when(studentRepository.findById(studentId).orElse(null).getFaculty()).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/" + studentId + "/students")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpectAll(status().isOk());
    }
}