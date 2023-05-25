package com.example.Basic_API.service.impl;

import com.example.Basic_API.dto.StudentRequestDTO;
import com.example.Basic_API.dto.StudentResponseDTO;
import com.example.Basic_API.entiry.Student;
import com.example.Basic_API.mapper.StudentMapper;
import com.example.Basic_API.repository.StudentRepository;
import com.example.Basic_API.service.IStudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class StudentService implements IStudentService {

    private final StudentRepository studentRepository;

//    private final ModelMapper modelMapper;
//    @Override
//    public List<StudentDTO> getAllData() {
//        List<Student> entities = studentRepository.findAll();
//        return entities.stream()
//                .map(Student ->modelMapper.map(Student,StudentDTO.class))
//                .toList();
//    }
//
//    @Override
//    public void addNewStudent(StudentDTO studentDTO) {
//        Student entity=modelMapper.map(studentDTO,Student.class);
//        studentRepository.save(entity);
//    }

    @Override
    public List<StudentResponseDTO> findAll() {
        List<Student> entities = studentRepository.findAll();
        return entities.stream()
                .map(student ->StudentMapper.STUDENT_MAPPER.getStudentDto(student))
                .toList();
    }

    @Override
    public void save(StudentRequestDTO studentDTO) {
        Student entity=StudentMapper.STUDENT_MAPPER.studentDTOToStudent(studentDTO);
        System.out.println(entity.getDiem());
        studentRepository.save(entity);
    }
}
