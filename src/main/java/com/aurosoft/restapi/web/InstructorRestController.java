package com.aurosoft.restapi.web;


import com.aurosoft.restapi.dto.CourseDTO;
import com.aurosoft.restapi.dto.InstructorDTO;
import com.aurosoft.restapi.entity.User;
import com.aurosoft.restapi.service.CourseService;
import com.aurosoft.restapi.service.InstructorService;
import com.aurosoft.restapi.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instructors")
public class InstructorRestController {

    private InstructorService instructorService;

    private UserService userService;

    private CourseService courseService;

    public InstructorRestController(InstructorService instructorService, UserService userService, CourseService courseService) {
        this.instructorService = instructorService;
        this.userService = userService;
        this.courseService = courseService;
    }

    @GetMapping
    public Page<InstructorDTO> searchInstructors(@RequestParam (name = "keyword", defaultValue = "")String keyword,
                                                 @RequestParam(name="page", defaultValue = "0")int page,
                                                 @RequestParam(name = "size", defaultValue = "5")int size)
    {
        return instructorService.findInstructorByName(keyword, page, size);
    }


    @GetMapping("/all")
    public List<InstructorDTO> findAllInstructors()
    {
      return instructorService.fetchInstructors();
    }

    @DeleteMapping("/{instructorId}")
    public  void deleteInstructor(@PathVariable Long instructorId)
    {
        instructorService.removeInstructor(instructorId);
    }

    @PostMapping
    public InstructorDTO saveInstructor(@RequestBody InstructorDTO instructorDTO)
    {
        User user =userService.loadUserByEmail(instructorDTO.getUser().getEmail());
        if(user != null) throw  new RuntimeException("Email Already Exist");
        return instructorService.createInstructor(instructorDTO);

    }

    @PostMapping("/{instructorId}")
    public  InstructorDTO updateInstructor(@RequestBody InstructorDTO instructorDTO, @PathVariable Long instructorId)
    {
        instructorDTO.setInstructorId(instructorId);
       return instructorService.updateInstructor(instructorDTO);

    }

    @GetMapping("/{instructorId}/courses")
    public Page<CourseDTO> coursesByInstructorId(@PathVariable Long instructorId,
                                                 @RequestParam(name = "page", defaultValue = "0")int page,
                                                 @RequestParam(name = "size", defaultValue = "5")int size)
    {
        return courseService.fetchCoursesForInstructor(instructorId,page,size);
    }

    @GetMapping("/find")
    public InstructorDTO loadInstructorByEmail (@RequestParam(name = "email", defaultValue = "")String email)
    {
       return instructorService.loadInstructorByEmail(email);
    }
}
