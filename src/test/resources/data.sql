delete from student;
delete from course;
delete from student_course;

insert into student (student_id, first_name, last_name, email, gender) values ('efcb4c83-fd53-4ad3-9abd-ea93b7f09ee1', 'John', 'Smith', 'jones0@mail.com', 'MALE');
insert into student (student_id, first_name, last_name, email, gender) values ('7ef45d41-0474-4c92-8741-12cb8478822a', 'Maria', 'Forest', 'mariaf1@mail.com', 'FEMALE');

insert into course (course_id, name, description, department, teacher_name) values('83c27df7-eda4-43ae-9b2c-69dc22553600', 'Java', 'Java', 'IT', 'Merry');
insert into course (course_id, name, description, department, teacher_name) values('a2c4e680-9a22-41a1-ae2c-c917163f09e1', 'SQL', 'SQL', 'IT', 'Tom');

insert into student_course (student_id, course_id, start_date, end_date, grade) values('efcb4c83-fd53-4ad3-9abd-ea93b7f09ee1', '83c27df7-eda4-43ae-9b2c-69dc22553600', '2023-01-01', '2023-09-01', 8);
insert into student_course (student_id, course_id, start_date, end_date, grade) values('efcb4c83-fd53-4ad3-9abd-ea93b7f09ee1', 'a2c4e680-9a22-41a1-ae2c-c917163f09e1', '2023-06-04', '2024-06-04', 7);
