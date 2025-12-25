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

import java.util.List;

import static org.mockito.Mockito.when;

class StudentServiceUnitTest {

    @Mock
    private StudentRepository studentRepository;
    @Mock
    private StudentEntity student1;
    @Mock
    private StudentEntity student2;
    @Mock
    private WorkshopEntity workshopEntity1;
    @Mock
    private WorkshopEntity workshopEntity2;

    private int numberOfTestStudents = 0;

    private StudentService studentService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        studentService = new StudentService(studentRepository);
    }

    @Test
    public void shouldReturnAllStudents() {
        //given
        List<StudentEntity> studentEntities = List.of(
            student1, student2
        );

        List<WorkshopEntity> workshopEntitiesForStudent1 = List.of(
                workshopEntity1, workshopEntity2
        );
        List<WorkshopEntity> workshopEntitiesForStudent2 = List.of(
                workshopEntity1
        );

        when(studentRepository.findAll()).thenReturn(studentEntities);
        when(workshopEntity1.getWorkshopId()).thenReturn(1L);
        when(workshopEntity2.getWorkshopId()).thenReturn(2L);

        prepareStudentEntity(student1, workshopEntitiesForStudent1, 1);
        prepareStudentEntity(student2, workshopEntitiesForStudent2, 2);

        //when
        List<Student> students = studentService.getStudents();

        //then
        Assertions.assertEquals(2, students.size());

        List<Student> expected = List.of(
                Student.builder()
                        .studentName("TestName1")
                        .studentSurname("TestSurname1")
                        .studentId(0L)
                        .workshopsForThisStudent(List.of(0L, 1L))
                        .build(),
                Student.builder()
                        .studentName("TestName2")
                        .studentSurname("TestSurname2")
                        .studentId(1L)
                        .workshopsForThisStudent(List.of(0L))
                        .build()
        );

        Assertions.assertEquals(expected, students);
    }

    private void prepareStudentEntity(
            StudentEntity student,
            List<WorkshopEntity> workshopForStudent,
            int id
    ){
        when(student.getStudentName()).thenReturn("TestName" + id);
        when(student.getStudentSurname()).thenReturn("TestSurname" + id);
        when(student.getStudentId()).thenReturn((long) numberOfTestStudents++);

        when(student.getWorkshopsForThisStudent()).thenReturn(workshopForStudent);
        for(int i = 0; i < workshopForStudent.size(); i++){
            when(workshopForStudent.get(i).getWorkshopId()).thenReturn((long)i);
        }
    }
}