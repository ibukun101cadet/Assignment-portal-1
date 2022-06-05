package org.systemspecs.interns.web.rest;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.systemspecs.interns.domain.AssignmentSubmission;
import org.systemspecs.interns.domain.Course;
import org.systemspecs.interns.dto.ResponseData;
import org.systemspecs.interns.service.AssignmentSubmissionService;
import org.systemspecs.interns.service.StudentService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/api/student")

public class StudentController {
    private final StudentService studentService;
    private final AssignmentSubmissionService assignmentSubmissionService;

    public StudentController(StudentService studentService,AssignmentSubmissionService assignmentSubmissionService) {
        this.studentService = studentService;
        this.assignmentSubmissionService = assignmentSubmissionService;
    }


    @GetMapping
    public String homepage(){
        return "Welcome";
    }

    @PostMapping("/add_courses")
    public ResponseEntity<?> addCoursesToStudent(@RequestBody CourseToStudent form){//?
        studentService.addCourseToStudent(form.getFullName(), form.getCourse_list());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/courses/{studentId}")
    public List<Course> getAllCourses(@PathVariable("studentId")Long studentId){
        return studentService.getAllCoursesTaken(studentId);
    }

    @DeleteMapping("/{fullName}/unenroll/{course_code}")
    public void removeCourse(@PathVariable("course_code")String course_code, @PathVariable("fullName")String fullName){
        studentService.deleteCourseFromStudent(fullName, course_code);
    }


    @PostMapping("/{matNo}/assignment/{assignmentId}/submitAssignment")
    public ResponseData submitAssignment(@PathVariable("assignmentId")Long assignmentId,
                                         @PathVariable("matNo") String matNo,
                                         @RequestParam("file") MultipartFile file) throws IOException {
            AssignmentSubmission assignment = null;
            assignment = assignmentSubmissionService.submitAssignment(file, assignmentId, matNo);

            String downloadURl = "";

            downloadURl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/download/")
                    .path(String.valueOf(assignment.getAssignmentSubmissionId()))
                    .toUriString();

            return new ResponseData(assignment.getDocName(),
                    downloadURl,
                    file.getContentType(),
                    file.getSize());
        }// is this necessary??//one to one btwn student?




}

@Data
class CourseToStudent{
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




