package pl.wojcikania.sfi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wojcikania.sfi.domain.PresenterEntity;
import pl.wojcikania.sfi.domain.WorkshopEntity;
import pl.wojcikania.sfi.dto.Presenter;
import pl.wojcikania.sfi.repositories.PresenterRepository;

@Service
public class PresenterService {
  @Autowired
  private PresenterRepository presenterRepository;


  public PresenterService(PresenterRepository presenterRepository) {
    this.presenterRepository = presenterRepository;
  }

  public List<Presenter> getPresenters() {
    List<PresenterEntity> presentersEntities = presenterRepository.findAll();

    List<Presenter> list = new ArrayList<>();
    for (int i = 0; i < presentersEntities.size(); i++) {
      PresenterEntity presentersEntity = presentersEntities.get(i);
      Presenter presenter = getPresenter(presentersEntity);
      list.add(presenter);
    }

    return list;
  }

  public long savePresenter(Presenter presenter) {
    return presenterRepository.save(PresenterEntity.builder()
            .presenterSurname(presenter.getPresenterSurname())
            .presenterName(presenter.getPresenterName())
            .build())
        .getPresenterId();
  }

  private static Presenter getPresenter(PresenterEntity presenterEntity) {
    return Presenter
        .builder()
        .presenterId(presenterEntity.getPresenterId())
        .presenterName(presenterEntity.getPresenterName())
        .presenterSurname(presenterEntity.getPresenterSurname())
        .workshopsRunByThisPresenter(presenterEntity
            .getWorkshopsRunByThisPresenter()
            .stream()
            .map(WorkshopEntity::getWorkshopId)
            .toList())
        .build();
  }

  public Presenter getPresenter(Long presenterId) {
    Optional<PresenterEntity> contact = presenterRepository.findById(presenterId);
    if (contact.isEmpty()){
      throw new IllegalArgumentException("no presenter with id " + presenterId);
    }
    return getPresenter(contact.get());
  }
}
