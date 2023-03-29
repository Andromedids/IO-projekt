package pl.wojcikania.sfi.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public final class Student {

  private long studentId;
  private String studentName;
  private String studentSurname;
  private List<Long> workshopsForThisStudent;

}

