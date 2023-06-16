CREATE TABLE review
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    comment VARCHAR(35),
    course_id INT,
    FOREIGN KEY (course_id)
        REFERENCES course(id)
        ON DELETE CASCADE
);