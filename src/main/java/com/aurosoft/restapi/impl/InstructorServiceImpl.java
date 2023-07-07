package com.aurosoft.restapi.impl;

import com.aurosoft.restapi.dao.InstructorDao;
import com.aurosoft.restapi.dto.InstructorDTO;
import com.aurosoft.restapi.entity.Course;
import com.aurosoft.restapi.entity.Instructor;
import com.aurosoft.restapi.entity.User;
import com.aurosoft.restapi.mapper.InstructorMapper;
import com.aurosoft.restapi.service.CourseService;
import com.aurosoft.restapi.service.InstructorService;
import com.aurosoft.restapi.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstructorServiceImpl implements InstructorService {


    private  InstructorDao instructorDao;
    private InstructorMapper instructorMapper;
    private UserService userService;
    private CourseService courseService;

    public InstructorServiceImpl(InstructorDao instructorDao, InstructorMapper instructorMapper) {
        this.instructorDao = instructorDao;
        this.instructorMapper = instructorMapper;
    }

    @Override
    public Instructor loadInstructorById(Long instructorId) {
        return instructorDao.findById(instructorId).orElseThrow(()-> new EntityNotFoundException("Instructor with Id" +instructorId+"Not Found" ));
    }

    @Override
    public Page<InstructorDTO> findInstructorByName(String name, int page, int size) {

        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Instructor> instructorsPage = instructorDao.findInstructorsByName(name, pageRequest);
        return new PageImpl<>(instructorsPage.getContent().stream().map(instructor ->instructorMapper.fromInstructor(instructor)).collect(Collectors.toList()),pageRequest,instructorsPage.getTotalElements());
    }

    @Override
    public InstructorDTO loadInstructorByEmail(String email) {

        return instructorMapper.fromInstructor((Instructor) instructorDao.findInstructorsByEmail(email));
    }

    @Override
    public InstructorDTO createInstructor(InstructorDTO instructorDTO) {
        User user = userService.createUser(instructorDTO.getUser().getEmail(), instructorDTO.getUser().getPassword());
        userService.assignRoleToUser(user.getEmail(),"Instructor");
        Instructor instructor = instructorMapper.fromInstructorDTO(instructorDTO);
        instructor.setUser(user);
        Instructor savedInstructor = instructorDao.save(instructor);
        return  instructorMapper.fromInstructor(savedInstructor);


    }

    @Override
    public InstructorDTO updateInstructor(InstructorDTO instructorDTO) {
        Instructor loadedInstructor = loadInstructorById(instructorDTO.getInstructorId());
        Instructor instructor = instructorMapper.fromInstructorDTO(instructorDTO);
        instructor.setUser(loadedInstructor.getUser());
        instructor.setCourses(loadedInstructor.getCourses());
        Instructor updatedInstructor = instructorDao.save(instructor);
        return  instructorMapper.fromInstructor(updatedInstructor);


    }

    @Override
    public List<InstructorDTO> fetchInstructors() {
        return instructorDao.findAll().stream().map(instructor -> instructorMapper.fromInstructor(instructor)).collect(Collectors.toList());
    }

    @Override
    public void removeInstructor(Long instructorId) {
Instructor instructor = loadInstructorById(instructorId);
for (Course course : instructor.getCourses())
{
    courseService.removeCourse(course.getCourseId());
}
instructorDao.deleteById(instructorId);
    }
}
