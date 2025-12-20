package pl.wojcikania.sfi.controllers;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.wojcikania.sfi.dto.Student;
import pl.wojcikania.sfi.service.StudentService;

@RestController
@AllArgsConstructor
@Slf4j
public class StudentController {

  @Autowired
  private StudentService studentService;

  @GetMapping("/students")
  public List<Student> getStudents() {
    log.info("fetching all students");
    return studentService.getStudents();
  }

  @GetMapping("/students/{studentId}")
  public Student getStudentBy(@PathVariable String studentId) {
    log.info("fetching student with id " + studentId);
    return studentService.getStudent(Long.valueOf(studentId));
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<String> handleBadIdInRequest(
      IllegalArgumentException exception,
      HttpServletRequest httpServletRequest
  ) {
    log.warn("bad. student id was provided");
    return new ResponseEntity<>("Provided id is not valid", NOT_FOUND);
  }
}
