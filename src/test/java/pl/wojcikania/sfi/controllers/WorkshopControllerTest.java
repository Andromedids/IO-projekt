package pl.wojcikania.sfi.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.wojcikania.sfi.repositories.StudentRepositoryTest.ID_ONE;
import static pl.wojcikania.sfi.repositories.StudentRepositoryTest.NOT_EXISTENT_ID;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class WorkshopControllerTest {

  @Autowired
  private WebApplicationContext context;

  private MockMvc mockMvc;

  @BeforeAll
  public void saveDataSource() {
    mockMvc = MockMvcBuilders
        .webAppContextSetup(context)
        .build();
  }

  @Test
  @Transactional
  void shouldGetAllWorkshops() throws Exception {
    //given when then
    areWorkshopsReturned(mockMvc.perform(get("/workshops")));
  }

  @Test
  @Transactional
  void shouldGetOneWorkshop() throws Exception {
    //given when then
    isOnePresenterReturned(mockMvc.perform(get("/workshops/{workshopId}", ID_ONE)));
  }

  @Test
  void shouldReturnNotFound() throws Exception {
    //given when then
    mockMvc.perform(get("/workshops/" + NOT_EXISTENT_ID))
        .andExpect(status().isNotFound());
  }

  private static void areWorkshopsReturned(ResultActions perform) throws Exception {
    perform.andExpect(status().isOk())
        .andExpect(jsonPath("$[0].workshopId").value("1"))
        .andExpect(jsonPath("$[0].workshopTitle").value("CRUDowa aplikacja w 45minut"))
        .andExpect(jsonPath("$[0].workshopDescription").value("postawimy szybko apke javova"))
        .andExpect(jsonPath("$[0].workshopDateTime").value("2023-03-30 11:10:25"))

        .andExpect(jsonPath("$[1].workshopId").value("2"))
        .andExpect(jsonPath("$[1].workshopTitle").value("Git Machete"))
        .andExpect(jsonPath("$[1].workshopDescription").value("Organizator repozytoriow i narzedzie do automatyzacji rebase i merge"))
        .andExpect(jsonPath("$[1].workshopDateTime").value("2023-03-30 15:30:00"));
  }

  private static void isOnePresenterReturned(ResultActions perform) throws Exception {
    perform.andExpect(status().isOk())
        .andExpect(jsonPath("$.workshopId").value("1"))
        .andExpect(jsonPath("$.workshopTitle").value("CRUDowa aplikacja w 45minut"))
        .andExpect(jsonPath("$.workshopDescription").value("postawimy szybko apke javova"))
        .andExpect(jsonPath("$.workshopDateTime").value("2023-03-30 11:10:25"));
  }
}