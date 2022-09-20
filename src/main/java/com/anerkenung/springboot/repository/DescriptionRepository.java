package com.anerkenung.springboot.repository;

import com.anerkenung.springboot.entity.CourseDescriptionEntitiy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface DescriptionRepository extends JpaRepository<CourseDescriptionEntitiy, Long> {

    Optional<CourseDescriptionEntitiy> findByExamnumber(String examnumber);

}



