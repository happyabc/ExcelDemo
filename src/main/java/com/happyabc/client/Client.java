package com.happyabc.client;

import com.happyabc.excel.ReadPolicyExcel;
import com.happyabc.excel.SaveData2DB;
import com.happyabc.excel.util.ExcelUtil;
import com.happyabc.excel.vo.LoanPolicyDO;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author happyabc
 * @created 2018-7-22
 */
public class Client {
    @Test
    public void testImportBank() throws IOException, SQLException {
        SaveData2DB saveData2DB = new SaveData2DB();
        saveData2DB.save();
        System.out.println("end");
    }

    @Test
    public void testImportPolicy() throws IOException, SQLException {
        ReadPolicyExcel xlsMain = new ReadPolicyExcel();

        List<LoanPolicyDO> list = xlsMain.getLoanPolicyDOExcel();
        for (LoanPolicyDO loanPolicyDO : list) {
            System.out.println(loanPolicyDO.toString());
        }

    }

    /**
     * 读取Excel测试，兼容 Excel 2003/2007/2010
     *
     * @throws Exception
     */
    @Test
    public void testPolicy() throws Exception {
        List<LoanPolicyDO> list = getPolicyExcelData();
        System.out.println("Size=" + list.size());

    }

    private List<LoanPolicyDO> getPolicyExcelData() throws IOException {
        List<LoanPolicyDO> list = new ArrayList<LoanPolicyDO>();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        FileInputStream in = null;
        try {
            // 同时支持Excel 2003、2007
            // 创建文件对象
            File excelFile = new File("d:/cap_template.xls");
            // 文件流
            in = new FileInputStream(excelFile);
            ExcelUtil.checkExcelVaild(excelFile);
            Workbook workbook = ExcelUtil.getWorkbok(in, excelFile);
            // Sheet的数量
            int sheetCount = workbook.getNumberOfSheets();
            // 遍历第一个Sheet
            Sheet sheet = workbook.getSheetAt(0);

            //获取总行数
           // System.out.println("总行数:" + sheet.getLastRowNum());

            int count = 0;
            for (Row row : sheet) {
                LoanPolicyDO policyDO = new LoanPolicyDO();
                //1:检验
                // 跳过第一行的目录
                if (count < 1) {
                    count++;
                    continue;
                }
                //如果当前行没有数据，跳出循环
                if (row.getCell(0).toString().equals("")) {
                    continue;
                }
                //2:读取数据
                policyDO.setInsName(ExcelUtil.getStringValue(row.getCell(0)));
                policyDO.setPolicyNo(ExcelUtil.getStringValue(row.getCell(1)));
                policyDO.setPolicyType(ExcelUtil.getStringValue(row.getCell(2)));
                policyDO.setLiabilityStartDt(fmt.parse(ExcelUtil.getStringValue(row.getCell(3))));
                policyDO.setApplicant(ExcelUtil.getStringValue(row.getCell(4)));
                policyDO.setApplicantId(ExcelUtil.getStringValue(row.getCell(5)));
                policyDO.setInsuredName(ExcelUtil.getStringValue(row.getCell(6)));
                policyDO.setInsuredId(ExcelUtil.getStringValue(row.getCell(7)));
                policyDO.setPaymentDate(fmt.parse(ExcelUtil.getStringValue(row.getCell(8))));
                policyDO.setInsFee(new BigDecimal(ExcelUtil.getStringValue(row.getCell(9))));
                policyDO.setCommissionRate(new BigDecimal(ExcelUtil.getStringValue(row.getCell(10))));
                policyDO.setPolicyStatus(ExcelUtil.getStringValue(row.getCell(11)));
//                policyDO.setSettlePeriod(new Integer(ExcelUtil.getStringValue(row.getCell(12))).intValue());
                policyDO.setCarNumber(ExcelUtil.getStringValue(row.getCell(13)));
                policyDO.setCarType(ExcelUtil.getStringValue(row.getCell(14)));
                policyDO.setCarFunction(ExcelUtil.getStringValue(row.getCell(15)));
                policyDO.setInsuredName(ExcelUtil.getStringValue(row.getCell(16)));
                policyDO.setChannelType(ExcelUtil.getStringValue(row.getCell(17)));
                policyDO.setAgent(ExcelUtil.getStringValue(row.getCell(18)));
                list.add(policyDO);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            in.close();
        }
        return list;
    }
}
