package org.systemspecs.interns.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter
@Setter
//@EqualsAndHashCode//?

public class AssignmentUpload {
    public Long getAssignmentUploadId() {
        return assignmentUploadId;
    }

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

    private String docName;

    private String docType;

    @Lob
    private byte[] content;

    public AssignmentUpload() {
    }

    public AssignmentUpload(Long assignmentUploadId, String docName, String docType, byte[] content, Course course) {
        this.assignmentUploadId = assignmentUploadId;
        this.docName = docName;
        this.docType = docType;
        this.content = content;
        this.course = course;
    }

    public AssignmentUpload(String docName, String docType, byte[] content, Course course) {
        this.docName = docName;
        this.docType = docType;
        this.content = content;
        this.course = course;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
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

    //    private String submission_status;//can be enum?
//
//    private String grading_status;//also enum?
//    private Date due_date;
//    private Date uploadTime;
// TODO: SET UPLOAD TIME AND DUE DATE AND TIME REMAINING

    public List<AssignmentSubmission> getAssignmentSubmissions() {
        return assignmentSubmissions;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_id",
            nullable = false)
    @JsonIgnore
    private Course course;

    @OneToMany(mappedBy = "assignmentUpload", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AssignmentSubmission> assignmentSubmissions;




}
