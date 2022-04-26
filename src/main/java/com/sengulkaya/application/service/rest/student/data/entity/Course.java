package com.sengulkaya.application.service.rest.student.data.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;



@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    public long id;

    @Column(nullable = false, unique = false)
    public String courseName;

    @Column(nullable = false)
    public int capacity;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "students_to_course",
            joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "course_id", nullable = false, updatable = false),
            inverseJoinColumns =  @JoinColumn(name = "student_id", referencedColumnName = "student_id", nullable = false, updatable = false))
    public Set<Student> students;
}
