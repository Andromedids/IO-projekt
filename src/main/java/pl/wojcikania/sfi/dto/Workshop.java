package pl.wojcikania.sfi.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode // Ta adnotacja zastępuje cały usunięty kod!
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Workshop {

  private long workshopId;
  private long presenterId;
  private String workshopTitle;
  private String workshopDescription;
  private String workshopDateTime;
  private List<Long> studentsAtThisWorkshop;

}