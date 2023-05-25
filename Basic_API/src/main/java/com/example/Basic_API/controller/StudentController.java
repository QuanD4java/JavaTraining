package com.example.Basic_API.controller;

import com.example.Basic_API.dto.StudentRequestDTO;
import com.example.Basic_API.dto.StudentResponseDTO;
import com.example.Basic_API.response.ResponseObject;
import com.example.Basic_API.response.ResponseObjects;
import com.example.Basic_API.service.IStudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/v1/student")
@AllArgsConstructor
public class StudentController {

    private final IStudentService studentService;

    @GetMapping()
    public ResponseEntity<?> getAllStudents() {
        try {
            List<StudentResponseDTO> data = studentService.findAll();
            return ResponseEntity.ok(
                    new ResponseObjects("ok", "lay du lieu thanh cong", Collections.singleton(data))
            );
        } catch (Exception e) {
            return ResponseEntity.ok(
                    new ResponseObjects("failed", e.getMessage(), new ArrayList<>())
            );
        }
    }

    @PostMapping()
    public ResponseEntity<?> addStudent(@RequestBody StudentRequestDTO studentDTO) {
        try {
            System.out.println("sds " +new StudentRequestDTO().getDiem() + " "+studentDTO.getDiem());
            studentService.save(studentDTO);
            return ResponseEntity.ok(
                    new ResponseObject("ok", "them thanh cong", "")
            );
        } catch (Exception e) {
            return ResponseEntity.ok(
                    new ResponseObject("failed", e.getMessage(), "")
            );
        }
    }


}
