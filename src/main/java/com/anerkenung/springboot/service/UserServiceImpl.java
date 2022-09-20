package com.anerkenung.springboot.service;

import com.anerkenung.springboot.entity.CoursesEntity;
import com.anerkenung.springboot.entity.UserEntity;
import com.anerkenung.springboot.hleper.Archive;
import com.anerkenung.springboot.hleper.ExcelHelper;
import com.anerkenung.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ExcelHelper excelHelper;


    public void saveUser(MultipartFile file, String country, String university) {
        try {
            UserEntity entities = excelHelper.convertExcelToListOfUser(file.getInputStream(), country, university);
            userRepository.save(entities);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    public void saveUserArchive(MultipartFile file, String country, String university) {
        try {
            UserEntity entities = Archive.convertExcelToListOfUserArchive(file.getInputStream(), country, university);
            userRepository.save(entities);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }


    public UserEntity updateUser(UserEntity userEntity) {

        return userRepository.save(userEntity);
    }

    public List<UserEntity> getAllEntities() {

        return userRepository.findAll();

    }


    @Transactional(readOnly = true)
    public Page<UserEntity> findAll(Pageable pageable) {
        //log.debug("Request to get all OrganizationLevels");
        return userRepository.findAllByStatusIsNot("hideCompleted", pageable);
    }


    //Delete Operation

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);

    }

    //save files link in db and dir

   // private final Path root = Paths.get("C:\\Users\\zarei\\Desktop\\uploads");
   @Value("${root}")
    private Path root;

    @Override
    public void init() {
        try {
            Files.createDirectory(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Override
    public void saveFiledPdf(MultipartFile file, Long id) {
        try {
            Files.copy(file.getInputStream(), this.root.resolve(id + "_" + file.getOriginalFilename()));
            UserEntity userEntity = userRepository.findById(id).get();
            userEntity.getFiles().add(userEntity.getId() + "_" + file.getOriginalFilename());


            userRepository.save(userEntity);

        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }


    @Override
    public Resource load(String filename) {
        try {
            Path filePath = root.resolve(filename);
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!" + filename);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage() + filename);
        }
    }


    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files!");
        }
    }

    @Override
    public UserEntity findById(long id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        return userEntity.get();
    }

    //delete a file
    @Override
    public void deleteFile(String filename, Long id) {
        Path filePath = root.resolve(filename);

        // FileSystemUtils.deleteRecursively(filePath);
        File f = new File(filePath.toString());
        if (f.delete()) {
            Optional<UserEntity> userEntity = userRepository.findById(id);
            userEntity.get().getFiles().remove(filename);
            userRepository.save(userEntity.get());

        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }


}




