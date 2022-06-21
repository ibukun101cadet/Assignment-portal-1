package org.systemspecs.interns.web.rest;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.systemspecs.interns.domain.AssignmentSubmission;
import org.systemspecs.interns.domain.AssignmentUpload;
import org.systemspecs.interns.dto.response.ResponseData;
import org.systemspecs.interns.dto.response.GenericResponse;
import org.systemspecs.interns.service.AssignmentSubmissionService;
import org.systemspecs.interns.service.AssignmentUploadService;

import java.io.IOException;


@RestController
@RequestMapping(path = "/api/assignmentUpload/")
public class AssignmentUploadController {

    private final AssignmentUploadService assignmentUploadService;
    private final AssignmentSubmissionService assignmentSubmissionService;

    public AssignmentUploadController(AssignmentUploadService assignmentUploadService,
                                      AssignmentSubmissionService assignmentSubmissionService) {
        this.assignmentUploadService = assignmentUploadService;
        this.assignmentSubmissionService = assignmentSubmissionService;
    }

    @PutMapping("{assignmentId}/update")
    public ResponseEntity<GenericResponse> updateAssignmentUpload(
            @PathVariable("assignmentId") Long assignmentId,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) MultipartFile file) throws IOException {
        System.out.println("Updating course assignment upload");

        GenericResponse response = new GenericResponse();
        assignmentUploadService.updateAssignmentUpload(assignmentId, title, file);
        AssignmentUpload assignment =
                assignmentUploadService.getAssignmentUploadById(assignmentId);
        response.setCode("00");
        response.setMessage("Assignment upload modified");
        response.setData(assignment);
        response.setMetadata(null);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("{assignmentId}/download")
    public ResponseEntity<ByteArrayResource> downloadCourseAssignment(@PathVariable Long assignmentId){
        System.out.println("downloading course assignment");

        AssignmentUpload assignment = assignmentUploadService.getAssignmentUploadById(assignmentId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(assignment.getDocType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment:filename=\""+assignment.getAssignmentTitle()+"\"")
                .body(new ByteArrayResource(assignment.getContent()));
    }

    @PostMapping("{assignmentId}/student/{matNo}/submitAssignment")
    public ResponseEntity<GenericResponse> submitAssignment(@PathVariable("assignmentId")Long assignmentId,
                                                            @PathVariable("matNo") String matNo,
                                                            @RequestParam("file") MultipartFile file) throws IOException {
        System.out.println("submitting course assignment");

        AssignmentSubmission assignment = null;
        assignment = assignmentSubmissionService.submitAssignment(file, assignmentId, matNo);

        String downloadURl = " ";

        downloadURl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(String.valueOf(assignment.getAssignmentSubmissionId()))
                .toUriString();

        ResponseData responseData = new ResponseData(
                assignment.getDocName(),
                downloadURl,
                file.getContentType(),
                file.getSize());

        GenericResponse response = new GenericResponse();

        response.setCode("00");
        response.setMessage("Assignment successfully submitted");
        response.setData(responseData);
        response.setMetadata(null);

        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }


    @GetMapping("{assignmentId}/viewAllSubmissions")
    public ResponseEntity<GenericResponse> viewAllSubmissions(@PathVariable("assignmentId")Long assignmentId){
        System.out.println("viewing all course assignment submissions");
        GenericResponse response = new GenericResponse();
        response.setCode("00");
        response.setMessage("Assignment upload modified");
        response.setData(assignmentSubmissionService.viewAllSubmissions(assignmentId));
        response.setMetadata(null);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    }





