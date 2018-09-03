package com.happyabc.excel;

import com.happyabc.common.Common;
import com.happyabc.excel.util.ExeclUtils;
import com.happyabc.excel.vo.LoanPolicyDO;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author happyabc
 * @created 2018-7-22
 */
public class ReadPolicyExcel {

    public List<LoanPolicyDO> getLoanPolicyDOExcel() throws IOException {
        InputStream is = new FileInputStream(Common.EXCEL_CAP_PATH);
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
                    policyDO.setInsName(ExeclUtils.getValue(hssfRow.getCell(0)));
                    policyDO.setPolicyNo(ExeclUtils.getValue(hssfRow.getCell(1)));
                    policyDO.setPolicyType(ExeclUtils.getValue(hssfRow.getCell(2)));
//                    policyDO.setLiabilityStartDt(new Date(ExeclUtils.getValue(hssfRow.getCell(3))));
                    policyDO.setApplicant(ExeclUtils.getValue(hssfRow.getCell(4)));
                    policyDO.setApplicantId(ExeclUtils.getValue(hssfRow.getCell(5)));
                    policyDO.setInsuredName(ExeclUtils.getValue(hssfRow.getCell(6)));
                    policyDO.setInsuredId(ExeclUtils.getValue(hssfRow.getCell(7)));
//                    policyDO.setPaymentDate(new Date(ExeclUtils.getValue(hssfRow.getCell(8))));
                    policyDO.setInsFee(new BigDecimal(ExeclUtils.getValue(hssfRow.getCell(9))));
                    policyDO.setCommissionRate(new BigDecimal(ExeclUtils.getValue(hssfRow.getCell(10))));
                    policyDO.setPolicyStatus(ExeclUtils.getValue(hssfRow.getCell(11)));
                    policyDO.setSettlePeriod(new Integer(ExeclUtils.getValue(hssfRow.getCell(12))));
                    policyDO.setCarNumber(ExeclUtils.getValue(hssfRow.getCell(13)));
                    policyDO.setCarType(ExeclUtils.getValue(hssfRow.getCell(14)));
                    policyDO.setCarFunction(ExeclUtils.getValue(hssfRow.getCell(15)));
                    policyDO.setInsuredName(ExeclUtils.getValue(hssfRow.getCell(16)));
                    policyDO.setChannelType(ExeclUtils.getValue(hssfRow.getCell(17)));
                    policyDO.setAgent(ExeclUtils.getValue(hssfRow.getCell(18)));
                    list.add(policyDO);
                }
            }
        }
        return list;
    }


}
