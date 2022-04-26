package com.sengulkaya.application.service.rest.student.controller;

import com.sengulkaya.application.service.rest.student.data.entity.Course;
import com.sengulkaya.application.service.rest.student.dto.requestDTO.CourseRequestDTO;
import com.sengulkaya.application.service.rest.student.dto.requestDTO.StudentRequestDTO;
import com.sengulkaya.application.service.rest.student.dto.responseDTO.CourseResponseDTO;
import com.sengulkaya.application.service.rest.student.dto.responseDTO.StudentResponseDTO;
import com.sengulkaya.application.service.rest.student.service.CourseService;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Scope("prototype")
@RequestMapping("api/course")
public class CourseController {
    private final CourseService m_courseService;

    public CourseController(CourseService courseService)
    {
        m_courseService = courseService;
    }

    @GetMapping("/all")
    public List<CourseResponseDTO> findAll()
    {
        return m_courseService.findAllCourses();
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<CourseResponseDTO> findCourseById(@PathVariable("id") Long courseId)
    {
        return new ResponseEntity<>(m_courseService.findCourseById(courseId), HttpStatus.OK);
    }

    @GetMapping("/find/byId")//(http://localhost:8082/api/course/find/byId?id=3)
    public List<CourseResponseDTO> findCoursesByStudentId(@RequestParam("id") Long studentId)
    {
        return m_courseService.findCoursesByStudentId(studentId);
    }


    @PostMapping("/save")//JdbcSQLIntegrityConstraintViolationException handle edilmeli!
    public CourseResponseDTO saveCourse(@RequestBody CourseRequestDTO courseRequestDTO)
    {
        return m_courseService.saveCourse(courseRequestDTO);
    }

    @PostMapping("/add/student/{studentId}/to/{courseId}")
    public CourseResponseDTO addStudent(@PathVariable("studentId") Long studentId, @PathVariable("courseId") Long courseId) throws Exception {
        return m_courseService.addStudentToCourse(studentId, courseId);
    }

    @PostMapping("/update/{id}")
    public CourseResponseDTO updateCourse(@PathVariable("id") Long id, @RequestBody CourseRequestDTO courseRequestDTO)
    {
        return m_courseService.updateCourse(id, courseRequestDTO);
    }

    @PostMapping("/dropStudent/{studentId}/from/{courseId}")
    public CourseResponseDTO removeStudent(@PathVariable Long studentId, @PathVariable Long courseId) {

        return m_courseService.deleteStudentFromCourse(studentId, courseId);
    }
}
