package ru.hogwarts.school.controller;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultyRepository;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = FacultyController.class)
class FacultyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyRepository facultyRepository;

    @SpyBean
    private FacultyService facultyService;

    @Test
    void getFaculty() throws Exception {
        final String name = "Huffelpuff";
        final String colour = "yellow";
        final long id = 1;

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColour(colour);

        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.colour").value(colour));
    }

    @Test
    void createFaculty() throws Exception {
        final String name = "Huffelpuff";
        final String colour = "yellow";
        final long id = 0;
        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", name);
        facultyObject.put("colour", colour);

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColour(colour);

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculty")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.colour").value(colour));
    }

    @Test
    void editFaculty() throws Exception {
        final String name = "Huffelpuff";
        final String colour = "yellow";
        final long id = 0;

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColour(colour);

        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.colour").value(colour));
    }

    @Test
    void deleteFaculty() throws Exception {
        final String name = "Huffelpuff";
        final String colour = "yellow";
        final long id = 1;

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColour(colour);

        JSONObject facultyObject = new JSONObject();
        facultyObject.put("id", id);
        facultyObject.put("name", name);
        facultyObject.put("colour", colour);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/faculty/" + id)
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void findFacultiesByColourOrName_getAll() throws Exception {
        final String name = "Huffelpuff";
        final String colour = "yellow";
        final long id = 1;

        Collection<Faculty> faculties = List.of(new Faculty[]{
                new Faculty(id, name, colour)});

        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", name);
        facultyObject.put("colour", colour);

        when(facultyRepository.findAll()).thenReturn((List<Faculty>) faculties);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpectAll(status().isOk());
    }

    @Test
    void findFacultiesByColourOrName_getByColour() throws Exception {
        final String name = "Huffelpuff";
        final String colour = "yellow";
        final long id = 1;

        Collection<Faculty> faculties = List.of(new Faculty[]{
                new Faculty(id, name, colour)
        });

        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", name);
        facultyObject.put("colour", colour);

        when(facultyRepository.findByColourIgnoreCase(colour)).thenReturn(faculties);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty?colour=" + colour)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpectAll(status().isOk());
    }

    @Test
    void findFacultiesByColourOrName_getByName() throws Exception {
        final String name = "Huffelpuff";
        final String colour = "yellow";
        final long id = 1;

        Collection<Faculty> faculties = List.of(new Faculty[]{
                new Faculty(id, name, colour)});

        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", name);
        facultyObject.put("colour", colour);

        when(facultyRepository.findByNameIgnoreCase(name)).thenReturn(faculties);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty?name=" + name)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpectAll(status().isOk());
    }

    @Test
    void findStudentsByFacultyId() throws Exception {
        final String name = "Huffelpuff";
        final String colour = "yellow";
        final long id = 1;

        final long studentId = 1;
        final String studentName = "Penelopa";
        final int age = 13;

        Student student = new Student(studentId, studentName, age);
        Collection<Student> students = null;
        students.add(student);

        JSONObject studentObject = new JSONObject();
        studentObject.put("id", studentId);
        studentObject.put("name", studentName);
        studentObject.put("age", age);

        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", name);
        facultyObject.put("colour", colour);

        when(facultyRepository.findById(id).orElse(null).getStudents()).thenReturn(students);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/" + id + "/faculty")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpectAll(status().isOk());
    }
}