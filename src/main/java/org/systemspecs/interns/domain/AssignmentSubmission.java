package org.systemspecs.interns.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Entity
@Getter
@Setter


public class AssignmentSubmission {

    @SequenceGenerator(
            name = "student_course_assignment_submission_sequence",
            sequenceName = "student_course_assignment_submission_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_course_assignment_submission_sequence"
    )


    private Long assignmentSubmissionId;

    @Column(nullable = false)
    private String docName;

    @Column(nullable = false)
    private String matricNo;

    @Lob
    @Column(nullable = false)
    private byte[] content;

    @Column(nullable = false)
    private String docType;

    @Column(nullable = false)
    private String submissionStatus;


    private String grade;

    @Column(nullable = false)
    private String gradingStatus;

    @Column(nullable = false)
    private String lastModified;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "assignmentUploadId",
            nullable = false)
    @JsonIgnore
    private AssignmentUpload assignmentUpload;


    public AssignmentSubmission() {
    }

    public AssignmentSubmission(Long assignmentSubmissionId,
                                String docName,
                                String matricNo,
                                byte[] content,
                                String docType,
                                String submissionStatus,
                                String gradingStatus,
                                String grade,
                                String lastModified,
                                AssignmentUpload assignmentUpload) {
        this.assignmentSubmissionId = assignmentSubmissionId;
        this.docName= docName;
        this.matricNo= matricNo;
        this.content= content;
        this.docType= docType;
        this.submissionStatus = submissionStatus;
        this.gradingStatus = gradingStatus;
        this.grade = grade;
        this.lastModified = lastModified;
        this.assignmentUpload= assignmentUpload;

    }

    public AssignmentSubmission(String docName,
                                String matricNo,
                                byte[] content,
                                String docType,
                                String submissionStatus,
                                String gradingStatus,
                                String grade,
                                String lastModified,
                                AssignmentUpload assignmentUpload) {
        this.docName= docName;
        this.matricNo = matricNo;
        this.content= content;
        this.docType= docType;
        this.submissionStatus = submissionStatus;
        this.gradingStatus = gradingStatus;
        this.grade = grade;
        this.lastModified = lastModified;
        this.assignmentUpload= assignmentUpload;

    }

    public Long getAssignmentSubmissionId() {
        return assignmentSubmissionId;
    }


    public void setAssignmentSubmissionId(Long assignmentSubmissionId) {
        this.assignmentSubmissionId = assignmentSubmissionId;
    }
    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getSubmissionStatus() {
        return submissionStatus;
    }

    public void setSubmissionStatus(String submissionStatus) {
        this.submissionStatus = submissionStatus;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getMatricNo() {
        return matricNo;
    }

    public void setMatricNo(String matricNo) {
        this.matricNo = matricNo;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public AssignmentUpload getAssignmentUpload() {
        return assignmentUpload;
    }

    public void setAssignmentUpload(AssignmentUpload assignmentUpload) {
        this.assignmentUpload = assignmentUpload;
    }

    public String getGradingStatus() {
        return gradingStatus;
    }

    public void setGradingStatus(String gradingStatus) {
        this.gradingStatus = gradingStatus;
    }

}
