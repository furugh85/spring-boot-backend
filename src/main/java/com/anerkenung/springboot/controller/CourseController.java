package com.anerkenung.springboot.controller;

import com.anerkenung.springboot.dto.CoursesEntityDTO;
import com.anerkenung.springboot.entity.CoursesEntity;
import com.anerkenung.springboot.repository.CourseRepository;
import com.anerkenung.springboot.service.CourseServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping(path = "/course")
public class CourseController {

    @Autowired
    private CourseServiceImpl courseServiceimpl;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ModelMapper modelMapper;

    CourseController(CourseRepository courseRepository, CourseServiceImpl courseServiceimpl) {
        this.courseRepository = courseRepository;
        this.courseServiceimpl = courseServiceimpl;
    }

    @GetMapping("/findbyid/{id}")
    public ResponseEntity<CoursesEntity> getCoursesById(@PathVariable("id") long id) {
        CoursesEntity coursesEntity = courseServiceimpl.findByCourseById(id);
        return new ResponseEntity<>(coursesEntity, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<CoursesEntity> updateCourse(@RequestBody CoursesEntity coursesEntity) {
        if (coursesEntity.getDYesNo().isEmpty()) {
            coursesEntity.setDYesNo(null);
            //  return new ResponseEntity<>(coursesEntity, HttpStatus.OK);
        }
        CoursesEntity updateCourse = courseServiceimpl.update(coursesEntity);
        return new ResponseEntity<>(updateCourse, HttpStatus.OK);
    }

    //    @GetMapping("/examandcountry")
//    public ResponseEntity<List<CoursesEntity>>findBydNameExamAndCountry
//            (@RequestParam("dnameExam") String dNameExam, @RequestParam("country") String country){
//
//        return new ResponseEntity<List<CoursesEntity>>(courseRepository.findBydNameExamAndCountry(dNameExam,country),HttpStatus.OK);
//    }
    @GetMapping("/examandcountry")
    public List<CoursesEntityDTO> findBydNameExamAndCountry
    (@RequestParam("dnameExam") String dNameExam, @RequestParam("country") String country) {
        return courseRepository.findBydNameExamAndCountry(dNameExam, country).stream()
                .map(coursesEntity -> modelMapper.map(coursesEntity, CoursesEntityDTO.class)).collect(Collectors.toList());


    }

    //Delete operation
    @Transactional
    @DeleteMapping("/deleteCourse/{id}")
    public ResponseEntity<String> deleteCourseById(@PathVariable("id") Long id) {
        courseServiceimpl.deleteCourse(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
