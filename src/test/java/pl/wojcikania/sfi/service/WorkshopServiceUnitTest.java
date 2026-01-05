package pl.wojcikania.sfi.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.wojcikania.sfi.domain.PresenterEntity;
import pl.wojcikania.sfi.domain.StudentEntity;
import pl.wojcikania.sfi.domain.WorkshopEntity;
import pl.wojcikania.sfi.dto.Workshop;
import pl.wojcikania.sfi.repositories.WorkshopRepository;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WorkshopServiceUnitTest {

    @Mock
    private WorkshopRepository workshopRepository;

    @InjectMocks
    private WorkshopService workshopService;

    @Mock
    private WorkshopEntity javaEntity;
    @Mock
    private WorkshopEntity pythonEntity;
    @Mock
    private StudentEntity mateuszJ;
    @Mock
    private StudentEntity krystianB;
    @Mock
    private PresenterEntity presenterMateusz;
    @Mock
    private PresenterEntity presenterKrystian;

    @Test
    public void shouldReturnALLWorkshops() {
        //given
        List<WorkshopEntity> workshopsFromDatabase = List.of(javaEntity, pythonEntity);
        List<StudentEntity> studentEntities = List.of(mateuszJ, krystianB);

        when(workshopRepository.findAll()).thenReturn(workshopsFromDatabase);

        when(mateuszJ.getStudentId()).thenReturn(1L);
        when(krystianB.getStudentId()).thenReturn(2L);
        when(javaEntity.getWorkshopId()).thenReturn(1L);
        when(javaEntity.getWorkshopTitle()).thenReturn("Workshop java");
        when(javaEntity.getWorkshopDescription()).thenReturn("java");
        when(javaEntity.getWorkshopDateTime()).thenReturn("10:00");
        when(javaEntity.getStudentsAtThisWorkshop()).thenReturn(studentEntities);
        when(javaEntity.getPresenter()).thenReturn(presenterMateusz);
        when(presenterMateusz.getPresenterId()).thenReturn(1L);

        when(pythonEntity.getWorkshopId()).thenReturn(2L);
        when(pythonEntity.getWorkshopTitle()).thenReturn("Workshop python");
        when(pythonEntity.getWorkshopDescription()).thenReturn("python");
        when(pythonEntity.getWorkshopDateTime()).thenReturn("11:00");
        when(pythonEntity.getStudentsAtThisWorkshop()).thenReturn(studentEntities);
        when(pythonEntity.getPresenter()).thenReturn(presenterKrystian);
        when(presenterKrystian.getPresenterId()).thenReturn(2L);

        //when
        List<Workshop> workshops = workshopService.getWorkshops();

        //then
        Assertions.assertEquals(2, workshops.size());

        List<Workshop> expected = List.of(
                Workshop.builder()
                        .presenterId(1L)
                        .workshopId(1L)
                        .workshopDateTime("10:00")
                        .workshopDescription("java")
                        .workshopTitle("Workshop java")
                        .studentsAtThisWorkshop(List.of(1L, 2L))
                        .build(),
                Workshop.builder()
                        .presenterId(2L)
                        .workshopId(2L)
                        .workshopDateTime("11:00")
                        .workshopDescription("python")
                        .workshopTitle("Workshop python")
                        .studentsAtThisWorkshop(List.of(1L, 2L))
                        .build()
        );
        Assertions.assertEquals(expected, workshops);
    }
}