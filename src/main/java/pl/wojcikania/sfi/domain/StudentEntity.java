package pl.wojcikania.sfi.domain;

import jakarta.persistence.FetchType;
import jakarta.persistence.SequenceGenerator;
import java.io.Serializable;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "student")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class StudentEntity implements Serializable {

  private static long numberOfStudents = 0L;
  @Id
  @SequenceGenerator(name = "student_seq", sequenceName = "student_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_seq")
  @Column(name = "student_id")
  private long studentId;

  @Column(name = "student_name")
  private String studentName;

  @Column(name = "student_surname")
  private String studentSurname;

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinTable(
      name = "workshop_attendance",
      joinColumns = { @JoinColumn(name = "student_id")},
      inverseJoinColumns = { @JoinColumn(name = "workshop_id")}
  )
  private List<WorkshopEntity> workshopsForThisStudent;

}
