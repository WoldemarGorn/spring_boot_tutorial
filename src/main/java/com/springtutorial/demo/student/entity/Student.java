package com.springtutorial.demo.student.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
public class Student {

    @Id
    @SequenceGenerator(name = "student_sequece", sequenceName = "student_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "srudent_sequence")
    private Long id;
    private String name;
    private String email;
    private LocalDate birtday;
    @Transient
    private int age;

    public Student(){

    }

    public Student(Long id, String name, String email, LocalDate birthday) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birtday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirtday() {
        return birtday;
    }

    public void setBirtday(LocalDate birtday) {
        this.birtday = birtday;
    }

    public int getAge() {
        return Period.between(this.birtday, LocalDate.now()).getYears();
    }

    public void setAge(int age) {
        this.age = age;
    }
}
