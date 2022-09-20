package com.anerkenung.springboot.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_course")
public class CoursesEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //student part
    private String sNameSub;
    private String sCountryOfExam;
    private String sExamMethod;
    private String sCreditSub;
    private String sMarkTrans;
    //number of subject to be annrekant
    private String consNum1;
    //name of subject to be annerkant in our uni choosed by Student part
    private String dNameExam;
    //prof part
    private String consNum2;
    //choosed by prof part
    private String dExamNameNum;
    private String dYesNo;
    private String dRecogCredit;
    private String dAquiredMark;
    private String nameOfExaminer;
    private String country;
    @Column(length = 1000)
    private String university;
    private String examNum;



    @ManyToOne
    private CourseDescriptionEntitiy courseDescriptionEntitiy;



    public CoursesEntity(
            String sNameSub, String sCountryOfExam,
            String sExamMethod, String sCreditSub,
            String sMarkTrans, String consNum1, String dNameExam,
            String consNum2, String dExamNameNum,
            String dYesNo, String dRecogCredit,
            String dAquiredMark, String nameOfExaminer,
            String examNum,
            UserEntity userEntity

    ) {

        this.sNameSub = sNameSub;
        this.sCountryOfExam = sCountryOfExam;
        this.sExamMethod = sExamMethod;
        this.sCreditSub = sCreditSub;
        this.sMarkTrans = sMarkTrans;
        this.consNum1 = consNum1;
        this.dNameExam = dNameExam;
        this.consNum2 = consNum2;
        this.dExamNameNum = dExamNameNum;
        this.dYesNo = dYesNo;
        this.dRecogCredit = dRecogCredit;
        this.dAquiredMark = dAquiredMark;
        this.nameOfExaminer = nameOfExaminer;
        //   this.userEntity = userEntity;
    }

    public CoursesEntity() {

    }


    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getExamNum() {
        return examNum;
    }

    public void setExamNum(String examNum) {
        this.examNum = examNum;
    }


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public CourseDescriptionEntitiy getCourseDescriptionEntitiy() {
        return courseDescriptionEntitiy;
    }

    public void setCourseDescriptionEntitiy(CourseDescriptionEntitiy courseDescriptionEntitiy) {
        this.courseDescriptionEntitiy = courseDescriptionEntitiy;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSNameSub() {
        return sNameSub;
    }

    public void setSNameSub(String nameOfSourceSubject) {
        this.sNameSub = nameOfSourceSubject;
    }

    public String getSCountryOfExam() {
        return sCountryOfExam;
    }

    public void setSCountryOfExam(String countryOfExamination) {
        this.sCountryOfExam = countryOfExamination;
    }

    public String getSExamMethod() {
        return sExamMethod;
    }

    public void setSExamMethod(String examinationMethod) {
        this.sExamMethod = examinationMethod;
    }

    public String getSCreditSub() {
        return sCreditSub;
    }

    public void setSCreditSub(String creditOfSourceSubject) {
        this.sCreditSub = creditOfSourceSubject;
    }

    public String getSMarkTrans() {
        return sMarkTrans;
    }

    public void setSMarkTrans(String markAccToTranscript) {
        this.sMarkTrans = markAccToTranscript;
    }

    public String getConsNum1() {
        return consNum1;
    }

    public void setConsNum1(String consNumber1) {
        this.consNum1 = consNumber1;
    }

    public String getDNameExam() {
        return dNameExam;
    }

    public void setDNameExam(String destinationExams) {
        this.dNameExam = destinationExams;
    }

    public String getConsNum2() {
        return consNum2;
    }

    public void setConsNum2(String consNumber2) {
        this.consNum2 = consNumber2;
    }

    public String getDExamNameAndNum() {
        return dExamNameNum;
    }

    public void setDExamNameAndNum(String destinationExamNumberAndName) {
        this.dExamNameNum = destinationExamNumberAndName;
    }

    public String getDYesNo() {
        return dYesNo;
    }

    public void setDYesNo(String destinationYesNo) {
        this.dYesNo = destinationYesNo;
    }

    public String getDRecogCredit() {
        return dRecogCredit;
    }

    public void setDRecogCredit(String destinationRecognizedCredits) {
        this.dRecogCredit = destinationRecognizedCredits;
    }

    public String getDAquiredMark() {
        return dAquiredMark;
    }

    public void setDAquiredMark(String destinationAquiredMark) {
        this.dAquiredMark = destinationAquiredMark;
    }

    public String getNameOfExaminer() {
        return nameOfExaminer;
    }

    public void setNameOfExaminer(String nameOfExamined) {
        this.nameOfExaminer = nameOfExamined;
    }



    @Override
    public String toString() {
        return "CoursesEntity{" +
                "id=" + id +
                ", sNameSub='" + sNameSub + '\'' +
                ", sCountryOfExam='" + sCountryOfExam + '\'' +
                ", sExamMethod='" + sExamMethod + '\'' +
                ", sCreditSub='" + sCreditSub + '\'' +
                ", sMarkTrans='" + sMarkTrans + '\'' +
                ", consNum1='" + consNum1 + '\'' +
                ", dNameExam='" + dNameExam + '\'' +
                ", consNum2='" + consNum2 + '\'' +
                ", dExamNameNum='" + dExamNameNum + '\'' +
                ", dYesNo='" + dYesNo + '\'' +
                ", dRecogCredit='" + dRecogCredit + '\'' +
                ", dAquiredMark='" + dAquiredMark + '\'' +
                ", nameOfExaminer='" + nameOfExaminer + '\'' +
                ", country='" + country + '\'' +
                ", university='" + university + '\'' +
//                ", description='" + description + '\'' +
                ", examNum='" + examNum + '\'' +
                '}';
    }


}
