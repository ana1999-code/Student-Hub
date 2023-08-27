    CREATE TABLE IF NOT EXISTS course(
        course_id UUID NOT NULL PRIMARY KEY,
        name VARCHAR(255) NOT NULL UNIQUE,
        description TEXT NOT NULL,
        department VARCHAR(255),
        teacher_name VARCHAR(100)
    );