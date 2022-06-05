package org.systemspecs.interns.web.rest;


import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.systemspecs.interns.domain.AssignmentSubmission;
import org.systemspecs.interns.service.AssignmentSubmissionService;

import java.io.IOException;

@RestController
@RequestMapping(path = "/api")
public class AssignmentSubmissionController {
    private final AssignmentSubmissionService assignmentSubmissionService;

    public AssignmentSubmissionController(AssignmentSubmissionService assignmentSubmissionService) {
        this.assignmentSubmissionService = assignmentSubmissionService;
    }



    @GetMapping("/assignmentSubmission/download/{assignmentId}")
    public ResponseEntity<ByteArrayResource> downloadAStudentAssignment(@PathVariable Long assignmentId){
        AssignmentSubmission assignment =
                    assignmentSubmissionService.getAssignmentSubmissionById(assignmentId);


        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(assignment.getDocType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment:filename=\""+assignment.getDocName()+"\"")
                .body(new ByteArrayResource(assignment.getContent()));
    }


    @PostMapping("/assignmentSubmission/{assignmentId}/gradeAssignment/{grade}")
    public String gradeAssignment(@PathVariable("assignmentId")Long assignmentId,
                                  @PathVariable("grade")String grade){

        assignmentSubmissionService.addGrade(assignmentId, grade);
        return "Assignment graded";
    }


    @PutMapping("/assignmentSubmission/{assignmentId}/updateGrade/{grade}")
    public String RegradeAssignment(@PathVariable("assignmentId")Long assignmentId,
                                    @PathVariable("grade")String grade){

        assignmentSubmissionService.updateGrade(assignmentId, grade);
        return "Assignment grade changed";
    }

    @GetMapping("assignmentSubmission/{assignmentId}/checkGrade")
    public String checkGrade(@PathVariable("assignmentId")Long assignmentId){
        return assignmentSubmissionService.checkGrade(assignmentId);
    }

    @PutMapping("/{assignmentId}/updateAssignmentSubmission")
    public void updateAssignment(@PathVariable("assignmentId")Long assignmentId, @RequestParam("file") MultipartFile file) throws IOException {
        assignmentSubmissionService.updateAssignmentSubmission(assignmentId, file);
    }


}
