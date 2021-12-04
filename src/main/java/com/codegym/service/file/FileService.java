package com.codegym.service.file;

import com.codegym.model.entity.File;
import com.codegym.repository.IFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FileService implements IFileService {
    @Autowired
    private IFileRepository fileRepository;

    @Override
    public Iterable<File> findAll() {
        return fileRepository.findAll();
    }

    @Override
    public Optional<File> findById(Long id) {
        return fileRepository.findById(id);
    }

    @Override
    public File save(File file) {
        return fileRepository.save(file);
    }

    @Override
    public void remove(Long id) {
        fileRepository.deleteById(id);
    }

    @Override
    public Iterable<File> findFileByPostId(Long id) {
        return fileRepository.findFileByPostId(id);
    }
}
