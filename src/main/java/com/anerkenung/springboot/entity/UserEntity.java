package com.anerkenung.springboot.entity;

import org.springframework.http.converter.json.GsonBuilderUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "t_student")
public class UserEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String Name;
    @Column(name = "status")
    private String status;
    @Column(unique = true)
    private String Matrikelnummer;
    private String Studiengang;



    @OneToMany(cascade = CascadeType.ALL, orphanRemoval=true, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_id", referencedColumnName = "id")
    //List<CoursesEntity> coursesEntities = new ArrayList<>();
    private List<CoursesEntity> coursesEntities;

    @ElementCollection
    @CollectionTable(name = "user_files", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "user_file")
    private Set<String> files= new HashSet<>();


    public UserEntity(Long id, String status, String name, String matrikelnummer, String studiengang, List<CoursesEntity> coursesEntities
            , Set<String> files) {
        this.Name = name;
        this.Matrikelnummer = matrikelnummer;
        this.Studiengang = studiengang;
        this.coursesEntities = coursesEntities;
        this.id = id;
        this.files=files;
        this.status = status;

    }

    public UserEntity() {

    }

    public String getMatrikelnummer() {
        return Matrikelnummer;
    }

    public void setMatrikelnummer(String matrikelnummer) {
        Matrikelnummer = matrikelnummer;
    }

    public String getStudiengang() {
        return Studiengang;
    }

    public void setStudiengang(String studiengang) {
        Studiengang = studiengang;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public List<CoursesEntity> getCoursesEntities() {
        return coursesEntities;
    }

    public void setCoursesEntities(List<CoursesEntity> coursesEntities) {
        this.coursesEntities = coursesEntities;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<String> getFiles() {

        return files;
    }

    public void setFiles(Set<String> files) {
        this.files = files;
    }


    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", status='" + status + '\'' +
                ", Matrikelnummer=" + Matrikelnummer +
                ", Studiengang='" + Studiengang + '\'' +
                ", coursesEntities=" + coursesEntities +
                ", files=" + files +
                '}';
    }
}

