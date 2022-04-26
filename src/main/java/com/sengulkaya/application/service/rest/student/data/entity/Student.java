package com.sengulkaya.application.service.rest.student.data.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    public long id;

    @Column(nullable = false, unique = false)
    public String studentName;

    @ManyToMany(mappedBy = "students", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Set<Course> courses;
}
