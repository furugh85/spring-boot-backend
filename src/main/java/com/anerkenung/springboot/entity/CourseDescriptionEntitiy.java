package com.anerkenung.springboot.entity;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "t_description")
public class CourseDescriptionEntitiy implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String examnumber;
    private String description;

    public CourseDescriptionEntitiy(Long id, String examnumber, String description) {
        this.id = id;
        this.examnumber = examnumber;
        this.description = description;
    }
    public CourseDescriptionEntitiy(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExamnumber() {
        return examnumber;
    }

    public void setExamnumber(String examnumber) {
        this.examnumber = examnumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "CourseDescription{" +
                "id=" + id +
                ", examnumber='" + examnumber + '\'' +
                ", description='" + description + '\'' +
                '}';
    }




}
