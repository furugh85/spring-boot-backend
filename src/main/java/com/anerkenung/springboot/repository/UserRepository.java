package com.anerkenung.springboot.repository;

import com.anerkenung.springboot.entity.CoursesEntity;
import com.anerkenung.springboot.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findById(Id id);

    Page<UserEntity> findAllByStatusIsNot(String status, Pageable pageable);

}
