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
public class Programme {
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
    private Long programmeId;
    private String programme_title;
    private int level;

    @ManyToMany
    @JoinTable(
            name = "course_program_mapping",
            joinColumns = @JoinColumn(
                    name="programme_id",
                    referencedColumnName = "programmeId"
            ),
            inverseJoinColumns = @JoinColumn(
                    name ="course_id",
                    referencedColumnName = "courseId"
            )
    )
    private List<Course> courses;

}
