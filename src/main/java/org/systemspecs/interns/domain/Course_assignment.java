package org.systemspecs.interns.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;



@Entity
@Getter
@Setter
//@EqualsAndHashCode//?
@AllArgsConstructor
@NoArgsConstructor
public class Course_assignment {
    @SequenceGenerator(
            name = "student_course_assignment_sequence",
            sequenceName = "student_course_assignment_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_course_assignment_sequence"
    )
    private Long assignmentId;
    @Column(nullable = false)

    private String docName;

    private String docType;

    @Lob
    private byte[] content;


    public Course_assignment( String docName, String docType, byte[] content) {
        this.docName = docName;
        this.docType = docType;
        this.content = content;
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


}
