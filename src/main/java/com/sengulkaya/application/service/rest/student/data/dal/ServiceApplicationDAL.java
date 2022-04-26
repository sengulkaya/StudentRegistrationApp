package com.sengulkaya.application.service.rest.student.data.dal;

import com.sengulkaya.application.service.rest.student.data.entity.Course;
import com.sengulkaya.application.service.rest.student.data.entity.Student;
import com.sengulkaya.application.service.rest.student.data.repository.ICourseRepository;
import com.sengulkaya.application.service.rest.student.data.repository.IStudentRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ServiceApplicationDAL {
    private final IStudentRepository m_studentRepository;
    private final ICourseRepository m_courseRepository;

    public ServiceApplicationDAL(IStudentRepository m_studentRepository, ICourseRepository m_courseRepository) {
        this.m_studentRepository = m_studentRepository;
        this.m_courseRepository = m_courseRepository;
    }
    public Student saveStudent(Student student)
    {
        return m_studentRepository.save(student);
    }

    public Iterable<Student> findAllStudents()
    {
        return m_studentRepository.findAll();
    }


    public Student findStudentById(Long id)
    {
        return m_studentRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException(
                        "student with id: " + id + " could not be found"));
    }

    public Student findStudentByStudentName(String studentName)
    {
        return m_studentRepository.findByStudentName(studentName).orElseThrow(() ->
                new IllegalArgumentException(
                        "student with name: " + studentName + " could not be found"));
    }

    public void deleteCourseById(Long studentId)
    {
        m_studentRepository.deleteById(studentId);
    }

    public Course findCourseById(Long id)
    {
        return m_courseRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException(
                        "course with id: " + id + " could not be found"));
    }

    public Course findCourseByCourseName(String courseName)
    {
        return m_courseRepository.findByCourseName(courseName).orElseThrow(() ->
                new IllegalArgumentException(
                        "course: " + courseName + " could not be found"));
    }

    public Iterable<Course> findAllCourses()
    {
        return m_courseRepository.findAll();
    }



    public Course saveCourse(Course course)
    {
        return m_courseRepository.save(course);
    }

    public void deleteStudentById(Long courseId)
    {
        m_courseRepository.deleteById(courseId);
    }


}
