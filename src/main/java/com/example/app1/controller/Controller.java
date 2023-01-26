package com.example.app1.controller;

import com.example.app1.payload.Data;
import com.example.app1.service.FileWriterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class Controller {
    private final FileWriterService fileWriterService;

    @PostMapping("/xml")
    public ResponseEntity<?> getXML(@RequestBody Data data) {
        fileWriterService.write(data);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
