ALTER TABLE student_course
DROP CONSTRAINT student_course_student_id_fkey;

ALTER TABLE student_course
ADD CONSTRAINT student_course_student_id_fkey
FOREIGN KEY (student_id)
REFERENCES student(student_id)
ON DELETE CASCADE;