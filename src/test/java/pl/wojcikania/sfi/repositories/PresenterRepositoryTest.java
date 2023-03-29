package pl.wojcikania.sfi.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static pl.wojcikania.sfi.repositories.StudentRepositoryTest.ID_ONE;
import static pl.wojcikania.sfi.repositories.StudentRepositoryTest.NOT_EXISTENT_ID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.wojcikania.sfi.domain.PresenterEntity;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class PresenterRepositoryTest {
  @Autowired
  private PresenterRepository presenterRepository;

  @Test
//  @Transactional
  public void shouldGetAllPresenters() {
    //given & when
    var presenters = presenterRepository.findAll();

    //then
    assertEquals(2, presenters.size());
    assertPresenterAnna(presenters.get(0));
    assertPresenterJan(presenters.get(1));
  }

  @Test
//  @Transactional
  public void shouldGetOnePresenter() {
    //given & when
    var presenter = presenterRepository.findById(ID_ONE);

    //then
    assertNotNull(presenter.get());
    assertPresenterAnna(presenter.get());
  }

  @Test
  public void shouldReturnNullWhenNoPresenter() {
    //given & when
    var presenter = presenterRepository.findById(NOT_EXISTENT_ID);

    //then
    assertTrue(presenter.isEmpty());
  }

  private void assertPresenterAnna(PresenterEntity presenter) {
    assertEquals(1, presenter.getPresenterId());
    assertEquals("Anna", presenter.getPresenterName());
    assertEquals("Wojcik", presenter.getPresenterSurname());
    assertFalse(presenter.getWorkshopsRunByThisPresenter().isEmpty());
  }

  private void assertPresenterJan(PresenterEntity presenter) {
    assertEquals(2, presenter.getPresenterId());
    assertEquals("Pawel", presenter.getPresenterName());
    assertEquals("Lipski", presenter.getPresenterSurname());
        assertFalse(presenter.getWorkshopsRunByThisPresenter().isEmpty());
  }
}
