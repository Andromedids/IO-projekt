package pl.wojcikania.sfi.service;

import java.util.ArrayList;
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
@AllArgsConstructor
public class StudentService {
  @Autowired
  private StudentRepository studentRepository;

  public List<Student> getStudents() {
    List<Student> list = new ArrayList<>();
    List<StudentEntity> studentsFromDatabase = studentRepository.findAll();
    for (StudentEntity studentEntity : studentsFromDatabase) {
      Student student = getStudent(studentEntity);
      list.add(student);
    }
    return list;
  }

  private static Student getStudent(StudentEntity studentEntity) {
    List<Long> list = new ArrayList<>();
    List<WorkshopEntity> workshopsForThisStudent = studentEntity.getWorkshopsForThisStudent();
    for (WorkshopEntity workshopEntity : workshopsForThisStudent) {
      Long workshopId = workshopEntity.getWorkshopId();
      list.add(workshopId);
    }
    return Student
        .builder()
        .studentName(studentEntity.getStudentName())
        .studentSurname(studentEntity.getStudentSurname())
        .studentId(studentEntity.getStudentId())
        .workshopsForThisStudent(list)
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
