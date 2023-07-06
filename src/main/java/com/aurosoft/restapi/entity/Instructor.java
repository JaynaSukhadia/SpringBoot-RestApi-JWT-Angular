package com.aurosoft.restapi.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name="instructors")
public class Instructor {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name="instructor_id", nullable = false)
    private Long instructorId;


    @Column(name="fname", nullable = false, length = 45)
    private  String fname;

    @Column(name="lname", nullable = false, length = 45)
    private  String lname;

    @Column(name="summary", nullable = false, length = 64)
    private  String summary;

    @OneToMany(mappedBy = "instructor", fetch = FetchType.LAZY)
    private Set<Course> courses = new HashSet<>();

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id" , referencedColumnName = "user_id",nullable = false)
    private  User user;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instructor that = (Instructor) o;
        return instructorId.equals(that.instructorId) && Objects.equals(fname, that.fname) && Objects.equals(lname, that.lname) && Objects.equals(summary, that.summary) && Objects.equals(courses, that.courses) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(instructorId, fname, lname, summary, courses, user);
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "instructorId=" + instructorId +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", summary='" + summary + '\'' +
                '}';
    }

    public Long getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(Long instructorId) {
        this.instructorId = instructorId;
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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
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

    public Instructor() {
    }

    public Instructor(String fname, String lname, String summary, User user) {
        this.fname = fname;
        this.lname = lname;
        this.summary = summary;
        this.user = user;
    }
}
