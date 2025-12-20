package pl.wojcikania.sfi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static pl.wojcikania.sfi.repositories.StudentRepositoryTest.ID_ONE;
import static pl.wojcikania.sfi.repositories.StudentRepositoryTest.NOT_EXISTENT_ID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.wojcikania.sfi.domain.StudentEntity;
import pl.wojcikania.sfi.domain.WorkshopEntity;
import pl.wojcikania.sfi.repositories.StudentRepository;

public class DobryStudentServiceUnitTest {

    @Mock
    private StudentService studentService;
    @Mock
    StudentRepository studentRepository;
    @Mock
    private StudentEntity meStudent;
    @Mock
    private StudentEntity heStudent;
    @Mock
    private WorkshopEntity structuralGeology;
    @Mock
    private WorkshopEntity geologicalMapping;

    @Test
    public void LetItGiveBackStudentsList() {
        //given

        //when

        //then
    }


}