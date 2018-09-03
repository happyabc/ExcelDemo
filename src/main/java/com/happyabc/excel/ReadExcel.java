package com.happyabc.excel;

import com.happyabc.common.Common;
import com.happyabc.excel.vo.Student;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author happyabc
 * @created 2018-7-22
 */
public class ReadExcel {

    public List<Student> getStudentExcel() throws IOException {
        InputStream is = new FileInputStream(Common.EXCEL_PATH);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        Student student = null;
        List<Student> list = new ArrayList<Student>();
        // 循环工作表Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // 循环行Row
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow != null) {
                    student = new Student();
                    student.setNo(getValue(hssfRow.getCell(0)));
                    student.setName(getValue(hssfRow.getCell(1)));
                    student.setAge(getValue(hssfRow.getCell(2)));
                    student.setScore(getValue(hssfRow.getCell(3)));
                    student.setA(getValue(hssfRow.getCell(4)));
                    student.setB(getValue(hssfRow.getCell(5)));
                    list.add(student);
                }
            }
        }
        return list;
    }

    private String getValue(HSSFCell hssfCell) {
        if (hssfCell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
            // 返回布尔类型的值
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
            // 返回数值类型的值
            return String.valueOf(hssfCell.getNumericCellValue());
        } else {
            // 返回字符串类型的值
            return String.valueOf(hssfCell.getStringCellValue());
        }
    }
}
