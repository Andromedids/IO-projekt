package pl.wojcikania.sfi.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.wojcikania.sfi.domain.StudentEntity;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class StudentRepositoryTest {
  public static final long ID_ONE = 1L;
  public static final long NOT_EXISTENT_ID = 3L;

  @Autowired
  private StudentRepository studentRepository;

  @Test
//  @Transactional
  public void shouldGetAllStudents() {
    //given & when
    var students = studentRepository.findAll();

    //then
    assertEquals(2, students.size());
    assertUser(students.get(0));
    assertOtherUser(students.get(1));
  }

  @Test
//  @Transactional
  public void shouldGetOneStudent() {
    //given & when
    var student = studentRepository.findById(ID_ONE);

    //then
    assertNotNull(student.get());
    assertUser(student.get());
  }

  @Test
  public void shouldReturnNullWhenNoStudentWithSuchId() {
    //given & when
    var student = studentRepository.findById(NOT_EXISTENT_ID);

    //then
    assertTrue(student.isEmpty());
  }

  private void assertUser(StudentEntity student) {
    assertEquals(ID_ONE, student.getStudentId());
    assertEquals("Jan", student.getStudentName());
    assertEquals("Kowalski", student.getStudentSurname());
    assertFalse(student.getWorkshopsForThisStudent().isEmpty());
  }

  private void assertOtherUser(StudentEntity student) {
    assertEquals(2, student.getStudentId());
    assertEquals("Anna", student.getStudentName());
    assertEquals("Kowalska", student.getStudentSurname());
    assertFalse(student.getWorkshopsForThisStudent().isEmpty());
  }
}
