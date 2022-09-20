package com.anerkenung.springboot.service;

import com.anerkenung.springboot.entity.UserEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;

public interface UserService {

    // Delete operation
    void deleteUserById(Long id);
    //upload operation
    // void upload(MultipartFile file) throws IOException;

    //upload the files(pdf and doc ...)

    public void init();

    public void saveFiledPdf(MultipartFile file, Long id);

    void deleteFile(String filename,Long id);

    public Resource load(String filename);

    public void deleteAll();

    public Stream<Path> loadAll();

    UserEntity findById(long id);

    UserEntity updateUser(UserEntity userEntity);
}
