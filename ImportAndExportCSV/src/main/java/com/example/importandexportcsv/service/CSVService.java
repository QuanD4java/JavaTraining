package com.example.importandexportcsv.service;

import com.example.importandexportcsv.entity.Model;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class CSVService {
    private static final String CSV_FORMAT = "text/csv";

    public static boolean hasCSVFormat(MultipartFile file) {
        return file.getContentType().equals(CSV_FORMAT);
    }

    public static List<Model> listModel(InputStream is) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"))) {
            CSVParser csvParser = new CSVParser(reader,
                    CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
            List<Model> list = new ArrayList<>();
            List<CSVRecord> csvRecords = csvParser.getRecords();
            for (CSVRecord record : csvRecords) {
                Model model = new Model(
                        record.get("Series_reference"),
                        record.get("Period"),
                        record.get("Data_value").isBlank()?null:Double.parseDouble(record.get("Data_value")),
                        record.get("Suppressed"),
                        record.get("STATUS"),
                        record.get("UNITS"),
                        record.get("Magnitude").isBlank()?null:Double.parseDouble(record.get("Magnitude")),
                        record.get("Subject"),
                        record.get("Group"),
                        record.get("Series_title_1"),
                        record.get("Series_title_2"),
                        record.get("Series_title_3"),
                        record.get("Series_title_4"),
                        record.get("Series_title_5")
                );
                list.add(model);
            }
            return list;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
