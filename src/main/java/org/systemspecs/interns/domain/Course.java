package org.systemspecs.interns.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;



@Entity
@Getter
@Setter
//@EqualsAndHashCode//?
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

    public Course(Long courseId, String course_title, String course_code, Integer credits) {
        this.courseId = courseId;
        this.course_title = course_title;
        this.course_code = course_code;
        this.credits = credits;
    }

    public Long getCourseId() {
        return courseId;
    }

    public Course() {
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

    public Course(String course_title, String course_code, Integer credits) {
        this.course_title = course_title;
        this.course_code = course_code;
        this.credits = credits;
    }

    @Column(nullable = false)
    private Integer credits;



}
