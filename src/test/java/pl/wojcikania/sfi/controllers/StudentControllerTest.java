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
class StudentControllerTest {

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
  void shouldGetAllStudents() throws Exception {
    //given when then
    areStudentsReturned(mockMvc.perform(get("/students")));
  }

  @Test
  void shouldGetOneStudent() throws Exception {
    //given when then
    isOneStudentReturned(mockMvc.perform(get("/students/{studentId}", ID_ONE)));
  }

  @Test
  void shouldReturnNotFound() throws Exception {
    //given when then
    mockMvc.perform(get("/students/" + NOT_EXISTENT_ID))
        .andExpect(status().isNotFound());
  }

  private static void areStudentsReturned(ResultActions perform) throws Exception {
    perform.andExpect(status().isOk())
        .andExpect(jsonPath("$[0].studentId").value("1"))
        .andExpect(jsonPath("$[0].studentName").value("Jan"))
        .andExpect(jsonPath("$[0].studentSurname").value("Kowalski"))
        .andExpect(jsonPath("$[0].workshopsForThisStudent").isNotEmpty())

        .andExpect(jsonPath("$[1].studentId").value("2"))
        .andExpect(jsonPath("$[1].studentName").value("Anna"))
        .andExpect(jsonPath("$[1].studentSurname").value("Kowalska"))
        .andExpect(jsonPath("$[1].workshopsForThisStudent").isNotEmpty());
  }

  private static void isOneStudentReturned(ResultActions perform) throws Exception {
    perform.andExpect(status().isOk())
        .andExpect(jsonPath("$.studentId").value("1"))
        .andExpect(jsonPath("$.studentName").value("Jan"))
        .andExpect(jsonPath("$.studentSurname").value("Kowalski"))
        .andExpect(jsonPath("$.workshopsForThisStudent").isNotEmpty());
  }
}