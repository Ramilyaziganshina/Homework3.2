package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Override
    public Student addStudent(Student student) {
        logger.info("The method addStudent is called");
        Student studentForAdd = studentRepository.save(student);
        logger.debug("The student {} is added", studentForAdd);
        return studentForAdd;
    }

    @Override
    public Student findStudent(long id) {
        logger.info("The method findStudent is called with id {}", id);
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null) {
            logger.error("There is not student with id {}", id);
        }
        return student;
    }

    @Override
    public Student editStudent(Student student) {
        logger.info("The method editStudent is called");
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(long id) {
        logger.info("The method deleteStudent is called");
        studentRepository.deleteById(id);
    }

    @Override
    public Collection<Student> findByAge(int age) {
        logger.info("The method findByAge is called");
        Collection<Student> students = studentRepository.findAll().stream()
                .filter(s -> s.getAge() == age).collect(Collectors.toList());
        if (students == null) {
            logger.error("There is not student with age {}", age);
        }
        return students;
    }

    @Override
    public Collection<Student> getAll() {
        logger.info("The method getAll is called");
        return studentRepository.findAll();
    }

    @Override
    public Collection<Student> findByAgeBetween(Integer age, Integer age2) {
        logger.info("The method findByAgeBetween is called");
        Collection<Student> students = studentRepository.findStudentsByAgeBetween(age, age2);
        if (students == null) {
            logger.error("There is not student with age between {} and {}", age, age2);
        }
        return students;
    }

    @Override
    public Faculty findFacultyByStudent(long studentId) {
        logger.info("The method findFacultyByStudent is called");
        Faculty faculty = studentRepository.findById(studentId).orElse(null).getFaculty();
        if (faculty == null) {
            logger.error("Student with id {} is not assigned faculty", studentId);
        }
        return faculty;
    }

    @Override
    public double getAverageAge() {
        logger.info("The method getAverageAge is called");
        double averageAge = studentRepository.getAverageAge();
        if (averageAge == 0.0) {
            logger.error("There is no students");
        }
        return averageAge;
    }

    @Override
    public int getCountOfStudents() {
        logger.info("The method getCountOfStudents is called");
        return studentRepository.getCountOfStudents();
    }

    @Override
    public Collection<Student> getFiveLastStudens() {
        logger.info("The method getFiveLastStudents is called");
        return studentRepository.getFiveLastStudents();
    }

    @Override
    public Collection<Student> getStudentsByName(String name) {
        logger.info("The method getStudentsByName is called");
        Collection<Student> students = studentRepository.getStudentsByName(name);
        if (students == null) {
            logger.error("There is not student with name {}", name);
        }
        return students;
    }
}