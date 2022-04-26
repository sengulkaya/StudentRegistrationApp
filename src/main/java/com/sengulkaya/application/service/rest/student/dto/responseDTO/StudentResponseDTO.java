package com.sengulkaya.application.service.rest.student.dto.responseDTO;

import java.util.List;
import java.util.Set;

public class StudentResponseDTO {
    private long id;
    private String studentName;
    private Set<String> courseNames;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Set<String> getCourseNames() {
        return courseNames;
    }

    public void setCourseNames(Set<String> courseNames) {
        this.courseNames = courseNames;
    }
}
