package com.sengulkaya.application.controller;

import com.sengulkaya.application.service.rest.student.controller.CourseController;
import com.sengulkaya.application.service.rest.student.controller.StudentController;
import com.sengulkaya.application.service.rest.student.dto.responseDTO.CourseResponseDTO;
import com.sengulkaya.application.service.rest.student.dto.responseDTO.StudentResponseDTO;
import com.sengulkaya.application.service.rest.student.service.CourseService;
import com.sengulkaya.application.service.rest.student.service.StudentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
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
public class CourseControllerTests {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @MockBean
    private CourseService courseService;


    @Test
    public void findCourseByIdTest() throws Exception
    {
        CourseResponseDTO courseResponseDTO = new CourseResponseDTO();
        courseResponseDTO.setCourseName("Math");
        courseResponseDTO.setCapacity(1);

        Mockito.when(courseService.findCourseById(Mockito.anyLong())).thenReturn(courseResponseDTO);


        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8082/api/course/find/1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.courseName").value("Math"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.capacity").value(1))
                .andExpect(status().isOk());

    }
}
