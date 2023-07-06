package com.aurosoft.restapi.mapper;


import com.aurosoft.restapi.dto.CourseDTO;
import com.aurosoft.restapi.entity.Course;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class CourseMapper {

private InstructorMapper instructorMapper;

    public CourseMapper(InstructorMapper instructorMapper) {
        this.instructorMapper = instructorMapper;
    }

    public CourseDTO fromCourse(Course course)
    {
        CourseDTO courseDTO= new CourseDTO();
        BeanUtils.copyProperties(course, courseDTO);
        courseDTO.setInstructor(instructorMapper.fromInstructor(course.getInstructor()));
        return courseDTO;
    }

    public Course fromCourse(CourseDTO courseDTO)
    {
        Course course= new Course();
        BeanUtils.copyProperties(courseDTO, course);

        return course;
    }
}

