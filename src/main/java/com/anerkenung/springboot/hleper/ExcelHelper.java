package com.anerkenung.springboot.hleper;

import com.anerkenung.springboot.entity.CourseDescriptionEntitiy;
import com.anerkenung.springboot.entity.CoursesEntity;
import com.anerkenung.springboot.entity.UserEntity;
import com.anerkenung.springboot.filesandsheets.OtherSheets;
import com.anerkenung.springboot.repository.DescriptionRepository;
import com.anerkenung.springboot.repository.UserRepository;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Component
public class ExcelHelper {
    @Autowired
    DescriptionRepository descriptionRepository;
    CourseDescriptionEntitiy courseDescriptionEntitiy;
    @Autowired
    UserRepository userRepository;
    int a = 244;

    public boolean hasExcelFormat(MultipartFile file) {
        String contentType = file.getContentType();
        if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
            return true;
        } else {
            return false;
        }
    }

    //convert excel to list of users
    public UserEntity convertExcelToListOfUser(InputStream is, String country, String university) {

        List<CoursesEntity> entities1 = new ArrayList<>();
        UserEntity userEntity = new UserEntity();
        XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook(is);
        } catch (Exception ex) {

            ex.printStackTrace();
        }

        XSSFSheet sheet = workbook.getSheet("Formular");
        XSSFSheet sheet1 = workbook.getSheet("Metallurgy and Metal Forming");
        XSSFSheet sheet2 = workbook.getSheet("Structural Engineering");
        XSSFSheet sheet3 = workbook.getSheet("Metallurgy & Metal Form. (dual)");
        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
        Number cellValue2 = sheet.getRow(5).getCell(5).getNumericCellValue();
        String cellValue3 = sheet.getRow(6).getCell(5).getStringCellValue();
        String cellValue1 = sheet.getRow(2).getCell(5).getStringCellValue();

        System.out.println(cellValue1);
        //put info from excel file into userEntity
        userEntity.setName("Applicant");
        userEntity.setStatus("pending");
        String stringMatriculeNumber=cellValue2.longValue()+"";
        //String finalStr= stringMatriculeNumber.hashCode()+"";
        userEntity.setMatrikelnummer(stringMatriculeNumber);

        userEntity.setStudiengang(cellValue3);


        try {
            Iterator<Row> rows = sheet.rowIterator();
            while (rows.hasNext()) {
                Row row = rows.next();
                // Skip first 9 rows
                if (row.getRowNum() <= 9 || row.getRowNum() >= 64) {
                    continue;
                }
                CoursesEntity coursesEntity = new CoursesEntity();

                coursesEntity.setSNameSub(getCellValue(row, 0, evaluator));

                if (coursesEntity.getSNameSub() == "")
                    break;
                coursesEntity.setSCountryOfExam(getCellValue(row, 2, evaluator));
                coursesEntity.setSExamMethod(getCellValue(row, 3, evaluator));
                coursesEntity.setSCreditSub(getCellValue(row, 4, evaluator));
                coursesEntity.setSMarkTrans(getCellValue(row, 5, evaluator));
                coursesEntity.setConsNum1(getCellValue(row, 6, evaluator));
                coursesEntity.setDNameExam(getCellValue(row, 7, evaluator));
                coursesEntity.setConsNum2(getCellValue(row, 8, evaluator));
                if (coursesEntity.getConsNum2() == "") {
                    coursesEntity.setConsNum2(coursesEntity.getConsNum1());
                }
                if (coursesEntity.getConsNum2() != "") {
                    coursesEntity.setConsNum2(coursesEntity.getConsNum1());
                }
                // coursesEntity.setDestinationExamNumberAndName(getCellValue(row, 9, evaluator));

                coursesEntity.setDExamNameAndNum(row.getCell(9).getStringCellValue());
                if (coursesEntity.getDExamNameAndNum() != "") {
                    coursesEntity.setDExamNameAndNum("");
                }
                coursesEntity.setDYesNo(getCellValue(row, 10, evaluator));
                if (coursesEntity.getDYesNo() == "") {
                    coursesEntity.setDYesNo(null);
                }
                if (coursesEntity.getDYesNo() != "") {
                    coursesEntity.setDYesNo(null);
                }
                coursesEntity.setDRecogCredit(getCellValue(row, 11, evaluator));
                if (coursesEntity.getDRecogCredit() == "") {
                    coursesEntity.setDRecogCredit(null);
                }
                if (coursesEntity.getDRecogCredit() != "") {
                    coursesEntity.setDRecogCredit(null);
                }
                coursesEntity.setDAquiredMark(getCellValue(row, 12, evaluator));
                if (coursesEntity.getDAquiredMark() == "") {
                    coursesEntity.setDAquiredMark(null);
                }
                if (coursesEntity.getDAquiredMark() != "") {
                    coursesEntity.setDAquiredMark(null);
                }
                coursesEntity.setNameOfExaminer(getCellValue(row, 13, evaluator));
                String cellSub1 = sheet.getRow(6).getCell(5).getStringCellValue();
                //to fill cell9 with different sheets
                if (coursesEntity.getDExamNameAndNum() == ""
                        && cellSub1.equalsIgnoreCase("Bachelor of Science Metallurgy and Metal Forming (dual) (PO15)")) {
                    getSheetValue(sheet1, coursesEntity);

                } else if (coursesEntity.getDExamNameAndNum() == ""
                        && cellSub1.equalsIgnoreCase("Bachelor of Science Structural Engineering (PO14)") || cellSub1.equalsIgnoreCase("Bachelor of Science Structural Engineering (PO19)")) {
                    getSheetValue(sheet2, coursesEntity);
                } else if (coursesEntity.getDExamNameAndNum() == ""
                        && cellSub1.equalsIgnoreCase("Bachelor of Science Metallurgy and Metal Forming (PO15)") || cellSub1.equalsIgnoreCase("Bachelor of Science Metallurgy and Metal Forming (dual) (PO19)")) {
                    getSheetValue(sheet3, coursesEntity);
                }

                if (coursesEntity.getSNameSub() == "")
                    break;
                coursesEntity.setCountry(country);
                coursesEntity.setUniversity(university);
                if (coursesEntity.getUniversity() == "") {
                    coursesEntity.setUniversity(null);
                }
                entities1.add(coursesEntity);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }


        userEntity.setCoursesEntities(entities1);
        return userEntity;
    }

    //method to iterate on different sheets to full fill with  appropriate
    public void getSheetValue(XSSFSheet sheet123, CoursesEntity coursesEntity) {
        OtherSheets otherSheets = new OtherSheets();
        DataFormatter dataFormatter = new DataFormatter();
        for (int i = 6; i < sheet123.getPhysicalNumberOfRows(); i++) {
            XSSFRow row2 = sheet123.getRow(i);
            otherSheets.setLfdNo(dataFormatter.formatCellValue(row2.getCell(0)));
            otherSheets.setPool(dataFormatter.formatCellValue(row2.getCell(1)));
            otherSheets.setPr??fNr(dataFormatter.formatCellValue(row2.getCell(2)));
            otherSheets.setPr??fungName(dataFormatter.formatCellValue(row2.getCell(3)));
            otherSheets.setPr??fungCredit(dataFormatter.formatCellValue(row2.getCell(4)));
            otherSheets.setPr??fungCredit(dataFormatter.formatCellValue(row2.getCell(5)));

            String lfdNo = otherSheets.getLfdNo();
            String pool = otherSheets.getPool();
            String pr??fNo = otherSheets.getPr??fNr();
            String pr??fungName = otherSheets.getPr??fungName();
            String pr??fungCredit = otherSheets.getPr??fungCredit();
            String sheet1DName = pool.concat("/" + pr??fNo + "/" + pr??fungName);
            System.out.println(sheet1DName);
            if (coursesEntity.getConsNum1().equals(lfdNo)) {
                coursesEntity.setDExamNameAndNum(sheet1DName);
                coursesEntity.setExamNum(pr??fNo);
                Optional<CourseDescriptionEntitiy> courseDescriptionEntitiy = descriptionRepository.findByExamnumber(pr??fNo);
                if (courseDescriptionEntitiy.isPresent()) {
                    coursesEntity.setCourseDescriptionEntitiy(courseDescriptionEntitiy.get());
                }
            }
        }

    }


    public String getCellValue(Row row, int colNumber, FormulaEvaluator evaluator) {
        XSSFCell cell = (XSSFCell) row.getCell(colNumber);
        DataFormatter dataFormatter = new DataFormatter();
        return dataFormatter.formatCellValue(cell, evaluator);
    }


}

