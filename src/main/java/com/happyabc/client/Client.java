package com.happyabc.client;

import com.happyabc.excel.ReadPolicyExcel;
import com.happyabc.excel.SaveData2DB;
import com.happyabc.excel.util.ExcelUtil;
import com.happyabc.excel.vo.BankDO;
import com.happyabc.excel.vo.LoanPolicyDO;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
    public void testmain() throws Exception {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // 同时支持Excel 2003、2007
            // 创建文件对象
            File excelFile = new File("d:/cap_template.xls");
            // 文件流
            FileInputStream in = new FileInputStream(excelFile);
            ExcelUtil.checkExcelVaild(excelFile);
            Workbook workbook = ExcelUtil.getWorkbok(in, excelFile);
            // 这种方式 Excel2003/2007/2010都是可以处理的
            //Workbook workbook = WorkbookFactory.create(is);
            // Sheet的数量
            int sheetCount = workbook.getNumberOfSheets();
            /**
             * 设置当前excel中sheet的下标：0开始
             */
            // 遍历第一个Sheet
            Sheet sheet = workbook.getSheetAt(0);

            //获取总行数
            System.out.println("总行数:" + sheet.getLastRowNum());

            // 为跳过第一行目录设置count
            int count = 0;
            for (Row row : sheet) {
                try {
                    // 跳过第一和第二行的目录
                    if (count < 2) {
                        count++;
                        continue;
                    }

                    //如果当前行没有数据，跳出循环
                    if (row.getCell(0).toString().equals("")) {
                        return;
                    }

                    //获取总列数(空格的不计算)
                    int columnTotalNum = row.getPhysicalNumberOfCells();
                    System.out.println("总列数：" + columnTotalNum + "最大列数：" + row.getLastCellNum());

                    //for循环的，不扫描空格的列
//                    for (Cell cell : row) {
//                    	System.out.println(cell);
//                    }
                    int end = row.getLastCellNum();
                    for (int i = 0; i < end; i++) {
                        Cell cell = row.getCell(i);
                        if (cell == null) {
                            System.out.print("null" + "\t");
                            continue;
                        }

                        Object obj = ExcelUtil.getValue(cell);
                        System.out.print(obj + "\t");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
