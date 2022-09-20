package com.anerkenung.springboot.service;

import com.anerkenung.springboot.entity.CoursesEntity;
import com.anerkenung.springboot.entity.UserEntity;

public interface CourseService {
    // Save operation
    UserEntity getCourses(CoursesEntity coursesEntity);
}
