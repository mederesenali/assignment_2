package com.example.app2.repository;

import com.example.app2.entity.FileState;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileStateRepository extends JpaRepository<FileState,Long> {
    FileState findByType(String type);
}
