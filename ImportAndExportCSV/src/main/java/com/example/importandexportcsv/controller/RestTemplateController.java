package com.example.importandexportcsv.controller;

import com.example.importandexportcsv.service.RestTemplateService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/3rd-api")
@AllArgsConstructor
public class RestTemplateController {
    private final RestTemplateService restTemplateService;
    @PostMapping("/webclient")
    public void writeDataFromApi(@RequestParam String url) throws IOException {
        restTemplateService.writeDataWebClient(url);
    }

    @PostMapping("/all-data")
    public void writeDataFromApi1(@RequestParam String url) throws IOException {
        restTemplateService.writeAllData(url);
    }
    @GetMapping
    public ResponseEntity<?> getData(@RequestParam String url){
       return restTemplateService.getData(url);
    }
}
