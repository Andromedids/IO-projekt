package pl.wojcikania.sfi.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wojcikania.sfi.domain.StudentEntity;
import pl.wojcikania.sfi.domain.WorkshopEntity;
import pl.wojcikania.sfi.dto.Student;
import pl.wojcikania.sfi.repositories.StudentRepository;

@Service
public class StudentService {
  @Autowired
  private StudentRepository studentRepository;

  public StudentService(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  public List<Student> getStudents() {

    return studentRepository.findAll().stream()
        .map(StudentService::getStudent)
        .toList();
  }

  private static Student getStudent(StudentEntity studentEntity) {
    return Student
        .builder()
        .studentName(studentEntity.getStudentName())
        .studentSurname(studentEntity.getStudentSurname())
        .studentId(studentEntity.getStudentId())
        .workshopsForThisStudent(studentEntity
            .getWorkshopsForThisStudent()
            .stream()
            .map(WorkshopEntity::getWorkshopId)
            .collect(Collectors.toList()))
        .build();
  }

  public Student getStudent(Long studentId) {
    Optional<StudentEntity> student = studentRepository.findById(studentId);
    if (student.isEmpty()){
      throw new IllegalArgumentException("no student with id " + studentId);
    }
    return getStudent(student.get());
  }
}
