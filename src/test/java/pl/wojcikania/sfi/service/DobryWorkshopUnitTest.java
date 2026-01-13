package pl.wojcikania.sfi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static pl.wojcikania.sfi.repositories.StudentRepositoryTest.ID_ONE;
import static pl.wojcikania.sfi.repositories.StudentRepositoryTest.NOT_EXISTENT_ID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.wojcikania.sfi.domain.PresenterEntity;
import pl.wojcikania.sfi.domain.StudentEntity;
import pl.wojcikania.sfi.domain.WorkshopEntity;
import pl.wojcikania.sfi.dto.Student;
import pl.wojcikania.sfi.dto.Workshop;
import pl.wojcikania.sfi.repositories.StudentRepository;
import pl.wojcikania.sfi.repositories.WorkshopRepository;

import java.text.DateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DobryWorkshopUnitTest {

    @Mock
    private WorkshopService workshopService;
    @Mock
    private WorkshopRepository workshopRepository;
    @Mock
    private WorkshopEntity structuralGeology;
    @Mock
    private WorkshopEntity geologicalMapping;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        workshopService = new WorkshopService(workshopRepository);
    }

    @Test
    public void getWorkshops_shouldReturnAllWorkshops() {
        //given
        prepareWorkshopEntity(structuralGeology,31, Instant.from(LocalDateTime.of(2022, 1, 15, 12, 30,34)),"Najfajniejszy kurs!","Geologia Strukturalna");
        prepareWorkshopEntity(geologicalMapping,32, Instant.from(LocalDateTime.of(2022, 1, 12, 12, 30,34)),"Fajny teren!","Kartowanie geologiczne");
        List<WorkshopEntity> workshopEntities = List.of(structuralGeology);
        when(workshopRepository.findAll()).thenReturn(workshopEntities);
        //when
        List < Workshop> workshop = workshopService.getWorkshops();
        //then
        Assertions.assertEquals(2, workshop.size());
        assertWorkshops(workshop.get(0),31,Instant.from(LocalDateTime.of(2022, 1, 15, 12, 30,34)),"Najfajniejszy kurs!","Geologia Strukturalna");
        assertWorkshops(workshop.get(1),32,Instant.from(LocalDateTime.of(2022, 1, 12, 12, 30,34)),"Fajny teren!","Kartowanie geologiczne");
    }
    private void assertWorkshops(Workshop workshop, long workshopID, Instant data, String description,  String title) {
        //Assertions.assertEquals(presenterId, workshop.getPresenterId());
        Assertions.assertEquals(workshopID, workshop.getWorkshopId());
        Assertions.assertEquals(data, workshop.getWorkshopDateTime());
        Assertions.assertEquals(description, workshop.getWorkshopDescription());
        Assertions.assertEquals(title, workshop.getWorkshopTitle());
        Assertions.assertEquals(new ArrayList<>(), workshop.getStudentsAtThisWorkshop());
    }

    private void prepareWorkshopEntity(WorkshopEntity workshopEntity, long workshopId, Instant data, String description, String title) {
        //when(workshopEntity.getPresenter().getPresenterId()).thenReturn(presenterID);
        when(workshopEntity.getWorkshopId()).thenReturn(workshopId);
        when(workshopEntity.getWorkshopDateTime()).thenReturn(data);
        when(workshopEntity.getWorkshopDescription()).thenReturn(description);
        when(workshopEntity.getWorkshopTitle()).thenReturn(title);
        when(workshopEntity.getStudentsAtThisWorkshop()).thenReturn(new ArrayList<>());
    }
}
