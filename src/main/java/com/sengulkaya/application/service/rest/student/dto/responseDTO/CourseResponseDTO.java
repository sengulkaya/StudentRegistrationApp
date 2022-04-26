package com.sengulkaya.application.service.rest.student.dto.responseDTO;

import java.util.List;
import java.util.Set;

public class CourseResponseDTO {
    private long id;
    private String courseName;
    private int capacity;
    private Set<String> studentNames;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Set<String> getStudentNames() {
        return studentNames;
    }

    public void setStudentNames(Set<String> studentNames) {
        this.studentNames = studentNames;
    }
}
