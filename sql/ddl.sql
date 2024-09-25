CREATE TYPE role_enum AS ENUM ('STUDENT', 'FACULTY_MEMBER', 'ADMINISTRATOR');

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role role_enum NOT NULL,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(15)
);


CREATE TABLE department (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT
);

CREATE TABLE student_profile (
    user_id INTEGER PRIMARY KEY,
    photo VARCHAR(255),
    department_id INTEGER NOT NULL,
    year VARCHAR(50),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (department_id) REFERENCES department(id) ON DELETE CASCADE
);

CREATE TABLE faculty_profile (
    user_id INTEGER PRIMARY KEY,
    photo VARCHAR(255),
    department_id INTEGER NOT NULL,
    office_hours VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (department_id) REFERENCES department(id) ON DELETE CASCADE
);

CREATE TABLE administrator_profile (
    user_id INTEGER PRIMARY KEY,
    photo VARCHAR(255),
    department_id INTEGER NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (department_id) REFERENCES department(id) ON DELETE CASCADE
);

SELECT constraint_name
FROM information_schema.table_constraints
WHERE table_name = ''
AND constraint_type = 'PRIMARY KEY';

ALTER TABLE student_profile
DROP CONSTRAINT "student_profile_pkey";

ALTER TABLE student_profile
ADD PRIMARY KEY (user_id, department_id);

select * from faculty_profile;
SELECT * FROM users;



