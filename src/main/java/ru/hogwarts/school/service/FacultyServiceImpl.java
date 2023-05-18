package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultyRepository;

import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.maxBy;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    Logger logger = LoggerFactory.getLogger(FacultyServiceImpl.class);

    @Override
    public Faculty addFaculty(Faculty faculty) {
        logger.info("The method addFaculty is called");
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty findFaculty(long id) {
        logger.info("The method findFaculty is called");
        Faculty faculty = facultyRepository.findById(id).orElse(null);
        if (faculty == null) {
            logger.warn("There is no faculty with id {} ", id);
        }
        return faculty;
    }

    @Override
    public Faculty editFaculty(Faculty faculty) {
        logger.info("The method editFaculty is called");
        return facultyRepository.save(faculty);
    }

    @Override
    public void deleteFaculty(long id) {
        logger.info("The method deleteFaculty is called");
        facultyRepository.deleteById(id);
    }

    @Override
    public Collection<Faculty> findByColour(String colour) {
        logger.info("The method findByColour is called");
        Collection<Faculty> faculties = facultyRepository.findAll().stream()
                .filter(f -> f.getColour().equals(colour)).collect(Collectors.toList());
        if (faculties == null) {
            logger.error("There is no faculty with colour {} ", colour);
        }
        return faculties;
    }

    @Override
    public Collection<Faculty> getAll() {
        logger.info("The method getAll is called");
        return facultyRepository.findAll();
    }

    @Override
    public Collection<Faculty> findByColourIgnoreCase(String colour) {
        logger.info("The method findByColourIgnoreCase is called");
        Collection<Faculty> faculties = facultyRepository.findByColourIgnoreCase(colour);
        if (faculties == null) {
            logger.error("There is no faculty with colour {} ", colour);
        }
        return faculties;
    }

    @Override
    public Collection<Faculty> findByNameIgnoreCase(String name) {
        logger.info("The method findByNameIgnoreCase is called");
        Collection<Faculty> faculties = facultyRepository.findByNameIgnoreCase(name);
        if (faculties == null) {
            logger.error("There is no faculty with name {} ", name);
        }
        return faculties;
    }

    @Override
    public Collection<Student> findStudentsByFacultyId(long facultyId) {
        logger.info("The method findStudentsByFacultyId is called");
        Collection<Student> students = facultyRepository.findById(facultyId).orElse(null).getStudents();
        if (students == null) {
            logger.error("There is no students in faculty with id {} ", facultyId);
        }
        return students;
    }

    @Override
    public Collection<Faculty> getFacultiesByColourAndNameIgnoreCase(String name, String colour) {
        logger.info("The method getFacultiesByColourAndNameIgnoreCase is called");
        Collection<Faculty> faculties = facultyRepository.getFacultiesByColourAndNameIgnoreCase(name, colour);
        if (faculties == null) {
            logger.error("There is no faculty with name {} and colour {}", name, colour);
        }
        return faculties;
    }

    @Override
    public Optional<String> theLongestNameOfFaculty() {
        Optional<String> theLongestName =
                facultyRepository.findAll().stream()
                        .map(Faculty::getName)
                        .collect(maxBy(Comparator.naturalOrder()));
        return theLongestName;
    }
}