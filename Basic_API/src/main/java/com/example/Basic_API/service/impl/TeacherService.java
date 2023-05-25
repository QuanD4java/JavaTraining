package com.example.Basic_API.service.impl;


import com.example.Basic_API.dto.TeacherRequestDTO;
import com.example.Basic_API.dto.TeacherResponseDTO;
import com.example.Basic_API.entiry.Teacher;
import com.example.Basic_API.repository.TeacherRepository;
import com.example.Basic_API.service.ITeacherService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TeacherService implements ITeacherService {
    private final ModelMapper modelMapper;
    private final TeacherRepository teacherRepository;

    @Override
    public List<TeacherResponseDTO> findAll() {
        if(modelMapper.getTypeMap(Teacher.class,TeacherResponseDTO.class)==null){
            TypeMap<Teacher, TeacherResponseDTO> typeMap=modelMapper.createTypeMap(Teacher.class,TeacherResponseDTO.class);
            typeMap.addMapping(Teacher::getAge,TeacherResponseDTO::setAge);
        }
        List<Teacher> entities=teacherRepository.findAll();
        return entities.stream().map(Teacher->modelMapper.map(Teacher,TeacherResponseDTO.class)).toList();
    }

    @Override
    public void save(TeacherRequestDTO teacherDTO) {
        Teacher entity=modelMapper.map(teacherDTO,Teacher.class);
        teacherRepository.save(entity);
    }
}
