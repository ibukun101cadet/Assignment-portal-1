package org.systemspecs.interns.web.rest;

import lombok.Data;

import java.util.List;

@Data
class CourseToStudent {
    public CourseToStudent() {
    }

    private String fullName;


    public String getFullName() {
        return fullName;
    }

    public List<String> getCourse_list() {
        return course_list;
    }

    private List<String> course_list;
}
