package com.aurosoft.restapi.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;



@Entity
@Table(name="courses")

public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name="course_id", nullable = false)
    private Long courseId;

    @Column(name= "name",nullable = false, length = 45)
    private  String name;

    @Column(name = "duration",nullable = false,length = 45)
    private  String duration;

    @Column(name="description", nullable = false, length = 45)
    private  String description;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id",referencedColumnName = "instructor_id", nullable = false)
    private Instructor instructor;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="enrolled_in",
    joinColumns = {@JoinColumn(name="course_id")},
    inverseJoinColumns = {@JoinColumn(name="student_id")})
    private Set<Student> students = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return courseId.equals(course.courseId) && Objects.equals(name, course.name) && Objects.equals(duration, course.duration) && Objects.equals(description, course.description) && Objects.equals(instructor, course.instructor) && Objects.equals(students, course.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, name, duration, description, instructor, students);
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", name='" + name + '\'' +
                ", duration='" + duration + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Course() {
    }

    public Course(Long courseId, String name, String duration, String description, Instructor instructor) {
        this.courseId = courseId;
        this.name = name;
        this.duration = duration;
        this.description = description;
        this.instructor = instructor;
    }
}
