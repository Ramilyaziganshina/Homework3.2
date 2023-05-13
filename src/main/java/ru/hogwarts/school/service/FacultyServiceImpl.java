package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultyRepository;

import java.util.Collection;
import java.util.stream.Collectors;

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
        logger.warn("There is no faculty with id {} ", id);
        return facultyRepository.findById(id).orElse(null);
    }

    @Override
    public Faculty editFaculty(Faculty faculty) {
        logger.info("The method editFaculty is called");
        return facultyRepository.save(faculty);
    }

    @Override
    public void deleteFaculty(long id) {
        logger.info("The method deleteFaculty is called");
        logger.error("There is no faculty with id {} ", id);
        facultyRepository.deleteById(id);
    }

    @Override
    public Collection<Faculty> findByColour(String colour) {
        logger.info("The method findByColour is called");
        logger.error("There is no faculty with colour {} ", colour);
        return facultyRepository.findAll().stream()
                .filter(f -> f.getColour().equals(colour)).collect(Collectors.toList());
    }

    @Override
    public Collection<Faculty> getAll() {
        logger.info("The method getAll is called");
        return facultyRepository.findAll();
    }

    @Override
    public Collection<Faculty> findByColourIgnoreCase(String colour) {
        logger.info("The method findByColourIgnoreCase is called");
        logger.error("There is no faculty with colour {} ", colour);
        return facultyRepository.findByColourIgnoreCase(colour);
    }

    @Override
    public Collection<Faculty> findByNameIgnoreCase(String name) {
        logger.info("The method findByNameIgnoreCase is called");
        logger.error("There is no faculty with name {} ", name);
        return facultyRepository.findByNameIgnoreCase(name);
    }

    @Override
    public Collection<Student> findStudentsByFacultyId(long facultyId) {
        logger.info("The method findStudentsByFacultyId is called");
        logger.error("There is no students in faculty with id {} ", facultyId);
        return facultyRepository.findById(facultyId).orElse(null).getStudents();
    }

    @Override
    public Collection<Faculty> getFacultiesByColourAndNameIgnoreCase(String name, String colour) {
        logger.info("The method getFacultiesByColourAndNameIgnoreCase is called");
        logger.error("There is no faculty with name {} and colour {}", name, colour);
        return facultyRepository.getFacultiesByColourAndNameIgnoreCase(name, colour);
    }
}