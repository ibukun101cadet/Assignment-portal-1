package org.systemspecs.interns.web.rest;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.systemspecs.interns.domain.Course;
import org.systemspecs.interns.service.LecturerService;


import java.util.List;

@RestController
@RequestMapping(path = "/api/lecturer")

public class LecturerController {
    private final LecturerService lecturerService;


    public LecturerController(LecturerService lecturerService) {
        this.lecturerService = lecturerService;

    }


    @GetMapping("/{lecturerId}/courses/")
    public List<Course> getAllCoursesTaught(@PathVariable("lecturerId")Long lecturerId){
        return lecturerService.getAllCoursesTaught(lecturerId);
    }

    @PostMapping("/add_courses")
    public ResponseEntity<?> addCoursesToLecturer(@RequestBody CourseToLecturer form){
        lecturerService.addCourseToLecturer(form.getFullName(), form.getCourse_list());
        return ResponseEntity.ok().build();

    }

    @DeleteMapping("/{fullName}/remove/{course_code}")
    public void removeCourse(@PathVariable("course_code")String course_code,
                             @PathVariable("fullName")String fullName){
        lecturerService.deleteCourseFromLecturer(fullName, course_code);
    }

}

@Data
class CourseToLecturer{
    public CourseToLecturer() {
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




