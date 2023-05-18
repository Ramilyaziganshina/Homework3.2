select student.name, student.age, faculty.name
from student INNER JOIN faculty ON student.faculty_id = faculty.id;
select student.name, student.age, faculty.name, avatar.file_path
from student
         INNER JOIN faculty ON student.faculty_id = faculty.id
         INNER JOIN avatar on student.id = avatar.student_id;