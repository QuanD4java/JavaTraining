package com.example.importandexportcsv.service;

import com.example.importandexportcsv.entity.Model;
import com.example.importandexportcsv.repository.ModelRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class ModelService {
    private final ModelRepository modelRepository;

    public void saveFromCSV(MultipartFile file) {
        try {
            if (CSVService.hasCSVFormat(file)) {
                List<Model> list = CSVService.readCSV(file.getInputStream());
                modelRepository.saveAll(list);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Model> readFromDB(int page) {
        try {
            return modelRepository.findAll(pageable(page, 50)).getContent();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//    public List<Model> getAllData(){
//        List<Model> data=modelRepository.findAll();
//        return data;
//    }

    @Transactional(readOnly = true)
    public void printCSV() {
        Stream<Model> data = modelRepository.streamData();
        CSVService.writeCSV(data);
    }

    private Pageable pageable(int page, int size) {
        return PageRequest.of(page, size);
    }
}
