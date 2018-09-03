package com.happyabc.excel;

import com.happyabc.common.Common;
import com.happyabc.excel.util.ExeclUtils;
import com.happyabc.excel.vo.LoanPolicyDO;
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
public class ReadPolicyExcel {

    public List<LoanPolicyDO> getStudentExcel() throws IOException {
        InputStream is = new FileInputStream(Common.EXCEL_PATH);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        LoanPolicyDO policyDO = null;
        List<LoanPolicyDO> list = new ArrayList<LoanPolicyDO>();
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
                    policyDO = new LoanPolicyDO();
//                    policyDO.setNo(ExeclUtils.getValue(hssfRow.getCell(0)));
//                    policyDO.setName(ExeclUtils.getValue(hssfRow.getCell(1)));
//                    policyDO.setAge(ExeclUtils.getValue(hssfRow.getCell(2)));
//                    policyDO.setScore(ExeclUtils.getValue(hssfRow.getCell(3)));
//                    policyDO.setA(ExeclUtils.getValue(hssfRow.getCell(4)));
//                    policyDO.setB(ExeclUtils.getValue(hssfRow.getCell(5)));
                    list.add(policyDO);
                }
            }
        }
        return list;
    }


}
