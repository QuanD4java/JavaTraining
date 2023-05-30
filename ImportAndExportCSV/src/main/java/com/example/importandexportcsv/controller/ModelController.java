package com.example.importandexportcsv.controller;

import com.example.importandexportcsv.entity.Model;
import com.example.importandexportcsv.service.ModelService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/model")
@AllArgsConstructor
public class ModelController {
    private final ModelService modelService;

    @PostMapping
    public void file(@RequestBody MultipartFile file) {
        modelService.saveFromCSV(file);
    }

    @GetMapping("/{page}")
    public ResponseEntity<?> getData(@PathVariable String page) {
        List<Model> data = modelService.readFromDB(Integer.parseInt(page));
        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @PostMapping("/print")
    public void printCSV() {
        modelService.printCSV();
    }
}
