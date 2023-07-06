package com.aurosoft.restapi.impl;

import com.aurosoft.restapi.dao.CourseDao;
import com.aurosoft.restapi.dao.InstructorDao;
import com.aurosoft.restapi.dto.CourseDTO;
import com.aurosoft.restapi.entity.Course;
import com.aurosoft.restapi.mapper.CourseMapper;
import com.aurosoft.restapi.service.CourseService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;

public class CourseServiceImpl implements CourseService {


    private CourseDao courseDao;

    public CourseServiceImpl(CourseDao courseDao, CourseMapper courseMapper) {
        this.courseDao = courseDao;
        this.courseMapper = courseMapper;
    }

    private CourseMapper courseMapper;
    private InstructorDao instructorDao;


    @Override
    public Course loadCourseById(Long courseId) {
        return courseDao.findById(courseId).orElseThrow(()->new EntityNotFoundException("Course with Id" +courseId+"Nor Found"));
    }

    @Override
    public CourseDTO createCourse(CourseDTO courseDTO) {
        return null;
    }

    @Override
    public CourseDTO updateCourse(CourseDTO courseDTO) {
        return null;
    }

    @Override
    public Page<CourseDTO> findCourseByCourseName(String keyword, int page, int size) {
        return null;
    }

    @Override
    public void assignStudentToCourse(Long courseId, Long studentId) {

    }

    @Override
    public Page<CourseDTO> fetchCoursesForStudent(Long studentId, int page, int size) {
        return null;
    }

    @Override
    public Page<CourseDTO> fetchNonEnrolledInCoursesForStudent(Long studentId, int page, int size) {
        return null;
    }

    @Override
    public void removeCourse(Long courseId) {

    }

    @Override
    public Page<CourseDTO> fetchCoursesForInstructor(Long instructorId, int page, int size) {
        return null;
    }
}
