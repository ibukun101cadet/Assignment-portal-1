package org.systemspecs.interns.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;


@Entity
@Getter
@Setter
//@EqualsAndHashCode//?

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


    private String matricNo;

    @Lob
    private byte[] content;


    private String docType;


    private String status;

    private String grade;

    private LocalDateTime dateCreated;

    public AssignmentUpload getAssignmentUpload() {
        return assignmentUpload;
    }

    public void setAssignmentUpload(AssignmentUpload assignmentUpload) {
        this.assignmentUpload = assignmentUpload;
    }

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
                                String status,
                                String grade,
                                LocalDateTime dateCreated,
                                AssignmentUpload assignmentUpload) {
        this.assignmentSubmissionId = assignmentSubmissionId;
        this.docName= docName;
        this.matricNo= matricNo;
        this.content= content;
        this.docType= docType;
        this.status = status;
        this.grade = grade;
        this.dateCreated = dateCreated;
        this.assignmentUpload= assignmentUpload;

    }

    public AssignmentSubmission(String docName,
                                String matricNo,
                                byte[] content,
                                String docType,
                                String status,
                                String grade,
                                LocalDateTime dateCreated,
                                AssignmentUpload assignmentUpload) {
        this.docName= docName;
        this.matricNo = matricNo;
        this.content= content;
        this.docType= docType;
        this.status = status;
        this.grade = grade;
        this.dateCreated = dateCreated;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
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

//TODO: CHECK IF ASSIGNMENT LATE OR EARLY




}
