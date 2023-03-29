package pl.wojcikania.sfi.service;

import static org.junit.jupiter.api.Assertions.*;
import static pl.wojcikania.sfi.repositories.StudentRepositoryTest.ID_ONE;
import static pl.wojcikania.sfi.repositories.StudentRepositoryTest.NOT_EXISTENT_ID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
class PresenterServiceTest {
  @Autowired
  private PresenterService presenterService;

  @Test
  public void shouldReturnPresenters() {
    //given & when
    var contacts = presenterService.getPresenters();

    //then
    assertEquals(2, contacts.size());
  }

  @Test
  public void shouldReturnPresenter() {
    //given & when
    var presenter = presenterService.getPresenter(ID_ONE);

    //then
    assertNotNull(presenter);
  }

  @Test
  public void shouldThrowExceptionWhenNoPresenterFound() {
    //given & when
    assertThrows(IllegalArgumentException.class, () ->
        presenterService.getPresenter(NOT_EXISTENT_ID), "no presenter with id " + NOT_EXISTENT_ID);
  }

}