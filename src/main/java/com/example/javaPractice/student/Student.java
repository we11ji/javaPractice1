package com.example.javaPractice.student;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@NoArgsConstructor
@Data
@Entity
@Table
public class Student {
    @Getter
    @Setter
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private LocalDate birthday;
    @Setter
    @Transient
    private Long age;
    @Getter
    @Setter
    private String email;

    public Student(String name,
                   LocalDate birthday,
                   String email){
        this.name = name;
        this.birthday = birthday;
        this.email = email;
    }

    public Integer getAge(){
        return Period.between(this.birthday, LocalDate.now()).getYears();
    }

}
