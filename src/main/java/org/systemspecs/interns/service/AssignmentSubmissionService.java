package org.systemspecs.interns.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.systemspecs.interns.domain.AssignmentSubmission;

import java.io.IOException;
import java.util.List;

@Service
public interface AssignmentSubmissionService {

    AssignmentSubmission submitAssignment(MultipartFile file, Long assignmentId, String matNo);

    AssignmentSubmission getAssignmentSubmissionById(Long assignmentId);

    List<AssignmentSubmission> viewAllSubmissions(Long assignmentUploadId);

    void addGrade(Long assignmentId, String grade);

    void updateGrade(Long assignmentId, String grade);

    void updateAssignmentSubmission(Long assignmentId, MultipartFile file) throws IOException;

    String checkGrade(Long assignmentId);
//    public Optional<Course_assignment> getFile(Long fileId){
//        return repo.findById(fileId);
//
//    }
//    public List<Course_assignment> getFiles(){
//        return repo.findAll();
//    }
}
