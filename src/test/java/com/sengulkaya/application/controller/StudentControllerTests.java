package com.sengulkaya.application.controller;


import com.sengulkaya.application.service.rest.student.dto.responseDTO.StudentResponseDTO;
import com.sengulkaya.application.service.rest.student.service.StudentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(classes={com.sengulkaya.application.service.rest.student.App.class})
public class StudentControllerTests {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @MockBean
    private StudentService studentService;


    @Test
    public void findStudentByIdTest() throws Exception
    {
        StudentResponseDTO studentResponseDTO = new StudentResponseDTO();
        studentResponseDTO.setStudentName("Eda Erdem");

        Mockito.when(studentService.findStudentById(Mockito.anyLong())).thenReturn(studentResponseDTO);


        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8082/api/student/find/1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.studentName").value("Eda Erdem"))
                        .andExpect(status().isOk());

    }

}
