package com.sengulkaya.application.service.rest.student.service;

import com.sengulkaya.application.service.rest.student.converter.CourseConverter;
import com.sengulkaya.application.service.rest.student.data.dal.ServiceApplicationDAL;
import com.sengulkaya.application.service.rest.student.data.entity.Course;
import com.sengulkaya.application.service.rest.student.data.entity.Student;
import com.sengulkaya.application.service.rest.student.dto.requestDTO.CourseRequestDTO;
import com.sengulkaya.application.service.rest.student.dto.responseDTO.CourseResponseDTO;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CourseService {
    private final ServiceApplicationDAL m_serviceApplicationDAL;
    private final CourseConverter m_courseConverter;

    public CourseService(ServiceApplicationDAL m_serviceApplicationDAL, CourseConverter m_courseConverter) {
        this.m_serviceApplicationDAL = m_serviceApplicationDAL;
        this.m_courseConverter = m_courseConverter;
    }


    public List<CourseResponseDTO> findAllCourses()
    {

        return StreamSupport.stream(m_serviceApplicationDAL.findAllCourses().spliterator(), false)
                .map(m_courseConverter::toCourseResponseDTO)
                .collect(Collectors.toList());

    }

    public CourseResponseDTO findCourseById(Long courseId)
    {
        return m_courseConverter.toCourseResponseDTO(m_serviceApplicationDAL.findCourseById(courseId));
    }


    public List<CourseResponseDTO> findCoursesByStudentId(Long studentId) //studentId öğrencinin almış olduğu dersler listelenecek
    {


        return StreamSupport.stream(m_serviceApplicationDAL.findAllCourses().spliterator(), false)
                .filter(course -> course.students.contains(m_serviceApplicationDAL.findStudentById(studentId)))
                .map(m_courseConverter::toCourseResponseDTO)
                .collect(Collectors.toList());
    }


    public CourseResponseDTO saveCourse(CourseRequestDTO courseRequestDTO)
    {
        Course course  = new Course();
        course.courseName = courseRequestDTO.getCourseName();
        course.capacity = courseRequestDTO.getCapacity();

        m_serviceApplicationDAL.saveCourse(course);
        return m_courseConverter.toCourseResponseDTO(course);
    }
    @Transactional
    public CourseResponseDTO addStudentToCourse(Long studentId, Long courseId) throws Exception {
        Course course = m_serviceApplicationDAL.findCourseById(courseId);
        Student student = m_serviceApplicationDAL.findStudentById(studentId);

        if (course.students.contains(student)) {
            throw new IllegalArgumentException(student.studentName + "is already registered to the course");
        }

        if (course.students.size() == course.capacity) {
            throw new Exception(course.courseName + " capacity is full");
        }

        course.students.add(student);
        student.courses.add(course);
        m_serviceApplicationDAL.saveCourse(course);
        m_serviceApplicationDAL.saveStudent(student);

        return m_courseConverter.toCourseResponseDTO(course);
    }


    @Transactional//use when operate on tables have many to many relations
    public CourseResponseDTO deleteStudentFromCourse(Long studentId, Long courseId) {

        Student student = m_serviceApplicationDAL.findStudentById(studentId);
        Course course = m_serviceApplicationDAL.findCourseById(courseId);

        if (!course.students.contains(student)){
            throw new IllegalArgumentException("This student is not registered to the course!");
        }
        course.students.remove(student);
        student.courses.remove(course);
        m_serviceApplicationDAL.saveCourse(course);
        m_serviceApplicationDAL.saveStudent(student);

        return m_courseConverter.toCourseResponseDTO(course);
    }

    @Transactional
    public CourseResponseDTO updateCourse(Long id,CourseRequestDTO courseRequestDTO) {

        Course course = m_serviceApplicationDAL.findCourseById(id);

        course.courseName = courseRequestDTO.getCourseName();
        course.capacity = courseRequestDTO.getCapacity();
        m_serviceApplicationDAL.saveCourse(course);

        return m_courseConverter.toCourseResponseDTO(course);
    }
}
