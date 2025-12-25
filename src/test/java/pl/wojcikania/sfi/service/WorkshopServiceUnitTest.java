package pl.wojcikania.sfi.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.wojcikania.sfi.domain.PresenterEntity;
import pl.wojcikania.sfi.domain.StudentEntity;
import pl.wojcikania.sfi.domain.WorkshopEntity;
import pl.wojcikania.sfi.dto.Workshop;
import pl.wojcikania.sfi.repositories.WorkshopRepository;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class WorkshopServiceUnitTest {

    @Mock private WorkshopRepository workshopRepository;
    @Mock private WorkshopEntity workshop1;
    @Mock private WorkshopEntity workshop2;
    @Mock private StudentEntity student1;
    @Mock private StudentEntity student2;
    @Mock private StudentEntity student3;
    @Mock private StudentEntity student4;
    @Mock private PresenterEntity presenter1;
    @Mock private PresenterEntity presenter2;

    private WorkshopService workshopService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        workshopService = new WorkshopService(workshopRepository);
    }

    @Test
    void shouldReturnAllWorkshops() {

        // given
        when(workshopRepository.findAll()).thenReturn(List.of(workshop1, workshop2));

        Instant date1 = Instant.parse("2024-01-01T10:00:00Z");
        Instant date2 = Instant.parse("2024-01-01T11:00:00Z");

        prepareWorkshop(workshop1, presenter1, 1L, date1,
                student1, 1L,
                student2, 2L);

        prepareWorkshop(workshop2, presenter2, 2L, date2,
                student3, 3L,
                student4, 4L);

        // when
        var result = workshopService.getWorkshops();

        // then
        List<Workshop> expected = List.of(
                expectedWorkshop(1L, date1, List.of(1L, 2L)),
                expectedWorkshop(2L, date2, List.of(3L, 4L))
        );

        assertEquals(expected, result);
    }

    private void prepareWorkshop(
            WorkshopEntity workshop,
            PresenterEntity presenter,
            Long id,
            Instant date,
            StudentEntity s1, Long sid1,
            StudentEntity s2, Long sid2
    ) {
        when(workshop.getPresenter()).thenReturn(presenter);
        when(presenter.getPresenterId()).thenReturn(id);

        when(workshop.getWorkshopId()).thenReturn(id);
        when(workshop.getWorkshopDateTime()).thenReturn(date);
        when(workshop.getWorkshopTitle()).thenReturn("Tytulik" + id);
        when(workshop.getWorkshopDescription()).thenReturn("Opisik" + id);

        when(workshop.getStudentsAtThisWorkshop()).thenReturn(List.of(s1, s2));

        when(s1.getStudentId()).thenReturn(sid1);
        when(s2.getStudentId()).thenReturn(sid2);
    }

    private Workshop expectedWorkshop(Long id, Instant date, List<Long> students) {
        return Workshop.builder()
                .workshopId(id)
                .workshopTitle("Tytulik" + id)
                .workshopDateTime(date)
                .workshopDescription("Opisik" + id)
                .presenterId(id)
                .studentsAtThisWorkshop(students)
                .build();
    }
}
