package com.anerkenung.springboot.dto;

import com.anerkenung.springboot.entity.UserEntity;

public class CoursesEntityDTO {
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
    private String dExamNameAndNum;
    private String dYesNo;
    private String dRecogCredit;
    private String dAquiredMark;
    private String nameOfExaminer;
    private String country;

    @Override
    public String toString() {
        return "CoursesEntityDTO{" +
                "id=" + id +
                ", sNameSub='" + sNameSub + '\'' +
                ", sCountryOfExam='" + sCountryOfExam + '\'' +
                ", sExamMethod='" + sExamMethod + '\'' +
                ", sCreditSub='" + sCreditSub + '\'' +
                ", sMarkTrans='" + sMarkTrans + '\'' +
                ", consNum1='" + consNum1 + '\'' +
                ", dNameExam='" + dNameExam + '\'' +
                ", consNum2='" + consNum2 + '\'' +
                ", dExamNameAndNum='" + dExamNameAndNum + '\'' +
                ", dYesNo='" + dYesNo + '\'' +
                ", dRecogCredit='" + dRecogCredit + '\'' +
                ", dAquiredMark='" + dAquiredMark + '\'' +
                ", nameOfExaminer='" + nameOfExaminer + '\'' +
                ", country='" + country + '\'' +
                ", university='" + university + '\'' +
                '}';
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    private String university;



//    public CoursesEntityDTO(
//            String sNameSub, String sCountryOfExam,
//            String sExamMethod, String sCreditSub,
//            String sMarkTrans, String consNum1, String dNameExam,
//            String consNum2, String dExamNameAndNum,
//            String dYesNo, String dRecogCredit,
//            String dAquiredMark, String nameOfExaminer
//            ) {
//
//        this.sNameSub = sNameSub;
//        //this.sCountryOfExam = sCountryOfExam;
//        this.sExamMethod = sExamMethod;
//        this.sCreditSub = sCreditSub;
//        this.sMarkTrans = sMarkTrans;
//       // this.consNum1 = consNum1;
//        this.dNameExam = dNameExam;
//        this.consNum2 = consNum2;
//        this.dExamNameAndNum = dExamNameAndNum;
//        this.dYesNo = dYesNo;
//        this.dRecogCredit = dRecogCredit;
//        this.dAquiredMark = dAquiredMark;
//        this.nameOfExaminer = nameOfExaminer;
//
//    }


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    public Long getId() {
        return id;
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
        return dExamNameAndNum;
    }

    public void setDExamNameAndNum(String destinationExamNumberAndName) {
        this.dExamNameAndNum = destinationExamNumberAndName;
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

}
