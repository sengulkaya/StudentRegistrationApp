package com.sengulkaya.application.service.rest.student.converter;

import com.sengulkaya.application.service.rest.student.data.entity.Course;
import com.sengulkaya.application.service.rest.student.data.entity.Student;
import com.sengulkaya.application.service.rest.student.dto.responseDTO.CourseResponseDTO;
import org.springframework.stereotype.Component;
import java.util.HashSet;
import java.util.Set;

@Component
public class CourseConverter {
    public CourseResponseDTO toCourseResponseDTO(Course course)
    {
        CourseResponseDTO courseResponseDTO = new CourseResponseDTO();
        courseResponseDTO.setId(course.id);
        courseResponseDTO.setCourseName(course.courseName);
        courseResponseDTO.setCapacity(course.capacity);

        Set<String> studentNames = new HashSet<>();
        Set<Student> students = course.students;

        if (students != null) {
            for (var student: students) {
                studentNames.add(student.studentName);
            }
        }
        courseResponseDTO.setStudentNames(studentNames);

        return courseResponseDTO;
    }
}
