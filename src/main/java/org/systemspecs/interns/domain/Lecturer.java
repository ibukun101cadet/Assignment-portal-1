package org.systemspecs.interns.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Lecturer {


    @SequenceGenerator(
            name = "lecturer_sequence",
            sequenceName = "lecturer_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "lecturer_sequence"
    )
    private Long lecturerId;

    @Column(nullable = false)
    private String fullName;


    public List<Course> getCourses_Taught() {
        return courses_Taught;
    }

    @ManyToMany
    @JoinTable(
            name = "lecturer_course_mapping",
            joinColumns = @JoinColumn(nullable = false,
                    name="lecturer_id",
                    referencedColumnName = "lecturerId"
            ),
            inverseJoinColumns = @JoinColumn(nullable = false,
                    name ="course_id",
                    referencedColumnName = "courseId"
            )
    )
    private List<Course> courses_Taught;





}