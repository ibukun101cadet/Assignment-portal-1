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
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;

@Service

@Transactional

public class AssignmentSubmissionServiceImpl implements AssignmentSubmissionService {


    private final AssignmentUploadRepo assignmentUploadRepo;
    private final AssignmentSubmissionRepo assignmentSubmissionRepo;


    public AssignmentSubmissionServiceImpl(AssignmentUploadRepo assignmentUploadRepo,
                                           AssignmentSubmissionRepo assignmentSubmissionRepo) {

        this.assignmentUploadRepo = assignmentUploadRepo;
        this.assignmentSubmissionRepo = assignmentSubmissionRepo;
    }

    @Override
    public AssignmentSubmission submitAssignment(MultipartFile file,
                                                 Long assignmentId,
                                                 String matricNo) {
        AssignmentUpload uploaded = assignmentUploadRepo.findById(assignmentId).get();
        AssignmentSubmission assignment = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMM dd,yyyy HH:mm a");
        //Local date time instance
        LocalDateTime localDateTime = LocalDateTime.now();
        //Get formatted String
        String ldtString = formatter.format(localDateTime);
        try {
            assignment = new AssignmentSubmission(StringUtils.cleanPath(
                    file.getOriginalFilename()),
                    matricNo,
                    file.getBytes(),
                    file.getContentType(),
                    "Submitted for grading",
                    "Not graded",
                    null,
                    ldtString,
                    uploaded);
            uploaded.getAssignmentSubmissions().add(assignment);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return assignment;//one to one btwn student?
        //todo change matno to id
    }


    @Override
    public AssignmentSubmission getAssignmentSubmissionById(Long assignmentId) {
        AssignmentSubmission assignmentSubmission;
        try {
            assignmentSubmission = assignmentSubmissionRepo.findById(assignmentId).get();
        } catch (Exception e) {
            throw new NoSuchElementException("Student has not submitted");
        }
        return assignmentSubmission;

    }

    @Override
    public List<AssignmentSubmission> viewAllSubmissions(Long assignmentUploadId) {

        return assignmentSubmissionRepo.findByAssignmentUpload(
                assignmentUploadRepo.findById(assignmentUploadId).get());
    }

    @Override
    public void addGrade(Long assignmentId, String grade) {
        AssignmentSubmission assignmentSubmission =
                assignmentSubmissionRepo.findById(assignmentId).get();
        assignmentSubmission.setGrade(grade);
        assignmentSubmission.setGradingStatus("Graded");
    }

    @Override
    public void updateGrade(Long assignmentId, String grade) {
        AssignmentSubmission assignmentSubmission =
                assignmentSubmissionRepo.findById(assignmentId).get();
        assignmentSubmission.setGrade(grade);
    }

    @Override
    public void updateAssignmentSubmission(Long assignmentId,
                                           MultipartFile file)
            throws IOException {
        AssignmentSubmission assignmentSubmission =
                assignmentSubmissionRepo.findById(assignmentId).get();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy 'at' hh:mm a");
        //Local date time instance
        LocalDateTime localDateTime = LocalDateTime.now();
        //Get formatted String
        String ldtString = formatter.format(localDateTime);

        assignmentSubmission.setDocName(file.getName());
        assignmentSubmission.setDocType(file.getContentType());
        assignmentSubmission.setContent(file.getBytes());
        assignmentSubmission.setLastModified(ldtString);
    }

    @Override
    public String checkGrade(Long assignmentId) {
        AssignmentSubmission assignmentSubmission =
                assignmentSubmissionRepo.findById(assignmentId).get();
        return assignmentSubmission.getGrade();
    }
}

