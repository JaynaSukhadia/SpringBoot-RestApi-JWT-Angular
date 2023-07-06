package com.aurosoft.restapi.dao;

import com.aurosoft.restapi.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.List;

public interface CourseDao extends JpaRepository<Course, Long> {

    Page<Course> findCourseByCourseNameContains(String keyword, Pageable pageable);


    @Query(value ="select * from courses as c where c.course_id in(select  e.course_id from enrolled_in as e where e.student_id=:studentId)",nativeQuery = true)
    Page<Course> getCoursesByStudentId(@Param("studentId") Long studentId, Pageable pageable);

    @Query(value ="select * from courses as c where c.course_id  not in(select  e.course_id from enrolled_in as e where e.student_id=:studentId)",nativeQuery = true)
    Page<Course> getNonEnrolledInCoursesByStudentId(@Param("studentId") Long studentId, Pageable pageable);

    @Query(value ="select c from Course as c where c.instructor.instructorId = :instructorId")
    Page<Course> getCoursesByInstructorId(@Param("instructorId") Long studentId, Pageable pageable);
}
