package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;

public interface FacultyService {
    Faculty addFaculty(Faculty faculty);
    Faculty findFaculty(long id);
    Faculty editFaculty(Faculty faculty);
    void deleteFaculty(long id);
    Collection<Faculty> findByColour(String colour);
    Collection<Faculty> getAll();
    Collection<Faculty> findByColourIgnoreCase(String colour);
    Collection<Faculty> findByNameIgnoreCase(String name);
    Collection<Student> findStudentsByFacultyId(long facultyId);
    Collection<Faculty> getFacultiesByColourAndNameIgnoreCase(String name, String colour);
}
