package pl.wojcikania.sfi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static pl.wojcikania.sfi.repositories.StudentRepositoryTest.ID_ONE;
import static pl.wojcikania.sfi.repositories.StudentRepositoryTest.NOT_EXISTENT_ID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.wojcikania.sfi.domain.PresenterEntity;
import pl.wojcikania.sfi.domain.StudentEntity;
import pl.wojcikania.sfi.domain.WorkshopEntity;
import pl.wojcikania.sfi.dto.Student;
import pl.wojcikania.sfi.repositories.StudentRepository;

import java.util.ArrayList;
import java.util.List;

public class DobryStudentServiceUnitTest {

    @Mock
    private StudentService studentService;
    @Mock
    StudentRepository studentRepository;
    @Mock
    private StudentEntity meStudent;
    @Mock
    private StudentEntity heStudent;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        studentService = new StudentService(studentRepository);
    }


    @Test
    public void getStudents_shouldReturnAllStudents() {
        //given
        prepareStudentEntity(meStudent, 123, "Romek", "Grzeszczyn");
        prepareStudentEntity(heStudent, 456, "Andrzej", "Anedy");
        List<StudentEntity> studentEntities = List.of(meStudent, heStudent);
        when(studentRepository.findAll()).thenReturn(studentEntities);
        //when
        List<Student> student = studentService.getStudents();
        //then
        Assertions.assertEquals(2, student.size());
        assertStudent(student.get(0),123,"Romek","Grzeszczyn");
        assertStudent(student.get(1),456,"Andrzej","Anedy");
    }
    private void assertStudent(Student student, int id, String firstName, String lastName) {
        Assertions.assertEquals(id, student.getStudentId());
        Assertions.assertEquals(firstName, student.getStudentName());
        Assertions.assertEquals(lastName, student.getStudentSurname());
        Assertions.assertEquals(new ArrayList<>(), student.getWorkshopsForThisStudent());
    }

    private void prepareStudentEntity(StudentEntity studentEntity, long studentId, String name, String surname) {
        when(studentEntity.getStudentId()).thenReturn(studentId);
        when(studentEntity.getStudentName()).thenReturn(name);
        when(studentEntity.getStudentSurname()).thenReturn(surname);
        when(studentEntity.getWorkshopsForThisStudent()).thenReturn(new ArrayList<>());}
}


