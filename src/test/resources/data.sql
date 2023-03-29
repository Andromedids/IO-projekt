INSERT INTO student(student_name, student_surname) VALUES
('Jan', 'Kowalski'),
('Anna', 'Kowalska')
;
INSERT INTO presenter(presenter_name, presenter_surname) VALUES
('Anna', 'Wojcik'),
('Pawel', 'Lipski')
;
INSERT INTO workshop(presenter_id, workshop_title, workshop_description, workshop_date) VALUES
(1, 'CRUDowa aplikacja w 45minut', 'postawimy szybko apke javova', '2023-03-30T09:10:25Z'),
(2, 'Git Machete', 'Organizator repozytoriow i narzedzie do automatyzacji rebase i merge', '2023-03-30T13:30:00Z')
;
INSERT INTO workshop_attendance(workshop_id, student_id) VALUES
(1, 1),
(1, 2),
(2, 1),
(2, 2)
;