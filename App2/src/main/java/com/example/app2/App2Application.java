package com.example.app2;

import com.example.app2.service.FileReaderService;
import com.example.app2.service.FileStateService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@SpringBootApplication
public class App2Application {
    @Autowired
    FileReaderService fileReaderService;
    @Autowired
    FileStateService fileStateService;

    public static void main(String[] args) {
        SpringApplication.run(App2Application.class, args);
    }

    @PostConstruct
    public void readData(){
        try {
            fileStateService.initializeDB();
            fileReaderService.readData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}