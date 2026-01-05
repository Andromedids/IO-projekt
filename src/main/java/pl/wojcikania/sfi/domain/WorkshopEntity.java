package pl.wojcikania.sfi.domain;

import jakarta.persistence.FetchType;
import jakarta.persistence.SequenceGenerator;
import java.time.Instant;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "workshop")
public class WorkshopEntity {

  @Id
  @SequenceGenerator(name = "workshop_seq", sequenceName = "workshop_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "workshop_seq")
  @Column(name = "workshop_id")
  private long workshopId;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "presenter_id", referencedColumnName = "presenter_id")
  private PresenterEntity presenter;

  @Column(name = "workshop_title")
  private String workshopTitle;

  @Column(name = "workshop_description")
  private String workshopDescription;

  @Column(name = "workshop_date")
  private String workshopDateTime;

  @ManyToMany(mappedBy = "workshopsForThisStudent", fetch = FetchType.EAGER)
  private List<StudentEntity> studentsAtThisWorkshop;

}
