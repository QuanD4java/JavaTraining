package com.example.Basic_API.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collection;

@Data
@AllArgsConstructor
public class ResponseObjects {
    private String status;
    private String message;
    private Collection<Object> data;
}
