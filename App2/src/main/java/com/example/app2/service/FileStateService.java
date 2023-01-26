package com.example.app2.service;

import com.example.app2.entity.FileState;
import com.example.app2.enums.TypeEnum;
import com.example.app2.repository.FileStateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileStateService {
    private final FileStateRepository fileStateRepository;

    public void initializeDB() {
        try {
            if (fileStateRepository.findAll().isEmpty()) {
                for (TypeEnum type : TypeEnum.values()) {
                    fileStateRepository.save(FileState
                            .builder()
                            .type(type.getValue())
                            .actualFileNumber("0001")
                            .capacity(100)
                            .lastReadIndex(0)
                            .build());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
