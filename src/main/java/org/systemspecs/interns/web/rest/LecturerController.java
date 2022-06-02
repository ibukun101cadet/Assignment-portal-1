package org.systemspecs.interns.web.rest;

import lombok.Data;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.systemspecs.interns.domain.AssignmentSubmission;
import org.systemspecs.interns.domain.Course;
//import org.systemspecs.interns.service.AssignmentService;
import org.systemspecs.interns.service.AssignmentSubmissionService;
import org.systemspecs.interns.service.AssignmentUploadService;
import org.systemspecs.interns.service.LecturerService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/api")

public class LecturerController {
    private final LecturerService lecturerService;
    private final AssignmentUploadService assignmentUploadService;
    private final AssignmentSubmissionService assignmentSubmissionService;

    public LecturerController(LecturerService lecturerService, AssignmentUploadService assignmentUploadService, AssignmentSubmissionService assignmentSubmissionService) {
        this.lecturerService = lecturerService;
        this.assignmentUploadService = assignmentUploadService;
        this.assignmentSubmissionService = assignmentSubmissionService;
    }


    @GetMapping("lecturer/{lecturerId}/courses/")
    public List<Course> getAllCoursesTaught(@PathVariable("lecturerId")Long lecturerId){
        return lecturerService.getAllCoursesTaught(lecturerId);
    }

    @PostMapping("/lecturer/add_courses")
    public ResponseEntity<?> addCoursesToLecturer(@RequestBody CourseToLecturer form){//?
        lecturerService.addCourseToLecturer(form.getFullName(), form.getCourse_list());
        return ResponseEntity.ok().build();

    }

    @DeleteMapping("lecturer/{fullName}/remove/{course_code}")
    public void removeCourse(@PathVariable("course_code")String course_code, @PathVariable("fullName")String fullName){
        lecturerService.deleteCourseFromLecturer(fullName, course_code);
    }



    @PostMapping("/course/{courseId}/uploadAssignment")
    public void uploadAssignments(@PathVariable("courseId")Long courseId, @RequestParam("files") MultipartFile[] files) throws IOException {
        for (MultipartFile file : files) {
            assignmentUploadService.saveFile(file, courseId);
        }
    }

    @PutMapping("assignment/{assignmentId}/update/")
    public String updateAssignment(
            @PathVariable("assignmentId") Long assignmentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false)MultipartFile file) throws IOException {
       return assignmentUploadService.updateAssignment(assignmentId, name, file);

    }


    @GetMapping("/student_assignment/{assignmentId}")
    public ResponseEntity<ByteArrayResource> downloadAStudentAssignment(@PathVariable Long assignmentId){
        AssignmentSubmission assignment = assignmentSubmissionService.getById(assignmentId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(assignment.getDocType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\""+assignment.getDocName()+"\"")
                .body(new ByteArrayResource(assignment.getContent()));
    }

    @GetMapping("/assignment/{assignmentId}/viewAllSubmissions")
    public List<AssignmentSubmission> viewallsubmissions(@PathVariable("assignmentId")Long assignmentId){
        return assignmentSubmissionService.viewAllSubmissions(assignmentId);
    }

    @PostMapping("/assignment/{assignmentId}/gradeAssignment/{grade}")
    public String gradeAssignment(@PathVariable("assignmentId")Long assignmentId, @PathVariable("grade")String grade){

        assignmentSubmissionService.addGrade(assignmentId, grade);
        return "Assignment graded";
    }


    @PutMapping("/assignment/{assignmentId}/updateGrade/{grade}")
    public String RegradeAssignment(@PathVariable("assignmentId")Long assignmentId, @PathVariable("grade")String grade){

        assignmentSubmissionService.updateGrade(assignmentId, grade);
        return "Assignment grade changed";
    }




//
//    @GetMapping("/downloadFile/{fileId}")
//    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Long fileId){
//        Course_assignment assignment = assignmentService.getFile(fileId).get();
//        return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType(assignment.getDocType()))
//                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\""+assignment.getDocName()+"\"")
//                .body(new ByteArrayResource(assignment.getContent()));
//    }

   


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




