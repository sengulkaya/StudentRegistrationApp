package com.sengulkaya.application.service.rest.student.dto.requestDTO;

public class CourseRequestDTO {
    private String courseName;
    private int capacity;


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

}
