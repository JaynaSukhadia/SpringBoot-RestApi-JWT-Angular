package com.aurosoft.restapi.mapper;

import com.aurosoft.restapi.dto.InstructorDTO;
import com.aurosoft.restapi.entity.Instructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class InstructorMapper {

    public InstructorDTO fromInstructor(Instructor instructor)
    {
        InstructorDTO instructorDTO= new InstructorDTO();
        BeanUtils.copyProperties(instructor, instructorDTO);
        return instructorDTO;
    }

    public Instructor fromInstructorDTO(InstructorDTO instructorDTO)
    {
        Instructor instructor= new Instructor();
        BeanUtils.copyProperties(instructorDTO, instructor);
        return instructor;
    }
}
