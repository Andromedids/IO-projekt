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
import pl.wojcikania.sfi.dto.Workshop;
import pl.wojcikania.sfi.repositories.WorkshopRepository;

@Service
@AllArgsConstructor
public class WorkshopService {

  @Autowired
  private WorkshopRepository workshopRepository;

  public List<Workshop> getWorkshops() {
    List<Workshop> list = new ArrayList<>();
    List<WorkshopEntity> workshopsFromDatabase = workshopRepository.findAll();
    for (WorkshopEntity workshopEntity : workshopsFromDatabase) {
      Workshop workshop = getWorkshop(workshopEntity);
      list.add(workshop);
    }
    return list;
  }

  private static Workshop getWorkshop(WorkshopEntity workshopEntity) {
    List<Long> list = new ArrayList<>();
    List<StudentEntity> workshopsForThisStudent = workshopEntity.getStudentsAtThisWorkshop();
    for (StudentEntity studentEntity : workshopsForThisStudent) {
      Long studentId = studentEntity.getStudentId();
      list.add(studentId);
    }
    return Workshop
        .builder()
        .presenterId(workshopEntity.getPresenter().getPresenterId())
        .workshopId(workshopEntity.getWorkshopId())
        .workshopDateTime(workshopEntity.getWorkshopDateTime())
        .workshopDescription(workshopEntity.getWorkshopDescription())
        .workshopTitle(workshopEntity.getWorkshopTitle())
        .studentsAtThisWorkshop(list)
        .build();
  }

  public Workshop getWorkshop(Long presenterId) {
    Optional<WorkshopEntity> contact = workshopRepository.findById(presenterId);
    if (contact.isEmpty()){
      throw new IllegalArgumentException("no workshop with id " + presenterId);
    }
    return getWorkshop(contact.get());
  }

}
