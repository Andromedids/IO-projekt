CREATE TABLE student(student_id SERIAL PRIMARY KEY NOT NULL, student_name VARCHAR, student_surname VARCHAR);
CREATE TABLE presenter(presenter_id SERIAL PRIMARY KEY NOT NULL, presenter_name VARCHAR, presenter_surname VARCHAR);
CREATE TABLE workshop(workshop_id SERIAL PRIMARY KEY NOT NULL, presenter_id INT, workshop_title VARCHAR, workshop_description VARCHAR, workshop_date TIMESTAMP,
FOREIGN KEY (presenter_id) REFERENCES presenter(presenter_id));

CREATE TABLE workshop_attendance(workshop_id INT, student_id INT,
PRIMARY KEY(workshop_id, student_id),
FOREIGN KEY (workshop_id) REFERENCES workshop(workshop_id),
FOREIGN KEY (student_id) REFERENCES student(student_id));