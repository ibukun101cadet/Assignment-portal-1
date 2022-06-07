package org.systemspecs.interns.web.rest;


import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.systemspecs.interns.domain.AssignmentSubmission;
import org.systemspecs.interns.dto.response.GenericResponse;
import org.systemspecs.interns.service.AssignmentSubmissionService;

import java.io.IOException;

@RestController
@RequestMapping(path = "/api/assignmentSubmission/")
public class AssignmentSubmissionController {
    private final AssignmentSubmissionService assignmentSubmissionService;

    public AssignmentSubmissionController(AssignmentSubmissionService assignmentSubmissionService) {
        this.assignmentSubmissionService = assignmentSubmissionService;
    }


    @GetMapping("{assignmentId}/download/")
    public ResponseEntity<ByteArrayResource> downloadStudentAssignment(@PathVariable Long assignmentId){
        System.out.println("Downloading student assignment");
        AssignmentSubmission assignment =
                    assignmentSubmissionService.getAssignmentSubmissionById(assignmentId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(assignment.getDocType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment:filename=\""+assignment.getDocName()+"\"")
                .body(new ByteArrayResource(assignment.getContent()));
    }

    @PostMapping("{assignmentId}/gradeAssignment/{grade}")
    public ResponseEntity<GenericResponse> gradeAssignment(@PathVariable("assignmentId")Long assignmentId,
                                                           @PathVariable("grade")String grade){
        System.out.println("Grading student assignment");
        GenericResponse response = new GenericResponse();
        assignmentSubmissionService.addGrade(assignmentId, grade);
        AssignmentSubmission assignment =
                assignmentSubmissionService.getAssignmentSubmissionById(assignmentId);
        response.setCode("00");
        response.setMessage("Assignment graded");
        response.setData(assignment);
        response.setMetadata(null);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PutMapping("{assignmentId}/updateGrade/{grade}")
    public ResponseEntity<GenericResponse> RegradeAssignment(@PathVariable("assignmentId")Long assignmentId,
                                    @PathVariable("grade")String grade){
        System.out.println("Updating student assignment grade");


        GenericResponse response = new GenericResponse();
        assignmentSubmissionService.updateGrade(assignmentId, grade);
        AssignmentSubmission assignment =
                assignmentSubmissionService.getAssignmentSubmissionById(assignmentId);
        response.setCode("00");
        response.setMessage("Assignment grade changed!");
        response.setData(assignment);
        response.setMetadata(null);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("{assignmentId}/checkGrade")
    public String checkGrade(@PathVariable("assignmentId")Long assignmentId){
        return assignmentSubmissionService.checkGrade(assignmentId);
    }// is dto necessary //is this even necessary?

    @PutMapping("/{assignmentId}/updateAssignmentSubmission")
    public ResponseEntity<GenericResponse>  updateAssignmentSubmission(@PathVariable("assignmentId")Long assignmentId, @RequestParam("file") MultipartFile file) throws IOException {
        System.out.println("Updating assignment submission");

        GenericResponse response = new GenericResponse();
        assignmentSubmissionService.updateAssignmentSubmission(assignmentId, file);
        AssignmentSubmission assignment =
                assignmentSubmissionService.getAssignmentSubmissionById(assignmentId);
        response.setCode("00");
        response.setMessage("Assignment submission modified");
        response.setData(assignment);
        response.setMetadata(null);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}//TODO DELETE ASSIGNMENT UPLOAD AND SUBMISSIONS




