package pl.wojcikania.sfi.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.wojcikania.sfi.domain.PresenterEntity;
import pl.wojcikania.sfi.dto.Presenter;
import pl.wojcikania.sfi.repositories.PresenterRepository;

class PresenterServiceUnitTest {

  @Mock
  private PresenterRepository presenterRepository;
  @Mock
  private PresenterEntity presenterEntity1;
  @Mock
  private PresenterEntity presenterEntity2;

  private PresenterService presenterService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    presenterService = new PresenterService(presenterRepository);
  }

  @Test
  public void shouldGetPresenters_when2Presenters() {
    // given
    List<PresenterEntity> presentersEntities = new ArrayList<>();
    preparePresenterEntity(presenterEntity1, 1L, "Test", "Testowy");
    preparePresenterEntity(presenterEntity2, 2L, "Test2", "Testowy2");

    presentersEntities.add(presenterEntity1);
    presentersEntities.add(presenterEntity2);
    when(presenterRepository.findAll()).thenReturn(presentersEntities);

    // when
    List<Presenter> actualPresenters = presenterService.getPresenters();

    // then
    Assertions.assertEquals(2, actualPresenters.size());
    assertPresenter(actualPresenters.get(0), 1L, "Test", "Testowy");
    assertPresenter(actualPresenters.get(1), 2L, "Test2", "Testowy2");
  }

  private static void assertPresenter(Presenter actualPresenter, long expectedId, String expectedName, String expectedSurname) {
    Assertions.assertEquals(expectedId, actualPresenter.getPresenterId());
    Assertions.assertEquals(expectedName, actualPresenter.getPresenterName());
    Assertions.assertEquals(expectedSurname, actualPresenter.getPresenterSurname());
    Assertions.assertEquals(new ArrayList<>(), actualPresenter.getWorkshopsRunByThisPresenter());
  }

  private void preparePresenterEntity(PresenterEntity presenterEntity, long presenterId, String name, String surname) {
    when(presenterEntity.getPresenterId()).thenReturn(presenterId);
    when(presenterEntity.getPresenterName()).thenReturn(name);
    when(presenterEntity.getPresenterSurname()).thenReturn(surname);
    when(presenterEntity.getWorkshopsRunByThisPresenter()).thenReturn(new ArrayList<>());
  }
}