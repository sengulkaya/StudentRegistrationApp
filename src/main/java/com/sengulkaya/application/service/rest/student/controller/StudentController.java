package com.sengulkaya.application.service.rest.student.controller;

import com.sengulkaya.application.service.rest.student.dto.requestDTO.StudentRequestDTO;
import com.sengulkaya.application.service.rest.student.dto.responseDTO.StudentResponseDTO;
import com.sengulkaya.application.service.rest.student.service.StudentService;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Scope("prototype")
@RequestMapping("api/student")
public class StudentController {
    private final StudentService m_studentService;

    public StudentController(StudentService studentService)
    {
        m_studentService = studentService;
    }

    @GetMapping("/all")
    public List<StudentResponseDTO> findAll()
    {
        return m_studentService.findAllStudents();
    }

    @GetMapping("/find/student/id")
    public List<StudentResponseDTO> findStudentsByCourseId(@RequestParam("id") Long courseId)
    {
        return m_studentService.findStudentsByCourseId(courseId);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<StudentResponseDTO> findStudentById(@PathVariable("id") Long studentId)
    {
        return new ResponseEntity<>(m_studentService.findStudentById(studentId), HttpStatus.OK);
    }


    @PostMapping("/save")
    public ResponseEntity<StudentResponseDTO> saveStudent(@RequestBody StudentRequestDTO studentRequestDTO)
    {
        return new ResponseEntity<>(m_studentService.saveStudent(studentRequestDTO), HttpStatus.CREATED);
    }

    @PostMapping("/update/{id}")
    public StudentResponseDTO updateStudent(@PathVariable("id") Long id, @RequestBody StudentRequestDTO studentRequestDTO)
    {
        return m_studentService.updateStudent(id,studentRequestDTO);
    }

    @PostMapping("/dropCourse/{courseId}/from/{studentId}")
    public StudentResponseDTO removeCourse(@PathVariable Long courseId, @PathVariable Long studentId) {

        return m_studentService.deleteCourseFromStudent(courseId, studentId);
    }

    @PostMapping("/addCourse/{courseId}/to/{studentId}")
    public StudentResponseDTO addCourse(@PathVariable Long courseId, @PathVariable Long studentId) throws Exception
    {

        return m_studentService.addCourseToStudent(courseId, studentId);
    }
}
