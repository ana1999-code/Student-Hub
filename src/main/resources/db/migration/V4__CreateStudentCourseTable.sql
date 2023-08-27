CREATE TABLE IF NOT EXISTS student_course(
    student_id UUID NOT NULL REFERENCES student (student_id),
    course_id UUID NOT NULL REFERENCES course (course_id),
    start_date DATE NOT NULL,
    end_date DATE,
    grade INTEGER CHECK(grade >= 0 AND grade <= 10),
    UNIQUE(student_id, course_id)
);