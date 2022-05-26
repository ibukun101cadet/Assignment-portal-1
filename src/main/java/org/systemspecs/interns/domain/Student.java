package org.systemspecs.interns.domain;

import lombok.EqualsAndHashCode;
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
    private String fullName;
    private int level;

    @OneToOne
            @JoinColumn(
            name ="programme_id",
            referencedColumnName = "programmeId"
    )
    private Programme programme;





}