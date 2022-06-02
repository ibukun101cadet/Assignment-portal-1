package org.systemspecs.interns.service.serviceimpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.systemspecs.interns.domain.AssignmentSubmission;
import org.systemspecs.interns.domain.AssignmentUpload;
import org.systemspecs.interns.repository.AssignmentSubmissionRepo;
import org.systemspecs.interns.repository.AssignmentUploadRepo;
import org.systemspecs.interns.service.AssignmentSubmissionService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service

@Transactional

public class AssignmentSubmissionServiceImpl implements AssignmentSubmissionService {


    private final AssignmentSubmissionRepo assignmentRepo;
    private final AssignmentUploadRepo assignmentUploadRepo;
    private final AssignmentSubmissionRepo assignmentSubmissionRepo;


    public AssignmentSubmissionServiceImpl(AssignmentSubmissionRepo assignmentRepo, AssignmentUploadRepo assignmentUploadRepo, AssignmentSubmissionRepo assignmentSubmissionRepo) {
        this.assignmentRepo = assignmentRepo;
        this.assignmentUploadRepo = assignmentUploadRepo;
        this.assignmentSubmissionRepo = assignmentSubmissionRepo;
    }

    @Override
    public AssignmentSubmission saveFile(MultipartFile file, Long assignmentId, String matricNo) {
        AssignmentUpload uploaded = assignmentUploadRepo.findById(assignmentId).get();
        AssignmentSubmission assignment = null;
        try {
            assignment = new AssignmentSubmission(StringUtils.cleanPath(file.getOriginalFilename()), matricNo, file.getBytes(), file.getContentType(), "Submitted for grading", null, LocalDateTime.now(), uploaded);
            uploaded.getAssignmentSubmissions().add(assignment);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return assignment;
    }


    @Override
    public AssignmentSubmission getById(Long assignmentId) {
        return assignmentRepo.findById(assignmentId).get();
    }

    @Override
    public List<AssignmentSubmission> viewAllSubmissions(Long assignmentUploadId) {
        return
                assignmentSubmissionRepo.findByAssignmentUpload(assignmentUploadRepo
                        .findById(assignmentUploadId).get());


    }

    @Override
    public void addGrade(Long assignmentId, String grade) {
        AssignmentSubmission assignmentSubmission =
                assignmentSubmissionRepo.findById(assignmentId).get();

        assignmentSubmission.setGrade(grade);

    }


    @Override
    public void updateGrade(Long assignmentId, String grade) {
        AssignmentSubmission assignmentSubmission =
                assignmentSubmissionRepo.findById(assignmentId).get();

        assignmentSubmission.setGrade(grade);
    }

    @Override
    public void updateAssignment(Long assignmentId, MultipartFile file) throws IOException {
        AssignmentSubmission assignmentSubmission = assignmentSubmissionRepo.findById(assignmentId).get();
        assignmentSubmission.setDocName(file.getName());
        assignmentSubmission.setDocType(file.getContentType());
        assignmentSubmission.setContent(file.getBytes());


    }
}

