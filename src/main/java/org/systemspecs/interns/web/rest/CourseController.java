package org.systemspecs.interns.web.rest;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.systemspecs.interns.domain.AssignmentUpload;
import org.systemspecs.interns.domain.Course;
//import org.systemspecs.interns.service.AssignmentService;
import org.systemspecs.interns.service.AssignmentUploadService;
import org.systemspecs.interns.service.CourseService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/api")

public class CourseController {

    private final CourseService courseService;
    private final AssignmentUploadService assignmentUploadService;

    public CourseController(CourseService courseService,
                            AssignmentUploadService assignmentUploadService) {
        this.courseService = courseService;
        this.assignmentUploadService = assignmentUploadService;
    }

    @GetMapping("/course/{course_code}")
    public Course findACourseByCourseCode(@PathVariable("course_code")String course_code){
        return courseService.getCourseByCourseCode(course_code);
    }

    @PostMapping("/course/{courseId}/uploadAssignment")
    public void uploadAssignments(@PathVariable("courseId")Long courseId,
                                  @RequestParam("files") MultipartFile[] files,
                                  @RequestParam("title") String assignmentTitle,
                                  @RequestParam("dueDate") String dueDate) throws IOException {
        for (MultipartFile file : files) {
            assignmentUploadService.uploadAssignment(file,assignmentTitle, dueDate, courseId);
        }
    }

    @GetMapping("{course_code}/assignments")
    public List<AssignmentUpload> findAllCourseAssignments(@PathVariable("course_code")String course_code){
        return courseService.getAllAssignmentsByCourseCode(course_code);
    }

}




