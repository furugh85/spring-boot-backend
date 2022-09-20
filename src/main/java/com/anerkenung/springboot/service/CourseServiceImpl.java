package com.anerkenung.springboot.service;

import com.anerkenung.springboot.dto.CoursesEntityDTO;
import com.anerkenung.springboot.entity.CoursesEntity;
import com.anerkenung.springboot.entity.UserEntity;
import com.anerkenung.springboot.exception.UserNotFoundException;
import com.anerkenung.springboot.hleper.ExcelHelper;
import com.anerkenung.springboot.repository.CourseRepository;
import com.anerkenung.springboot.repository.DescriptionRepository;
import com.anerkenung.springboot.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    private ModelMapper modelMapper;


    public List<CoursesEntity> getAllEntities() {

        return courseRepository.findAll();
    }

    public CoursesEntity update(CoursesEntity coursesEntity) {
        return courseRepository.save(coursesEntity);
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteCourseById(id);
    }

    public CoursesEntity findByCourseById(Long id) {
        return courseRepository.findCourseById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id" + id + "was not found"));
    }

//    public List<CoursesEntityDTO> getDNameExamAndCountry() {
//        return courseRepository.findBydNameExamAndCountry().stream().map(this::coursesEntityToDTO)
//                .List(Collectors.toList());
//
//    }
//
//    private CoursesEntityDTO coursesEntityToDTO(CoursesEntity coursesEntity) {
//        CoursesEntityDTO coursesEntityDTO = new CoursesEntityDTO();
//        coursesEntityDTO = modelMapper.map(coursesEntity, CoursesEntityDTO.class);
//        return coursesEntityDTO;
//
//    }

}
