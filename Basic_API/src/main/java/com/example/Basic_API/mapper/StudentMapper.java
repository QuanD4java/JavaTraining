package com.example.Basic_API.mapper;

import com.example.Basic_API.dto.StudentRequestDTO;
import com.example.Basic_API.dto.StudentResponseDTO;
import com.example.Basic_API.entiry.Student;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentMapper {
    StudentMapper STUDENT_MAPPER = Mappers.getMapper(StudentMapper.class);

    Student studentDTOToStudent(StudentRequestDTO studentDTO);

    StudentResponseDTO studentToStudentDTO(Student student);

    default StudentResponseDTO getStudentDto(Student student) {
        StudentResponseDTO studentResponseDTO = studentToStudentDTO(student);
        String hocluc = "";
        if (student.getDiem() > 7) hocluc = "gioi";
        else if (student.getDiem() > 5) hocluc = "kha";
        else if (student.getDiem() < 0) hocluc = "chua nhap diem";
        else hocluc = "yeu";
        studentResponseDTO.setHocLuc(hocluc);
        return studentResponseDTO;
    }
}
