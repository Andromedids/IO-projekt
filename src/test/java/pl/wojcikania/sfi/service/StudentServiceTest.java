package pl.wojcikania.sfi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static pl.wojcikania.sfi.repositories.StudentRepositoryTest.ID_ONE;
import static pl.wojcikania.sfi.repositories.StudentRepositoryTest.NOT_EXISTENT_ID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class StudentServiceTest {
  @Autowired
  private StudentService studentService;

  @Test
  public void shouldReturnStudents() {
    //given & when
    var students = studentService.getStudents();

    //then
    assertEquals(2, students.size());
  }

  @Test
  public void shouldReturnStudent() {
    //given & when
    var student = studentService.getStudent(ID_ONE);

    //then
    assertNotNull(student);
  }

  @Test
  public void shouldThrowExceptionWhenNoStudentFound() {
    //given & when
    assertThrows(IllegalArgumentException.class, () ->
        studentService.getStudent(NOT_EXISTENT_ID), "no student with id " + NOT_EXISTENT_ID);
  }
}


