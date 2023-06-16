CREATE TABLE course
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(35) NOT NULL UNIQUE,
    instructor_id INT,
    FOREIGN KEY (instructor_id)
        REFERENCES instructor(id)
);