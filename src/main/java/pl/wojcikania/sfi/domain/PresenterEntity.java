package pl.wojcikania.sfi.domain;

import jakarta.persistence.SequenceGenerator;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "presenter")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PresenterEntity {

  @Id
  @SequenceGenerator(name = "presenter_seq", sequenceName = "presenter_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "presenter_seq")
  @Column(name = "presenter_id")
  private Long presenterId;

  @Column(name = "presenter_name")
  private String presenterName;

  @Column(name = "presenter_surname")
  private String presenterSurname;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "presenter")
  private List<WorkshopEntity> workshopsRunByThisPresenter;
}
