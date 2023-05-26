package com.example.importandexportcsv.service;

import lombok.AllArgsConstructor;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
@AllArgsConstructor
public class RestTemplateService {
    private final RestTemplate restTemplate;
    private final WebClient webClient;

    public void writeDataWebClient(String url) throws IOException {
        BufferedWriter writer= Files.newBufferedWriter(Paths.get("webclient.csv"));
        Flux<org.springframework.core.io.buffer.DataBuffer> data=webClient.get()
                .uri(url)
                .retrieve()
                .bodyToFlux(DataBuffer.class);
        data.subscribe(dataBuffer -> {
            byte[] bytes=new byte[dataBuffer.readableByteCount()];
            dataBuffer.read(bytes);
            DataBufferUtils.release(dataBuffer);
            try {
                String line= new String(bytes,"UTF-8");
                writer.write(line);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void writeAllData(String url) throws IOException {
        ResponseEntity<String> response = restTemplate.getForEntity (url, String.class);
        BufferedWriter writer= Files.newBufferedWriter(Paths.get("alldata.csv"));
        writer.write(response.getBody());
    }
    public ResponseEntity<?> getData(String url){
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response;
    }
}
