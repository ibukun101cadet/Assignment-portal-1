package org.systemspecs.interns.web.rest;

import lombok.Data;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.systemspecs.interns.domain.AssignmentSubmission;
import org.systemspecs.interns.domain.AssignmentUpload;
import org.systemspecs.interns.domain.Course;
//import org.systemspecs.interns.service.AssignmentService;
import org.systemspecs.interns.dto.ResponseData;
import org.systemspecs.interns.service.AssignmentSubmissionService;
import org.systemspecs.interns.service.AssignmentUploadService;
import org.systemspecs.interns.service.CourseService;
import org.systemspecs.interns.service.StudentService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/api")

public class StudentController {
    private final StudentService studentService;
    private final CourseService courseService;
    private final AssignmentUploadService assignmentUploadService;
    private final AssignmentSubmissionService assignmentSubmissionService;

    public StudentController(StudentService studentService, CourseService courseService, AssignmentUploadService assignmentUploadService, AssignmentSubmissionService assignmentSubmissionService) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.assignmentUploadService = assignmentUploadService;
        this.assignmentSubmissionService = assignmentSubmissionService;
    }





    @GetMapping
    public String homepage(){
        return "Welcome";
    }

    @PostMapping("/student/add_courses")
    public ResponseEntity<?> addCoursesToStudent(@RequestBody CourseToStudent form){//?
        studentService.addCourseToStudent(form.getFullName(), form.getCourse_list());
        return ResponseEntity.ok().build();

    }
    @GetMapping("/courses/student_id/{studentId}")
    public List<Course> getAllCourses(@PathVariable("studentId")Long studentId){
        return studentService.getAllCourses(studentId);
    }

    @DeleteMapping("student/{fullName}/unenroll/{course_code}")
    public void removeCourse(@PathVariable("course_code")String course_code, @PathVariable("fullName")String fullName){
        studentService.deleteCourseFromStudent(fullName, course_code);
    }


    @GetMapping("assignments/course/{course_code}")
    public List<AssignmentUpload> findAllCourseAssignments(@PathVariable("course_code")String course_code){
        return courseService.getByAssignmentCourseCode(course_code);
    }

    @GetMapping("/assignment/{assignmentId}")
    public ResponseEntity<ByteArrayResource> downloadCourseAssignment(@PathVariable Long assignmentId){
        AssignmentUpload assignment = assignmentUploadService.getById(assignmentId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(assignment.getDocType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\""+assignment.getDocName()+"\"")
                .body(new ByteArrayResource(assignment.getContent()));
    }

    @PostMapping("{matNo}/assignment/{assignmentId}/submitAssignment")
    public ResponseData submitAssignment(@PathVariable("assignmentId")Long assignmentId, @PathVariable("matNo") String matNo, @RequestParam("file") MultipartFile file) throws IOException {
            AssignmentSubmission assignment = null;
            assignment = assignmentSubmissionService.saveFile(file, assignmentId, matNo);

            String downloadURl = "";

            downloadURl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/download/")
                    .path(String.valueOf(assignment.getAssignmentSubmissionId()))
                    .toUriString();

            return new ResponseData(assignment.getDocName(),
                    downloadURl,
                    file.getContentType(),
                    file.getSize());
        }

    @PutMapping("/{assignmentId}/updateAssignment")
    public void updateAssignment(@PathVariable("assignmentId")Long assignmentId, @RequestParam("file") MultipartFile file) throws IOException {
        assignmentSubmissionService.updateAssignment(assignmentId, file);
    }



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




