package pl.wojcikania.sfi.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static pl.wojcikania.sfi.repositories.StudentRepositoryTest.ID_ONE;
import static pl.wojcikania.sfi.repositories.StudentRepositoryTest.NOT_EXISTENT_ID;

import java.time.Instant;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import pl.wojcikania.sfi.domain.WorkshopEntity;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class WorkshopRepositoryTest {
  @Autowired
  private WorkshopRepository workshopRepository;

  @Test
    @Transactional
  public void shouldGetAllWorkshops() {
    //given & when
    var workshops = workshopRepository.findAll();

    //then
    assertEquals(2, workshops.size());
    assertFirstWorkshop(workshops.get(0));
    assertSecondWorkshop(workshops.get(1));
  }

  @Test
    @Transactional
  public void shouldGetOneWorkshop() {
    //given & when
    var student = workshopRepository.findById(ID_ONE);

    //then
    assertNotNull(student.get());
    assertFirstWorkshop(student.get());
  }

  @Test
  public void shouldReturnNullWhenNoWorkshop() {
    //given & when
    var workshop = workshopRepository.findById(NOT_EXISTENT_ID);

    //then
    assertTrue(workshop.isEmpty());
  }

  private void assertFirstWorkshop(WorkshopEntity workshopEntity) {
    assertEquals(1, workshopEntity.getWorkshopId());
    assertEquals("CRUDowa aplikacja w 45minut", workshopEntity.getWorkshopTitle());
    assertEquals("postawimy szybko apke javova", workshopEntity.getWorkshopDescription());
    assertEquals(Instant.parse("2023-03-30T09:10:25.00Z"), workshopEntity.getWorkshopDateTime());
    assertFalse(workshopEntity.getStudentsAtThisWorkshop().isEmpty());
    assertNotNull(workshopEntity.getPresenter());
  }

  private void assertSecondWorkshop(WorkshopEntity workshopEntity) {
    assertEquals(2, workshopEntity.getWorkshopId());
    assertEquals("Git Machete", workshopEntity.getWorkshopTitle());
    assertEquals("Organizator repozytoriow i narzedzie do automatyzacji rebase i merge", workshopEntity.getWorkshopDescription());
    assertEquals(Instant.parse("2023-03-30T13:30:00.00Z"), workshopEntity.getWorkshopDateTime());
    assertFalse(workshopEntity.getStudentsAtThisWorkshop().isEmpty());
    assertNotNull(workshopEntity.getPresenter());
  }
}
