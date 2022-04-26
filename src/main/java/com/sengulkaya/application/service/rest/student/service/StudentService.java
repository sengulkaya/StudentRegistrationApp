package com.sengulkaya.application.service.rest.student.service;

import com.sengulkaya.application.service.rest.student.converter.StudentConverter;
import com.sengulkaya.application.service.rest.student.data.dal.ServiceApplicationDAL;
import com.sengulkaya.application.service.rest.student.data.entity.Course;
import com.sengulkaya.application.service.rest.student.data.entity.Student;
import com.sengulkaya.application.service.rest.student.dto.requestDTO.StudentRequestDTO;
import com.sengulkaya.application.service.rest.student.dto.responseDTO.StudentResponseDTO;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class StudentService {
    private final ServiceApplicationDAL m_serviceApplicationDAL;
    private final StudentConverter m_studentConverter;

    public StudentService(ServiceApplicationDAL m_serviceApplicationDAL, StudentConverter m_studentConverter) {
        this.m_serviceApplicationDAL = m_serviceApplicationDAL;
        this.m_studentConverter = m_studentConverter;
    }

    public List<StudentResponseDTO> findAllStudents()
    {

        return StreamSupport.stream(m_serviceApplicationDAL.findAllStudents().spliterator(), false)
                .map(m_studentConverter::toStudentResponseDTO)
                .collect(Collectors.toList());

    }


    public StudentResponseDTO findStudentById(Long id)
    {
        return m_studentConverter.toStudentResponseDTO(m_serviceApplicationDAL.findStudentById(id));
    }



    public StudentResponseDTO saveStudent(StudentRequestDTO studentRequestDTO)
    {
        Student student = new Student();
        student.studentName = studentRequestDTO.getStudentName();
        m_serviceApplicationDAL.saveStudent(student);
        return m_studentConverter.toStudentResponseDTO(student);
    }


    public StudentResponseDTO updateStudent(Long studentId, StudentRequestDTO studentRequestDTO) {

        Student student = m_serviceApplicationDAL.findStudentById(studentId);

        student.studentName = studentRequestDTO.getStudentName();
        m_serviceApplicationDAL.saveStudent(student);

        return m_studentConverter.toStudentResponseDTO(student);
    }

    @Transactional
    public List<StudentResponseDTO> findStudentsByCourseId(Long courseId) //courseId dersini almış olanlar listelenecek
    {


        return StreamSupport.stream(m_serviceApplicationDAL.findAllStudents().spliterator(), false)
                .filter(student -> student.courses.contains(m_serviceApplicationDAL.findCourseById(courseId)))
                .map(m_studentConverter::toStudentResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public StudentResponseDTO deleteCourseFromStudent(Long courseId, Long studentId) {

        Student student = m_serviceApplicationDAL.findStudentById(studentId);
        Course course = m_serviceApplicationDAL.findCourseById(courseId);

        if (!student.courses.contains(course)){
            throw new IllegalArgumentException(course.courseName + " is not in the schedule already!");
        }
        course.students.remove(student);
        student.courses.remove(course);
        m_serviceApplicationDAL.saveCourse(course);
        m_serviceApplicationDAL.saveStudent(student);

        return m_studentConverter.toStudentResponseDTO(student);
    }

    @Transactional
    public StudentResponseDTO addCourseToStudent(Long courseId, Long studentId) throws Exception {

        Student student = m_serviceApplicationDAL.findStudentById(studentId);
        Course course = m_serviceApplicationDAL.findCourseById(courseId);

        if (student.courses.contains(course)){
            throw new IllegalArgumentException(course.courseName + " is already in the schedule!");
        }
        if (course.students.size() == course.capacity)
            throw new Exception(course.courseName + " is full. You can not register!");

        student.courses.add(course);
        course.students.add(student);
        m_serviceApplicationDAL.saveCourse(course);
        m_serviceApplicationDAL.saveStudent(student);

        return m_studentConverter.toStudentResponseDTO(student);
    }
}
