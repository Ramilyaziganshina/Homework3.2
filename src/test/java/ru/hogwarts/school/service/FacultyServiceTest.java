package ru.hogwarts.school.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repositories.FacultyRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {FacultyService.class})
@ExtendWith(SpringExtension.class)
class FacultyServiceTest {

    @Autowired
    public FacultyService facultyService;

    @MockBean
    public FacultyRepository facultyRepository;

    @Test
    void addFaculty_success() {
        long id = 1;
        String name = "Griffindor";
        String color = "red";
        Faculty griffindor = new Faculty(id, name, color);

        when(facultyRepository.save(griffindor)).thenReturn(griffindor);

        Faculty actualResult = facultyService.addFaculty(id, name, color);

        assertEquals(griffindor, actualResult);
    }

    @Test
    void findFaculty_success() {
        long id = 1;
        Faculty griffindor = new Faculty(1, "Griffindor", "red");

        when(facultyRepository.findById(id)).thenReturn(Optional.of(griffindor));

        Faculty actualResult = facultyService.findFaculty(id);

        assertEquals(griffindor, actualResult);
    }

    @Test
    void editFaculty_success() {
        Faculty griffindor = new Faculty(1, "Griffindor", "red");

        when(facultyRepository.save(griffindor)).thenReturn(griffindor);

        Faculty actualResult = facultyService.editFaculty(griffindor);
        assertEquals(griffindor, actualResult);
    }

    @Test
    void getAll_success() {
        Faculty griffindor = new Faculty(1, "Griffindor", "red");
        Faculty slyzerin = new Faculty(2, "Slyzerin", "green");
        List<Faculty> expectedResult = List.of(griffindor, slyzerin);

        when(facultyRepository.findAll()).thenReturn(List.of(griffindor, slyzerin));

        List<Faculty> actualResult = (List<Faculty>) facultyService.getAll();

        Assertions.assertEquals(expectedResult, actualResult);
    }
}