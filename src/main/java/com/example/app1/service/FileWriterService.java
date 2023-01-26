package com.example.app1.service;

import com.example.app1.Model.JsonData;
import com.example.app1.payload.Data;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileWriterService {
    private final ObjectMapper objectMapper;

    public void write(Data data) {
        log.info(String.format("data: %s", data));
        try {
            Path path = Paths.get("files/" + getFileName(data.getType()));
            if (Files.exists(path)) {
                String content = Files.readAllLines(path).get(0);
                JsonData jsonData = objectMapper.readValue(content, JsonData.class);
                List<Data> dataList = jsonData.getData();
                dataList.add(data);
                jsonData.setData(dataList);
                jsonData.setRecordCount(jsonData.getRecordCount() + 1);
                String writingContent = objectMapper.writeValueAsString(jsonData);
                Files.writeString(path, writingContent);
            } else {
                List<Data> dataList = new ArrayList<>();
                dataList.add(data);
                String str = objectMapper.writeValueAsString(new JsonData(1, dataList));
                Files.writeString(path, str);
            }
        } catch (Exception e) {
            log.info("exception", e);
        }
    }

    public String getFileName(String fileType) {
        return String.format("%s-%s.log", fileType, LocalDate.now());
    }
}
