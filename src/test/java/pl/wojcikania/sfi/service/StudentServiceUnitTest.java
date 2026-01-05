package pl.wojcikania.sfi.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.wojcikania.sfi.domain.StudentEntity;
import pl.wojcikania.sfi.domain.WorkshopEntity;
import pl.wojcikania.sfi.dto.Student;
import pl.wojcikania.sfi.repositories.StudentRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class StudentServiceUnitTest {
    private StudentService studentService;
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private StudentEntity mateuszJ;
    @Mock
    private StudentEntity krystianB;
    @Mock
    private WorkshopEntity java;
    @Mock
    private WorkshopEntity python;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        studentService = new StudentService(studentRepository);
    }


    @Test
    public void shouldReturnALLStudents() {
        //given@

        List<StudentEntity> studentsFromDatabase = new ArrayList<>();
        studentsFromDatabase.add(mateuszJ);
        studentsFromDatabase.add(krystianB);
        List<WorkshopEntity> workshopForThisStudent = new ArrayList<>();
        workshopForThisStudent.add(java);
        workshopForThisStudent.add(python);
        when(studentRepository.findAll()).thenReturn(studentsFromDatabase);
        when(mateuszJ.getStudentName()).thenReturn("Mateusz");
        when(mateuszJ.getStudentSurname()).thenReturn("J");
        when(mateuszJ.getStudentId()).thenReturn(1L);
        when(mateuszJ.getWorkshopsForThisStudent()).thenReturn(workshopForThisStudent);
        when(krystianB.getStudentName()).thenReturn("Krystian");
        when(krystianB.getStudentSurname()).thenReturn("B");
        when(krystianB.getStudentId()).thenReturn(2L);
        when(krystianB.getWorkshopsForThisStudent()).thenReturn(workshopForThisStudent);
        when(java.getWorkshopId()).thenReturn(1L);
        when(python.getWorkshopId()).thenReturn(2L);
        //when
        List<Student> students = studentService.getStudents();
        //then

        Assertions.assertEquals(2, students.size());
        List<Student> expected = List.of(
                Student.builder()
                        .studentId(1L)
                        .studentName("Mateusz")
                        .studentSurname("J")
                        .workshopsForThisStudent(List.of(1L, 2L))
                        .build(),
                Student.builder()
                        .studentId(2L)
                        .studentName("Krystian")
                        .studentSurname("B")
                        .workshopsForThisStudent(List.of(1L, 2L))
                        .build()
        );
        Assertions.assertEquals(expected, students);
    }
}
