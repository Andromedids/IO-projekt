package pl.wojcikania.sfi.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.wojcikania.sfi.repositories.StudentRepositoryTest.ID_ONE;
import static pl.wojcikania.sfi.repositories.StudentRepositoryTest.NOT_EXISTENT_ID;

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
class PresenterControllerTest {

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
  void shouldGetAllPresenters() throws Exception {
    //given when then
    arePresentersReturned(mockMvc.perform(get("/presenters")));
  }

  @Test
  void shouldGetOnePresenter() throws Exception {
    //given when then
    isOnePresenterReturned(mockMvc.perform(get("/presenters/{presenterId}", ID_ONE)));
  }

  @Test
  void shouldReturnNotFound() throws Exception {
    //given when then
    mockMvc.perform(get("/presenters/" + NOT_EXISTENT_ID))
        .andExpect(status().isNotFound());
  }

  private static void arePresentersReturned(ResultActions perform) throws Exception {
    perform.andExpect(status().isOk())
        .andExpect(jsonPath("$[0].presenterId").value("1"))
        .andExpect(jsonPath("$[0].presenterName").value("Anna"))
        .andExpect(jsonPath("$[0].presenterSurname").value("Wojcik"))

        .andExpect(jsonPath("$[1].presenterId").value("2"))
        .andExpect(jsonPath("$[1].presenterName").value("Pawel"))
        .andExpect(jsonPath("$[1].presenterSurname").value("Lipski"));
  }

  private static void isOnePresenterReturned(ResultActions perform) throws Exception {
    perform.andExpect(status().isOk())
        .andExpect(jsonPath("$.presenterId").value("1"))
        .andExpect(jsonPath("$.presenterName").value("Anna"))
        .andExpect(jsonPath("$.presenterSurname").value("Wojcik"));
  }
}