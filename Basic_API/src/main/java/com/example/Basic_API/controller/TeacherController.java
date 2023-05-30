package com.example.Basic_API.controller;

import com.example.Basic_API.dto.TeacherRequestDTO;
import com.example.Basic_API.dto.TeacherResponseDTO;
import com.example.Basic_API.response.ResponseObject;
import com.example.Basic_API.response.ResponseObjects;
import com.example.Basic_API.service.ITeacherService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1/teacher")
@AllArgsConstructor
public class TeacherController {

    private final ITeacherService teacherService;

    @GetMapping()
    public ResponseEntity<?> getAllTeachers() {

        List<TeacherResponseDTO> data = teacherService.findAll();
        return ResponseEntity.ok(
                new ResponseObjects("ok", "lay du lieu thanh cong", Collections.singleton(data))
        );


    }

    @PostMapping()
    public ResponseEntity<?> addTeacher(@RequestBody TeacherRequestDTO teacherRequestDTO) {
        try {
            teacherService.save(teacherRequestDTO);
            return ResponseEntity.ok(
                    new ResponseObject("ok", "them du lieu thanh cong", "")
            );
        } catch (Exception e) {
            return ResponseEntity.ok(
                    new ResponseObject("failed", e.getMessage(), "")
            );
        }
    }
}
