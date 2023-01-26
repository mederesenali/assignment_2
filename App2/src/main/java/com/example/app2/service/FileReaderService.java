package com.example.app2.service;

import com.example.app2.entity.FileState;
import com.example.app2.enums.TypeEnum;
import com.example.app2.model.JsonData;
import com.example.app2.payload.Data;
import com.example.app2.repository.FileStateRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileReaderService {
    public static final String FILE_WRITE_PATH = "App2/src/main/resources/";
    private final ObjectMapper objectMapper;
    private final FileStateRepository fileStateRepository;

    public void readData() throws IOException {
        for (TypeEnum type : TypeEnum.values()) {
            Path path = Paths.get("files/" + getFileName(type.getValue()));
            if (Files.exists(path)) {
                write(type, path);
            }
        }

    }

    private void write(TypeEnum type, Path path) throws IOException {
        List<Data> dataList = getDataFromApp1File(path);
        FileState fileState = getFileStateByType(type.getValue());
        int capacity = fileState.getCapacity();
        int lastReadIndex = fileState.getLastReadIndex();
        List<Data> temp = new ArrayList<>();
        for (int i = lastReadIndex; i < dataList.size(); i++) {
            capacity--;
            temp.add(dataList.get(i));
            if (capacity == 0) {
                String fileName = String.format("%s-%s.log",getFileName(type.getValue()),fileState.getActualFileNumber());
                Path newFilePath = Paths.get(FILE_WRITE_PATH + fileName);
                writeDataToApp2File(temp, newFilePath);
                fileState = incrementFileNumber(fileState);
                temp = new ArrayList<>();
                capacity = 100;
            }
        }
        if (!temp.isEmpty()) {
            String fileName = String.format("%s-%s.log",getFileName(type.getValue()),fileState.getActualFileNumber());
            Path newFilePath = Paths.get("App2/src/main/resources/"+fileName);
            if (Files.exists(newFilePath)) {
                String content = Files.readAllLines(newFilePath).get(0);
                JsonData jsonData = objectMapper.readValue(content, JsonData.class);
                List<Data> list = jsonData.getData();
                list.addAll(temp);
                jsonData.setData(list);
                jsonData.setRecordCount(jsonData.getRecordCount() + 1);
                String writingContent = objectMapper.writeValueAsString(jsonData);
                Files.writeString(newFilePath, writingContent);
                fileState.setCapacity(capacity);
                fileState.setLastReadIndex(dataList.size());
                incrementFileNumber(fileState);
            }else{
                writeDataToApp2File(temp, newFilePath);
                fileState.setLastReadIndex(dataList.size());
                fileState.setCapacity(capacity);
                fileState.setLastReadIndex(dataList.size());
                incrementFileNumber(fileState);
            }

        }
    }

    private List<Data> getDataFromApp1File(Path path) throws IOException {
        String content = Files.readAllLines(path).get(0);
        JsonData jsonData = objectMapper.readValue(content, JsonData.class);
        return jsonData.getData();
    }

    private FileState incrementFileNumber(FileState fileState) {
        Integer currentFileNumber = Integer.parseInt(fileState.getActualFileNumber());
        fileState.setActualFileNumber(String.format("%04d", currentFileNumber++));
        return   fileStateRepository.save(fileState);
    }

    public void writeDataToApp2File(List<Data> dataList, Path path) throws IOException {
        String str = objectMapper.writeValueAsString(new JsonData(1, dataList));
        Files.writeString(path, str);
    }

    private FileState getFileStateByType(String type) {
        return fileStateRepository.findByType(type);
    }

    public String getFileName(String fileType) {
        return String.format("%s-%s.log", fileType, LocalDate.now());
    }

}
