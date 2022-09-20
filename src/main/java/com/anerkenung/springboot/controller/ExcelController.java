package com.anerkenung.springboot.controller;

import com.anerkenung.springboot.config.PaginationUtils;
import com.anerkenung.springboot.entity.UserEntity;
import com.anerkenung.springboot.filesandsheets.FilesInfo;
import com.anerkenung.springboot.hleper.Archive;
import com.anerkenung.springboot.hleper.ExcelHelper;
import com.anerkenung.springboot.message.ResponseMessage;
import com.anerkenung.springboot.repository.UserRepository;
import com.anerkenung.springboot.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping(path = "/user")
public class ExcelController {


    @Autowired
    private UserServiceImpl userServiceimpl;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ExcelHelper excelHelper;


    @PostMapping("/add")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("country") String country, @RequestParam("university") String university) {
        if (country.equals("undefined") || country.equals("")) {
            country = null;
        }
        String message = "";
        System.out.println(country + "ooooooooooooooooooooooooooooooooooooooooooo");

        if (excelHelper.hasExcelFormat(file)) {
            if (country == null && excelHelper.hasExcelFormat(file)) {
                message = "Please Enter Country Name For File:" + " " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));

            } else if (country != null && excelHelper.hasExcelFormat(file)) {
                try {
                    userServiceimpl.saveUser(file, country, university);
                    message = "Uploaded The File Successfully: " + " " + file.getOriginalFilename();
                    return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
                } catch (Exception e) {
                    message = "Could not Upload The File: " + " " + file.getOriginalFilename() + "!";
                    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
                }
            }
        }
        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }


//        if (excelHelper.hasExcelFormat(file)) {
//            if (country != "undefined") {
//                try {
//                    userServiceimpl.saveUser(file, country, university);
//                    message = "Uploaded the file successfully: " + file.getOriginalFilename();
//                    return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
//                } catch (Exception e) {
//                    message = "Could not upload the file: " + file.getOriginalFilename() + "!";
//                    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
//                }
//            }
//            message = "Please upload an excel file!";
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
//        }


    @PostMapping("/add-archive")
    public ResponseEntity<ResponseMessage> uploadFileArchive(@RequestParam("file") MultipartFile file, @RequestParam("country") String country, @RequestParam("university") String university) {


        if (country.equals("undefined") || country.equals("")) {
            country = null;
        }
        String message = "";
        System.out.println(country + "ooooooooooooooooooooooooooooooooooooooooooo");

        if (Archive.hasExcelFormatArchive(file)) {
            if (country == null && Archive.hasExcelFormatArchive(file)) {
                message = "Please Enter Country Name For Archive File:" + " " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));

            } else if (country != null && Archive.hasExcelFormatArchive(file)) {
                try {
                    userServiceimpl.saveUserArchive(file, country, university);
                    message = "Uploaded The Archive File Successfully: " + " " + file.getOriginalFilename();
                    return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
                } catch (Exception e) {
                    message = "Could not Upload The Archive File: " + " " + file.getOriginalFilename() + "!";
                    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
                }
            }
        }
        message = "Please upload an excel file to Archive!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }

//        String message = "";
//        if (Archive.hasExcelFormatArchive(file)) {
//            try {
//                userServiceimpl.saveUserArchive(file, country, university);
//                message = "Uploaded the file successfully: " + file.getOriginalFilename();
//                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
//            } catch (Exception e) {
//                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
//                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
//            }
//        }
//        message = "Please upload an excel file!";
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
//    }


    //Get by id
    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable("id") long id) {

        Optional<UserEntity> userEntity = userRepository.findById(id);
        if (userEntity.isPresent()) {
            return new ResponseEntity<>(userEntity.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //to change the status
    @PutMapping("/statusCompleted/{id}")
    public ResponseEntity<UserEntity> statusCompleted(@PathVariable("id") long id) {

        UserEntity userEntity = userServiceimpl.findById(id);
        if (userEntity == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userEntity.setStatus("completed");
        UserEntity updatedUser = userServiceimpl.updateUser(userEntity);

        return new ResponseEntity<>(updatedUser, HttpStatus.OK);

    }

    //to hide status
    @PutMapping("/statusHideCompleted/{id}")
    public ResponseEntity<UserEntity> statusHideCompleted(@PathVariable("id") long id) {
        UserEntity userEntity = userServiceimpl.findById(id);
        if (userEntity == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (userEntity.getStatus().equals("completed")) {
            userEntity.setStatus("hideCompleted");
        }
        UserEntity updatedUser = userServiceimpl.updateUser(userEntity);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }


    //Delete operation
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") Long id) {
        userServiceimpl.deleteUserById(id);
        return ResponseEntity.status(200).body(null);
    }


    @GetMapping("/all")
    public ResponseEntity<List<UserEntity>> getAllEntities() {
        try {
            List<UserEntity> entities = userServiceimpl.getAllEntities();
            if (entities.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(entities, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // pagination
    @GetMapping("/pagination-levels")
    public ResponseEntity<List<UserEntity>> getAllUser(@RequestParam int pageNo, @RequestParam int pageSize) {
        // log.debug("REST request to get a page of OrganizationLevels");
        // pageable = new QPageRequest(1, 2);
        Pageable pageing = PageRequest.of(pageNo, pageSize);
        Page<UserEntity> page = userServiceimpl.findAll(pageing);
        HttpHeaders headers = PaginationUtils.generatePaginationHttpHeaders(page, "/pagination-levels");
        headers.add("Access-Control-Expose-Headers", " X-Total-Count, X-Paging-PageSize");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }


    //for upload Files
    @PostMapping("/uploadMultipleFiles/{id}")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file, @PathVariable("id") long id) {
        String message = "";
        try {

            userServiceimpl.saveFiledPdf(file, id);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file : " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/files")

    public ResponseEntity<List<FilesInfo>> getListFiles() {

        List<FilesInfo> fileInfos = userServiceimpl.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(ExcelController.class, "getFile", path.getFileName().toString()).build().toString();
            return new FilesInfo(filename, url);
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = userServiceimpl.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +
                        file.getFilename() + "\"").body(file);
    }

    // Delete operation
    @GetMapping("/delete-file/{filename:.+}/{id}")
    public ResponseEntity<String> deleteFileByName(@PathVariable String filename, @PathVariable Long id) {
        userServiceimpl.deleteFile(filename, id);
        return ResponseEntity.status(200).body(null);
    }


}
