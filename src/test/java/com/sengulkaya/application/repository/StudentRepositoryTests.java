package com.sengulkaya.application.repository;

import com.sengulkaya.application.service.rest.student.data.entity.Student;
import com.sengulkaya.application.service.rest.student.data.repository.IStudentRepository;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@RunWith(SpringRunner.class)
@SpringBootTest(classes={com.sengulkaya.application.service.rest.student.App.class})
public class StudentRepositoryTests {
    @Autowired
    private IStudentRepository studentRepository;



    @Test
    @Order(1)
    public void saveStudentTest()
    {
        Student student = new Student();
                student.studentName = "Eda Erdem";

        studentRepository.save(student);
        assertThat(student.id).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void findStudentByIdTest()
    {
        Student student = studentRepository.findById(1L).get();

        assertThat(student.studentName).isEqualTo("Eda Erdem");
    }

    @Test
    @Order(3)
    public void findAllStudentsTest()
    {
        List<Student> list = StreamSupport.stream(studentRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        assertThat(list.size()).isGreaterThan(0);
        assertThat(list.get(0).studentName).isEqualTo("Eda Erdem");

    }
}
