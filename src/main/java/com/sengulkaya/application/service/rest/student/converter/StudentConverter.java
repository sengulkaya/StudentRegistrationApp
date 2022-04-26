package com.sengulkaya.application.service.rest.student.converter;

import com.sengulkaya.application.service.rest.student.data.entity.Course;
import com.sengulkaya.application.service.rest.student.data.entity.Student;
import com.sengulkaya.application.service.rest.student.dto.responseDTO.StudentResponseDTO;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class StudentConverter {
    public StudentResponseDTO toStudentResponseDTO(Student student)
    {
        StudentResponseDTO studentResponseDTO = new StudentResponseDTO();

        studentResponseDTO.setId(student.id);
        studentResponseDTO.setStudentName(student.studentName);

        Set<String> courseNames = new HashSet<>();
        Set<Course> courses = student.courses;

        if (courses != null) {
            for (var course: courses) {
                courseNames.add(course.courseName);
            }
        }

        studentResponseDTO.setCourseNames(courseNames);

        return studentResponseDTO;
    }
}
