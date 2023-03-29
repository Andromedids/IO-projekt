package pl.wojcikania.sfi.service;

import static org.junit.jupiter.api.Assertions.*;
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
class WorkshopServiceTest {
  public static final long PRESENTER_ID = 1L;
  @Autowired
  private WorkshopService workshopService;

  @Test
  public void shouldReturnWorkshops() {
    //given & when
    var contacts = workshopService.getWorkshops();

    //then
    assertEquals(2, contacts.size());
  }

  @Test
  public void shouldReturnWorkshop() {
    //given & when
    var workshop = workshopService.getWorkshop(PRESENTER_ID);

    //then
    assertNotNull(workshop);
  }

  @Test
  public void shouldThrowExceptionWhenNoWorkshopFound() {
    //given & when
    assertThrows(IllegalArgumentException.class, () ->
        workshopService.getWorkshop(NOT_EXISTENT_ID), "no workshop with id " + NOT_EXISTENT_ID);
  }

}