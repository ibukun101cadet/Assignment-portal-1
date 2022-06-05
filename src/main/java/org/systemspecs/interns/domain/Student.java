package org.systemspecs.interns.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {


    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long studentId;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private int level;

    @Column(nullable = false)
    private String matricNo;

    @ManyToMany
    @JoinTable(
            name = "student_course_mapping",
            joinColumns = @JoinColumn(nullable = false,
                    name="student_id",
                    referencedColumnName = "studentId"
            ),
            inverseJoinColumns = @JoinColumn(nullable = false,
                    name ="course_id",
                    referencedColumnName = "courseId"
            )
    )
    private List<Course> courses;

    public List<Course> getCourses() {
        return courses;
    }

//    @OneToOne
//            @JoinColumn(nullable = false,
//            name ="programme_title",
//            referencedColumnName = "programme_title"
//    )
//    private Programme programme;




//    @OneToOne
//    @JoinColumn(name = "student_id",
//            referencedColumnName = "studentId")
//    private AssignmentSubmission assignmentSubmission;




}