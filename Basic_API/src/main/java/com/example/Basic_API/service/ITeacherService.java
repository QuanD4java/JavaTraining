package com.example.Basic_API.service;

import com.example.Basic_API.dto.TeacherRequestDTO;
import com.example.Basic_API.dto.TeacherResponseDTO;

import java.util.List;

public interface ITeacherService {
    public List<TeacherResponseDTO> findAll();
    public void save(TeacherRequestDTO teacherDTO);
}
