package com.sengulkaya.application.service.rest.student.data.repository;

import com.sengulkaya.application.service.rest.student.data.entity.Course;
import com.sengulkaya.application.service.rest.student.data.entity.Student;
import org.junit.validator.ValidateWith;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;


public interface ICourseRepository extends CrudRepository<Course, Long> {
    Optional<Course> findById(Long courseId);
    Optional<Course> findByCourseName(String courseName);
    void deleteById(Long id);
}
