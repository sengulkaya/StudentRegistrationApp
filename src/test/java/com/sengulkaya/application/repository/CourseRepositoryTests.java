package com.sengulkaya.application.repository;

import com.sengulkaya.application.service.rest.student.data.entity.Course;
import com.sengulkaya.application.service.rest.student.data.repository.ICourseRepository;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import static org.assertj.core.api.Assertions.assertThat;



@RunWith(SpringRunner.class)
@SpringBootTest(classes={com.sengulkaya.application.service.rest.student.App.class})
public class CourseRepositoryTests {
    @Autowired
    private ICourseRepository courseRepository;


    @Test
    @Order(1)
    public void saveCourseTest()
    {
        Course course = new Course();
        course.courseName = "Math";
        course.capacity = 1;

        courseRepository.save(course);
        assertThat(course.id).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void findCourseByIdTest()
    {
        Course course = courseRepository.findById(1L).get();

        assertThat(course.courseName).isEqualTo("Math");
        assertThat(course.capacity).isEqualTo(1);
    }

    @Test
    @Order(3)
    public void findAllCoursesTest()//not working
    {
        List<Course> list = StreamSupport.stream(courseRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());


        assertThat(list.size()).isEqualTo(1);
        assertThat(list.get(0).courseName).isEqualTo("Math");
        assertThat(list.get(0).capacity).isEqualTo(1);
    }
}
