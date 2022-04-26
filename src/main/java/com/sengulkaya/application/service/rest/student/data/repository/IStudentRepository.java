package com.sengulkaya.application.service.rest.student.data.repository;

import com.sengulkaya.application.service.rest.student.data.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

public interface IStudentRepository extends CrudRepository<Student, Long> {
    Optional<Student> findById(Long id);
    Optional<Student> findByStudentName(String studentName);
    void deleteById(Long id);
}
