package com.aurosoft.restapi.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name="students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name="student_id", nullable = false)
    private  Long studentId;

    @Column(name="fname", nullable = false, length = 45)
    private  String fname;

    @Column(name="lname", nullable = false, length = 45)
    private  String lname;

    @Column(name="level", nullable = false, length = 64)
    private String level;


    @ManyToMany(mappedBy = "students" , fetch = FetchType.LAZY)
    private Set<Course> courses =new HashSet<>();

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name="user_id", referencedColumnName = "user_id",nullable = false)
    private  User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return studentId.equals(student.studentId) && Objects.equals(fname, student.fname) && Objects.equals(lname, student.lname) && Objects.equals(level, student.level) && Objects.equals(courses, student.courses) && Objects.equals(user, student.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, fname, lname, level, courses, user);
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", level='" + level + '\'' +
                '}';
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Student() {
    }

    public Student(String fname, String lname, String level, User user) {
        this.fname = fname;
        this.lname = lname;
        this.level = level;
        this.user = user;
    }
}
