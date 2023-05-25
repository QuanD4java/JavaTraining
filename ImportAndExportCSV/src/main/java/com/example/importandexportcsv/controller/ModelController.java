package com.example.importandexportcsv.controller;

import com.example.importandexportcsv.service.ModelService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class ModelController {
    private final ModelService modelService;

    @PostMapping
    public void file(@RequestBody MultipartFile file){
        modelService.saveFromCSV(file);
    }
}
