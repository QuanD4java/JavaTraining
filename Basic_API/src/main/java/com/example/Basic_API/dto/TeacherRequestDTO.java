package com.example.Basic_API.dto;

import lombok.Data;

import java.util.Date;

@Data
public class TeacherRequestDTO {
    private String name;
    private Date birthday;
}
