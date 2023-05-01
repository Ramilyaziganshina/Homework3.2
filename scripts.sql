scripts.sql
select *
from student;
select *
from student
where age between 30 and 60;
select student.name
from student;
select *
from student
where name like '%o%';
select *
from student
where age < student.id;
select *
from student
order by age;