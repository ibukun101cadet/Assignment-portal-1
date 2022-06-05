package org.systemspecs.interns.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;



@Entity
@Getter
@Setter

public class Course {
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_sequence"
    )
    private Long courseId;

    @Column(nullable = false)
    private String course_title;

    @Column(nullable = false)
    private String course_code;

    @Column(nullable = false)
    private Integer credits;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AssignmentUpload> course_assignments;


    public Course() {
    }

    public Course(Long courseId, String course_title, String course_code, Integer credits) {
        this.courseId = courseId;
        this.course_title = course_title;
        this.course_code = course_code;
        this.credits = credits;
    }

    public Course(String course_title, String course_code, Integer credits) {
        this.course_title = course_title;
        this.course_code = course_code;
        this.credits = credits;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourse_title() {
        return course_title;
    }

    public void setCourse_title(String course_title) {
        this.course_title = course_title;
    }

    public String getCourse_code() {
        return course_code;
    }

    public void setCourse_code(String course_code) {
        this.course_code = course_code;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public List<AssignmentUpload> getCourse_assignments() {
        return course_assignments;
    }
}

