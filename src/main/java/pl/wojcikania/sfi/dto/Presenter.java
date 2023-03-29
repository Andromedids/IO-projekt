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
public final class Presenter {
  private long presenterId;
  private String presenterName;
  private String presenterSurname;
  private List<Long> workshopsRunByThisPresenter;
}

