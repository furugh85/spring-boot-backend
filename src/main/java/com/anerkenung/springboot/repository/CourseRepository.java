package com.anerkenung.springboot.repository;

import com.anerkenung.springboot.dto.CoursesEntityDTO;
import com.anerkenung.springboot.entity.CoursesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<CoursesEntity, Long> {
    List<CoursesEntity> findById(Id id);
    void deleteCourseById(Long id);
    //query methods in spring doesnt support by spring we create them
    Optional<CoursesEntity> findCourseById(Long id);
   @Query("SELECT c FROM CoursesEntity c WHERE (c.dNameExam = :dNameExam) and (c.country = :country) and (c.dYesNo is not null )")
      List<CoursesEntity> findBydNameExamAndCountry( String dNameExam , String country);


}


//   List<CoursesEntityDTO> findBydNameExamAndCountry( String dNameExam , String country);






