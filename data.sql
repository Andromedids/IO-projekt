CREATE TABLE student(student_id SERIAL PRIMARY KEY NOT NULL, student_name VARCHAR, student_surname VARCHAR);
CREATE TABLE presenter(presenter_id SERIAL PRIMARY KEY NOT NULL, presenter_name VARCHAR, presenter_surname VARCHAR);

CREATE TABLE workshop(workshop_id SERIAL PRIMARY KEY NOT NULL,
presenter_id INT, workshop_title VARCHAR,
 workshop_description VARCHAR, workshop_date TIMESTAMP,
FOREIGN KEY (presenter_id) REFERENCES presenter(presenter_id));

CREATE TABLE workshop_attendance(workshop_id INT, student_id INT,
PRIMARY KEY(workshop_id, student_id),
FOREIGN KEY (workshop_id) REFERENCES workshop(workshop_id),
FOREIGN KEY (student_id) REFERENCES student(student_id));

INSERT INTO student(student_name, student_surname) VALUES
('Jan', 'Kowalski'),
('Anna', 'Kowalska'),
('Marianna','Sochacka'),
('Marek', 'Wojcik'),
('Andrzej', 'Nowak'),
('Natalia', 'Wisniewska'),
('Grzegorz', 'Lewandowski'),
('Szymon', 'Mazur'),
('Karolina', 'Kwiatkowska'),
('Jozef', 'Wojciechowski');
INSERT INTO presenter(presenter_name, presenter_surname) VALUES
('Anna', 'Wojcik'),
('Pawel', 'Lipski'),
('Wojciech', 'Jasinski'),
('Piotr', 'Faliszewski');
INSERT INTO workshop(presenter_id, workshop_title, workshop_description, workshop_date) VALUES
(1, 'CRUDowa aplikacja w 45minut', 'postawimy szybko apke javova', '2023-03-30T11:10:25'),
(2, 'Git Machete', 'Organizator repozytoriow i narzedzie do automatyzacji rebase i merge', '2023-03-30T15:30:00'),
(3, 'Jak rozmawiac ze sztuczna inteligencja', 'Pokaze, jak korzystac z dostepnych narzedzi','2023-03-30T10:00:25'),
(4, 'Od Formuly 1 do budzetow obywatleskich', 'Opowiem o kilku osiagnieciach w zakresie algorytmiki', '2023-03-30T10:00:25');
INSERT INTO workshop_attendance(workshop_id, student_id) VALUES
(1, 1),
(1, 2),
(1, 3),
(2, 4),
(2, 5),
(2, 6),
(3, 7),
(3, 8),
(3, 9),
(4, 10),
(4, 1),
(4, 2),
(4, 3);

CREATE SEQUENCE IF NOT EXISTS presenter_seq;
CREATE SEQUENCE IF NOT EXISTS workshop_seq;
CREATE SEQUENCE IF NOT EXISTS workshop_attendance_seq;
CREATE SEQUENCE IF NOT EXISTS student_seq;

SELECT setval('presenter_seq', (SELECT MAX(p.presenter_id) FROM presenter p));
SELECT setval('workshop_seq', (SELECT MAX(w.workshop_id) FROM workshop w));
SELECT setval('student_seq', (SELECT MAX(s.student_id) FROM student s));
