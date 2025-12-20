package pl.wojcikania.sfi.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.wojcikania.sfi.domain.WorkshopEntity;
import pl.wojcikania.sfi.dto.Workshop;
import pl.wojcikania.sfi.repositories.WorkshopRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class WorkshopServiceUnitTest {
    private WorkshopService workshopService;

    @Mock
    private WorkshopRepository workshopRepository;
    @Mock
    private WorkshopEntity programming;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        workshopService = new WorkshopService(workshopRepository);
    }

    @Test
    public void shouldReturnAllWorkshops (){
        //given
        List<WorkshopEntity> workshopsFromDatabase = new ArrayList<>();
        workshopsFromDatabase.add(programming);

        //when
        List<Workshop> workshops = workshopService.getWorkshops();

        //then


    }

}