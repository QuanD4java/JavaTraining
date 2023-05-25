package com.example.importandexportcsv.service;

import com.example.importandexportcsv.entity.Model;
import com.example.importandexportcsv.repository.ModelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@AllArgsConstructor
public class ModelService {
    private final ModelRepository modelRepository;

    public void saveFromCSV(MultipartFile file) {
        try {
            List<Model> list = CSVService.listModel(file.getInputStream());
            modelRepository.saveAll(list);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
