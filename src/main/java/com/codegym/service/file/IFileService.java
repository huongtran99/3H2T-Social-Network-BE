package com.codegym.service.file;

import com.codegym.model.entity.File;
import com.codegym.service.IGeneralService;

public interface IFileService extends IGeneralService<File> {
    Iterable<File> findFileByPostId(Long id);
}
