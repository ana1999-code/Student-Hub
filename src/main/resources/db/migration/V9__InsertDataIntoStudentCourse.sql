INSERT INTO student_course (student_id, course_id, start_date, end_date, grade)
SELECT
    'b8fbeace-e4e2-435b-9ee1-82f9092fdfaf',
    course_id,
    TO_DATE('2023-03-01', 'YYYY-MM-DD') + (random() * 90) * '1 day'::interval,
    TO_DATE('2023-08-01', 'YYYY-MM-DD') + (random() * 90) * '1 day'::interval,
    FLOOR(random() * 11)
FROM course
ORDER BY random()
LIMIT 5;

INSERT INTO student_course (student_id, course_id, start_date, end_date, grade)
SELECT
    'd7e6cb09-97df-4da0-9991-d9be6923c8b8',
    course_id,
    TO_DATE('2023-03-01', 'YYYY-MM-DD') + (random() * 90) * '1 day'::interval,
    TO_DATE('2023-08-01', 'YYYY-MM-DD') + (random() * 90) * '1 day'::interval,
    FLOOR(random() * 11)
FROM course
ORDER BY random()
LIMIT 4;

INSERT INTO student_course (student_id, course_id, start_date, end_date, grade)
SELECT
    '1bbb6e41-e6dd-46d1-af5f-5919b5ca5f58',
    course_id,
    TO_DATE('2023-03-01', 'YYYY-MM-DD') + (random() * 90) * '1 day'::interval,
    TO_DATE('2023-08-01', 'YYYY-MM-DD') + (random() * 90) * '1 day'::interval,
    FLOOR(random() * 11)
FROM course
ORDER BY random()
LIMIT 3;

INSERT INTO student_course (student_id, course_id, start_date, end_date, grade)
SELECT
    '90ce1cb3-a7d8-432b-949d-324eb731df44',
    course_id,
    TO_DATE('2023-03-01', 'YYYY-MM-DD') + (random() * 90) * '1 day'::interval,
    TO_DATE('2023-08-01', 'YYYY-MM-DD') + (random() * 90) * '1 day'::interval,
    FLOOR(random() * 11)
FROM course
ORDER BY random()
LIMIT 6;

INSERT INTO student_course (student_id, course_id, start_date, end_date, grade)
SELECT
    'b50da259-45cd-4235-9339-8ecdeea08cfd',
    course_id,
    TO_DATE('2023-03-01', 'YYYY-MM-DD') + (random() * 90) * '1 day'::interval,
    TO_DATE('2023-08-01', 'YYYY-MM-DD') + (random() * 90) * '1 day'::interval,
    FLOOR(random() * 11)
FROM course
ORDER BY random()
LIMIT 1;

INSERT INTO student_course (student_id, course_id, start_date, end_date, grade)
SELECT
    '1eab1b35-cc5b-4cc9-9232-8446875bc419',
    course_id,
    TO_DATE('2023-03-01', 'YYYY-MM-DD') + (random() * 90) * '1 day'::interval,
    TO_DATE('2023-08-01', 'YYYY-MM-DD') + (random() * 90) * '1 day'::interval,
    FLOOR(random() * 11)
FROM course
ORDER BY random()
LIMIT 3;

INSERT INTO student_course (student_id, course_id, start_date, end_date, grade)
SELECT
    '3a10fea2-b3c3-4421-ab2b-068ffd027eac',
    course_id,
    TO_DATE('2023-03-01', 'YYYY-MM-DD') + (random() * 90) * '1 day'::interval,
    TO_DATE('2023-08-01', 'YYYY-MM-DD') + (random() * 90) * '1 day'::interval,
    FLOOR(random() * 11)
FROM course
ORDER BY random()
LIMIT 3;

INSERT INTO student_course (student_id, course_id, start_date, end_date, grade)
SELECT
    '0ce15acd-9085-447e-936d-ffc2ec83fc8f',
    course_id,
    TO_DATE('2023-03-01', 'YYYY-MM-DD') + (random() * 90) * '1 day'::interval,
    TO_DATE('2023-08-01', 'YYYY-MM-DD') + (random() * 90) * '1 day'::interval,
    FLOOR(random() * 11)
FROM course
ORDER BY random()
LIMIT 3;

INSERT INTO student_course (student_id, course_id, start_date, end_date, grade)
SELECT
    '5f289ea9-a166-49eb-9ff0-e9af7707f572',
    course_id,
    TO_DATE('2023-03-01', 'YYYY-MM-DD') + (random() * 90) * '1 day'::interval,
    TO_DATE('2023-08-01', 'YYYY-MM-DD') + (random() * 90) * '1 day'::interval,
    FLOOR(random() * 11)
FROM course
ORDER BY random()
LIMIT 1;

INSERT INTO student_course (student_id, course_id, start_date, end_date, grade)
SELECT
    'f3b06810-003d-4ea6-8551-c8224570bb9d',
    course_id,
    TO_DATE('2023-03-01', 'YYYY-MM-DD') + (random() * 90) * '1 day'::interval,
    TO_DATE('2023-08-01', 'YYYY-MM-DD') + (random() * 90) * '1 day'::interval,
    FLOOR(random() * 11)
FROM course
ORDER BY random()
LIMIT 6;

INSERT INTO student_course (student_id, course_id, start_date, end_date, grade)
SELECT
    '1aec3f45-3cd6-4c4c-a862-49b83d725065',
    course_id,
    TO_DATE('2023-03-01', 'YYYY-MM-DD') + (random() * 90) * '1 day'::interval,
    TO_DATE('2023-08-01', 'YYYY-MM-DD') + (random() * 90) * '1 day'::interval,
    FLOOR(random() * 11)
FROM course
ORDER BY random()
LIMIT 6;

INSERT INTO student_course (student_id, course_id, start_date, end_date, grade)
SELECT
    '7108d631-7164-43c1-9082-993ba257aed6',
    course_id,
    TO_DATE('2023-03-01', 'YYYY-MM-DD') + (random() * 90) * '1 day'::interval,
    TO_DATE('2023-08-01', 'YYYY-MM-DD') + (random() * 90) * '1 day'::interval,
    FLOOR(random() * 11)
FROM course
ORDER BY random()
LIMIT 2;

INSERT INTO student_course (student_id, course_id, start_date, end_date, grade)
SELECT
    '2ba4dba2-0ba3-4248-a6ea-c4b94c96e358',
    course_id,
    TO_DATE('2023-03-01', 'YYYY-MM-DD') + (random() * 90) * '1 day'::interval,
    TO_DATE('2023-08-01', 'YYYY-MM-DD') + (random() * 90) * '1 day'::interval,
    FLOOR(random() * 11)
FROM course
ORDER BY random()
LIMIT 4;

INSERT INTO student_course (student_id, course_id, start_date, end_date, grade)
SELECT
    '5d7ceba3-7a45-4df5-9c41-92e62b6087e7',
    course_id,
    TO_DATE('2023-03-01', 'YYYY-MM-DD') + (random() * 90) * '1 day'::interval,
    TO_DATE('2023-08-01', 'YYYY-MM-DD') + (random() * 90) * '1 day'::interval,
    FLOOR(random() * 11)
FROM course
ORDER BY random()
LIMIT 8;

INSERT INTO student_course (student_id, course_id, start_date, end_date, grade)
SELECT
    '0aaa44bb-6777-4e78-a9a2-90eb4989ab71',
    course_id,
    TO_DATE('2023-03-01', 'YYYY-MM-DD') + (random() * 90) * '1 day'::interval,
    TO_DATE('2023-08-01', 'YYYY-MM-DD') + (random() * 90) * '1 day'::interval,
    FLOOR(random() * 11)
FROM course
ORDER BY random()
LIMIT 5;

INSERT INTO student_course (student_id, course_id, start_date, end_date, grade)
SELECT
    '9c2c2aea-8640-4fea-b007-512cbeb22bbd',
    course_id,
    TO_DATE('2023-03-01', 'YYYY-MM-DD') + (random() * 90) * '1 day'::interval,
    TO_DATE('2023-08-01', 'YYYY-MM-DD') + (random() * 90) * '1 day'::interval,
    FLOOR(random() * 11)
FROM course
ORDER BY random()
LIMIT 1;

INSERT INTO student_course (student_id, course_id, start_date, end_date, grade)
SELECT
    'b2e4c2a1-b895-45cb-b079-100d14f14532',
    course_id,
    TO_DATE('2023-03-01', 'YYYY-MM-DD') + (random() * 90) * '1 day'::interval,
    TO_DATE('2023-08-01', 'YYYY-MM-DD') + (random() * 90) * '1 day'::interval,
    FLOOR(random() * 11)
FROM course
ORDER BY random()
LIMIT 5;

INSERT INTO student_course (student_id, course_id, start_date, end_date, grade)
SELECT
    '83c27df7-eda4-43ae-9b2c-69dc22553600',
    course_id,
    TO_DATE('2023-03-01', 'YYYY-MM-DD') + (random() * 90) * '1 day'::interval,
    TO_DATE('2023-08-01', 'YYYY-MM-DD') + (random() * 90) * '1 day'::interval,
    FLOOR(random() * 11)
FROM course
ORDER BY random()
LIMIT 3;

INSERT INTO student_course (student_id, course_id, start_date, end_date, grade)
SELECT
    '1e1d6812-7d57-4039-a91c-77e18c3cf09b',
    course_id,
    TO_DATE('2023-03-01', 'YYYY-MM-DD') + (random() * 90) * '1 day'::interval,
    TO_DATE('2023-08-01', 'YYYY-MM-DD') + (random() * 90) * '1 day'::interval,
    FLOOR(random() * 11)
FROM course
ORDER BY random()
LIMIT 2;

INSERT INTO student_course (student_id, course_id, start_date, end_date, grade)
SELECT
    '82de25a5-ccdf-41e3-b386-2ab6db96c3a1',
    course_id,
    TO_DATE('2023-03-01', 'YYYY-MM-DD') + (random() * 90) * '1 day'::interval,
    TO_DATE('2023-08-01', 'YYYY-MM-DD') + (random() * 90) * '1 day'::interval,
    FLOOR(random() * 11)
FROM course
ORDER BY random()
LIMIT 4;

INSERT INTO student_course (student_id, course_id, start_date, end_date, grade)
SELECT
    'a2c4e680-9a22-41a1-ae2c-c917163f09e1',
    course_id,
    TO_DATE('2023-03-01', 'YYYY-MM-DD') + (random() * 90) * '1 day'::interval,
    TO_DATE('2023-08-01', 'YYYY-MM-DD') + (random() * 90) * '1 day'::interval,
    FLOOR(random() * 11)
FROM course
ORDER BY random()
LIMIT 7;

INSERT INTO student_course (student_id, course_id, start_date, end_date, grade)
SELECT
    'd9ecb914-ee55-44e4-b0de-064370e8f3eb',
    course_id,
    TO_DATE('2023-03-01', 'YYYY-MM-DD') + (random() * 90) * '1 day'::interval,
    TO_DATE('2023-08-01', 'YYYY-MM-DD') + (random() * 90) * '1 day'::interval,
    FLOOR(random() * 11)
FROM course
ORDER BY random()
LIMIT 3;

INSERT INTO student_course (student_id, course_id, start_date, end_date, grade)
SELECT
    '08828f30-3539-4bd7-b01c-018f7f671340',
    course_id,
    TO_DATE('2023-03-01', 'YYYY-MM-DD') + (random() * 90) * '1 day'::interval,
    TO_DATE('2023-08-01', 'YYYY-MM-DD') + (random() * 90) * '1 day'::interval,
    FLOOR(random() * 11)
FROM course
ORDER BY random()
LIMIT 1;

INSERT INTO student_course (student_id, course_id, start_date, end_date, grade)
SELECT
    'efcb4c83-fd53-4ad3-9abd-ea93b7f09ee1',
    course_id,
    TO_DATE('2023-03-01', 'YYYY-MM-DD') + (random() * 90) * '1 day'::interval,
    TO_DATE('2023-08-01', 'YYYY-MM-DD') + (random() * 90) * '1 day'::interval,
    FLOOR(random() * 11)
FROM course
ORDER BY random()
LIMIT 5;

INSERT INTO student_course (student_id, course_id, start_date, end_date, grade)
SELECT
    '7ef45d41-0474-4c92-8741-12cb8478822a',
    course_id,
    TO_DATE('2023-03-01', 'YYYY-MM-DD') + (random() * 90) * '1 day'::interval,
    TO_DATE('2023-08-01', 'YYYY-MM-DD') + (random() * 90) * '1 day'::interval,
    FLOOR(random() * 11)
FROM course
ORDER BY random()
LIMIT 3;