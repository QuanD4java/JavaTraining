package com.example.Basic_API.service;

import com.example.Basic_API.dto.StudentRequestDTO;
import com.example.Basic_API.dto.StudentResponseDTO;

import java.util.List;

public interface IStudentService {
    public List<StudentResponseDTO> findAll();

    public void save(StudentRequestDTO studentDTO);

}
