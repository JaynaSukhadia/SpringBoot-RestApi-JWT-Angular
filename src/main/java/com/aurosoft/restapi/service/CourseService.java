package com.aurosoft.restapi.service;

import com.aurosoft.restapi.dto.CourseDTO;
import com.aurosoft.restapi.entity.Course;
import org.springframework.data.domain.Page;

public interface CourseService {

    Course loadCourseById(Long courseId);

    CourseDTO createCourse(CourseDTO courseDTO);

    CourseDTO updateCourse(CourseDTO courseDTO);

    Page<CourseDTO> findCourseByCourseName(String keyword, int page, int size);

    void assignStudentToCourse(Long courseId, Long studentId);

    Page<CourseDTO> fetchCoursesForStudent(Long studentId , int page, int size);

    Page<CourseDTO> fetchNonEnrolledInCoursesForStudent(Long studentId , int page, int size);

    void removeCourse(Long courseId);

    Page<CourseDTO> fetchCoursesForInstructor(Long instructorId , int page, int size);

}
