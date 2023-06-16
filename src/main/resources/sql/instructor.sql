CREATE TABLE instructor_detail
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    youtube_channel VARCHAR(35) DEFAULT NULL,
    hobby VARCHAR(35) DEFAULT NULL
);

CREATE TABLE instructor
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(35),
    last_name VARCHAR(35),
    email VARCHAR(35),
    instructor_detail_id INT DEFAULT NULL,
    FOREIGN KEY (instructor_detail_id)
        REFERENCES instructor_detail(id)
        ON DELETE CASCADE
);