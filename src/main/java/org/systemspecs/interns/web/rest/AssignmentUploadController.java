package org.systemspecs.interns.web.rest;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.systemspecs.interns.domain.AssignmentSubmission;
import org.systemspecs.interns.domain.AssignmentUpload;
import org.systemspecs.interns.service.AssignmentSubmissionService;
import org.systemspecs.interns.service.AssignmentUploadService;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping(path = "/api")
public class AssignmentUploadController {

    private final AssignmentUploadService assignmentUploadService;
    private final AssignmentSubmissionService assignmentSubmissionService;

    public AssignmentUploadController(AssignmentUploadService assignmentUploadService,
                                      AssignmentSubmissionService assignmentSubmissionService) {
        this.assignmentUploadService = assignmentUploadService;
        this.assignmentSubmissionService = assignmentSubmissionService;
    }

    @PutMapping("assignmentUpload/{assignmentId}/update")
    public String updateAssignment(
            @PathVariable("assignmentId") Long assignmentId,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) MultipartFile file) throws IOException {
        return assignmentUploadService.updateAssignmentUpload(assignmentId, title, file);
    }

    @GetMapping("/assignmentUpload/{assignmentId}")
    public ResponseEntity<ByteArrayResource> downloadCourseAssignment(@PathVariable Long assignmentId){
        AssignmentUpload assignment = assignmentUploadService.getAssignmentUploadById(assignmentId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(assignment.getDocType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment:filename=\""+assignment.getAssignmentTitle()+"\"")
                .body(new ByteArrayResource(assignment.getContent()));
    }

    @GetMapping("/assignmentUpload/{assignmentId}/viewAllSubmissions")
    public List<AssignmentSubmission> viewAllSubmissions(@PathVariable("assignmentId")Long assignmentId){
        return assignmentSubmissionService.viewAllSubmissions(assignmentId);
    }




}
