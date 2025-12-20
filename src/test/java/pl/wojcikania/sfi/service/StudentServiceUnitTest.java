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
    private StudentEntity janKowalski;
    @Mock
    private StudentEntity annaKowalska;
    @Mock
    private WorkshopEntity programmingWorkshop;
    @Mock
    private WorkshopEntity networkingWorkshop;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        studentService = new StudentService(studentRepository);
    }

    @Test
    public void shouldReturnAllStudents() {
        //given
        List<StudentEntity> studentsFromDatabase = new ArrayList<>();
        studentsFromDatabase.add(janKowalski);
        studentsFromDatabase.add(annaKowalska);
        when(janKowalski.getStudentName()).thenReturn("Jan");
        when(janKowalski.getStudentSurname()).thenReturn("Kowalski");
        when(janKowalski.getStudentId()).thenReturn(1L);
        List<WorkshopEntity> workshopForThisStudent = new ArrayList<>();
        workshopForThisStudent.add(programmingWorkshop);
        workshopForThisStudent.add(networkingWorkshop);
        when(janKowalski.getWorkshopsForThisStudent()).thenReturn(workshopForThisStudent);
        when(annaKowalska.getStudentName()).thenReturn("Anna");
        when(annaKowalska.getStudentSurname()).thenReturn("Kowalska");
        when(annaKowalska.getStudentId()).thenReturn(2L);
        when(annaKowalska.getWorkshopsForThisStudent()).thenReturn(workshopForThisStudent);
        when(studentRepository.findAll()).thenReturn(studentsFromDatabase);
        when(programmingWorkshop.getWorkshopId()).thenReturn(1L);
        when(networkingWorkshop.getWorkshopId()).thenReturn(2L);

        //when
        List<Student> students = studentService.getStudents();

        //then
        Assertions.assertEquals(2, students.size());
        List<Student> expected = List.of(
               Student.builder()
                       .studentId(1L)
                       .studentName("Jan")
                       .studentSurname("Kowalski")
                       .workshopsForThisStudent(List.of(1L, 2L))
                       .build(),
                Student.builder()
                        .studentId(2L)
                        .studentName("Anna")
                        .studentSurname("Kowalska")
                        .workshopsForThisStudent(List.of(1L, 2L))
                        .build()

        );
        Assertions.assertEquals(expected, students);

    }

}