package org.systemspecs.interns.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Entity
@Getter
@Setter


public class AssignmentUpload {
    @SequenceGenerator(
            name = "lecturer__course_assignment_upload_sequence",
            sequenceName = "lecturer__course_assignment_upload_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "lecturer__course_assignment_upload_sequence"
    )
    private Long assignmentUploadId;


    @Column(nullable = false)
    private String assignmentTitle;

    @Column(nullable = false)
    private String docType;


    @Column(nullable = false)
    @Lob
    private byte[] content;

    @FutureOrPresent(message = "Date must be in the future or present")
    private LocalDateTime dueDate;


    private LocalDateTime uploadTime;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_id",
            nullable = false)
    @JsonIgnore
    private Course course;

    @OneToMany(mappedBy = "assignmentUpload", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AssignmentSubmission> assignmentSubmissions;

    public AssignmentUpload() {
    }

    public AssignmentUpload(Long assignmentUploadId,
                            String AssignmentTitle,
                            String docType,
                            byte[] content,
                            LocalDateTime uploadTime,
                            LocalDateTime dueDate,
                            Course course) {
        this.assignmentUploadId = assignmentUploadId;
        this.assignmentTitle = AssignmentTitle;
        this.docType = docType;
        this.uploadTime = uploadTime;
        this.content = content;
        this.dueDate = dueDate;
        this.course = course;
    }

    public AssignmentUpload(String AssignmentTitle,
                            String docType,
                            byte[] content,
                            LocalDateTime dueDate,
                            LocalDateTime uploadTime,
                            Course course) {
        this.assignmentTitle = AssignmentTitle;
        this.docType = docType;
        this.content = content;
        this.dueDate = dueDate;
        this.uploadTime = uploadTime;
        this.course = course;
    }

    public Long getAssignmentUploadId() {
        return assignmentUploadId;
    }

    public String getAssignmentTitle() {
        return assignmentTitle;
    }

    public void setAssignmentTitle(String assignmentTitle) {
        this.assignmentTitle = assignmentTitle;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDateTime getUploadTime() {
        return uploadTime;
    }

    public LocalDateTime setUploadTime() {
        return uploadTime;
    }


    public List<AssignmentSubmission> getAssignmentSubmissions() {
        return assignmentSubmissions;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }


}
