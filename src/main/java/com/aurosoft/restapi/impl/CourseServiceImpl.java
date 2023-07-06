package com.aurosoft.restapi.impl;

import com.aurosoft.restapi.dao.CourseDao;
import com.aurosoft.restapi.dao.InstructorDao;
import com.aurosoft.restapi.dao.StudentDao;
import com.aurosoft.restapi.dto.CourseDTO;
import com.aurosoft.restapi.entity.Course;
import com.aurosoft.restapi.entity.Instructor;
import com.aurosoft.restapi.entity.Student;
import com.aurosoft.restapi.mapper.CourseMapper;
import com.aurosoft.restapi.service.CourseService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.awt.print.Pageable;
import java.util.stream.Collectors;

public class CourseServiceImpl implements CourseService {


    private CourseDao courseDao;
    private CourseMapper courseMapper;
    private InstructorDao instructorDao;
    private StudentDao studentDao;

    public CourseServiceImpl(CourseDao courseDao, CourseMapper courseMapper, InstructorDao instructorDao) {
        this.courseDao = courseDao;
        this.courseMapper = courseMapper;
        this.instructorDao = instructorDao;
    }

    @Override
    public Course loadCourseById(Long courseId) {
        return courseDao.findById(courseId).orElseThrow(()->new EntityNotFoundException("Course with Id" +courseId+"Nor Found"));
    }

    @Override
    public CourseDTO createCourse(CourseDTO courseDTO) {
        Course course = courseMapper.fromCourseDTO(courseDTO);
        Instructor instructor = instructorDao.findById(courseDTO.getInstructor().getInstructorId()).orElseThrow(()-> new EntityNotFoundException("Instructor with Id" +courseDTO.getInstructor().getInstructorId()+"Not Found" ));
        course.setInstructor(instructor);
       Course savedCourse =  courseDao.save(course);
        return courseMapper.fromCourse(savedCourse);
    }

    @Override
    public CourseDTO updateCourse(CourseDTO courseDTO) {
       Course loadedCourse = loadCourseById(courseDTO.getCourseId());
       Instructor instructor = instructorDao.findById(courseDTO.getInstructor().getInstructorId()).orElseThrow(()-> new EntityNotFoundException("Instructor with Id" +courseDTO.getInstructor().getInstructorId()+"Not Found" ));
       Course course = courseMapper.fromCourseDTO(courseDTO);
       course.setInstructor(instructor);
       course.setStudents(loadedCourse.getStudents());
       Course updatedCourse = courseDao.save(course);
       return courseMapper.fromCourse(updatedCourse);
    }

    @Override
    public Page<CourseDTO> findCourseByCourseName(String keyword, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
       Page<Course> coursesPage = courseDao.findCourseByCourseNameContains(keyword, (Pageable) pageRequest);
        return  new PageImpl<>(coursesPage.getContent().stream().map(course -> courseMapper.fromCourse(course)).collect(Collectors.toList()),pageRequest,coursesPage.getTotalElements());
    }

    @Override
    public void assignStudentToCourse(Long courseId, Long studentId) {
    Student student = studentDao.findById(studentId).orElseThrow(()-> new EntityNotFoundException("Student with Id" +studentId+"Not Found" ));
    Course course = loadCourseById(courseId);
    course.assignStudentToCourse(student);
    }

    @Override
    public Page<CourseDTO> fetchCoursesForStudent(Long studentId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
      Page<Course> studentCoursesPage=  courseDao.getCoursesByStudentId(studentId, (Pageable) pageRequest);
        return new PageImpl<>(studentCoursesPage.getContent().stream().map(course -> courseMapper.fromCourse(course)).collect(Collectors.toList()), pageRequest, studentCoursesPage.getTotalElements());
    }

    @Override
    public Page<CourseDTO> fetchNonEnrolledInCoursesForStudent(Long studentId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Course> nonEnrolledInCoursesPage =  courseDao.getNonEnrolledInCoursesByStudentId(studentId,(Pageable) pageRequest);
        return new PageImpl<>(nonEnrolledInCoursesPage.getContent().stream().map(course -> courseMapper.fromCourse(course)).collect(Collectors.toList()), pageRequest, nonEnrolledInCoursesPage.getTotalElements());
    }

    @Override
    public void removeCourse(Long courseId) {
courseDao.deleteById(courseId);
    }

    @Override
    public Page<CourseDTO> fetchCoursesForInstructor(Long instructorId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        Page<Course> instructorCoursesPage =  courseDao.getCoursesByInstructorId(instructorId,(Pageable) pageRequest);
        return new PageImpl<>(instructorCoursesPage.getContent().stream().map(course -> courseMapper.fromCourse(course)).collect(Collectors.toList()), pageRequest, instructorCoursesPage.getTotalElements());
    }

}
