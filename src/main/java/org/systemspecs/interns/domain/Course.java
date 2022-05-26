package org.systemspecs.interns.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
//@EqualsAndHashCode//?
@NoArgsConstructor
@Entity
public class Course {
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
    private Long courseId;
    private String course_title;
    private String course_code;
    private Integer credits;

    @ManyToMany
    @JoinTable(
            name = "student_course_mapping",
            joinColumns = @JoinColumn(
                    name="course_id",
                    referencedColumnName = "courseId"
                    ),
            inverseJoinColumns = @JoinColumn(
                    name ="student_id",
                    referencedColumnName = "studentId"
                    )
    )
    private List<Student> students;



}
