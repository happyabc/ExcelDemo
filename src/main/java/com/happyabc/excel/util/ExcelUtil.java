package com.happyabc.excel.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description: Excel操作工具类
 *
 * @author whq
 */
public class ExcelUtil {

    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";

    /**
     * 判断Excel的版本,获取Workbook
     *
     * @param in
     * @param file
     * @return
     * @throws IOException
     */
    public static Workbook getWorkbok(InputStream in, File file) throws IOException {
        Workbook wb = null;
        if (file.getName().endsWith(EXCEL_XLS)) {
            //Excel 2003
            wb = new HSSFWorkbook(in);
        } else if (file.getName().endsWith(EXCEL_XLSX)) {
            // Excel 2007/2010
            wb = new XSSFWorkbook(in);
        }
        return wb;
    }

    /**
     * 判断文件是否是excel
     *
     * @throws Exception
     */
    public static void checkExcelVaild(File file) throws Exception {
        if (!file.exists()) {
            throw new Exception("文件不存在");
        }
        if (!(file.isFile() && (file.getName().endsWith(EXCEL_XLS) || file.getName().endsWith(EXCEL_XLSX)))) {
            throw new Exception("文件不是Excel");
        }
    }

    /**
     * Execl 值转换
     *
     * @param cell
     * @return
     */
    public static Object getObjectValue(Cell cell) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Object obj = null;
        switch (cell.getCellTypeEnum()) {
            case BLANK:
                // 空白格
                obj = cell.getStringCellValue();
                break;
            case BOOLEAN:
                // 布尔型
                obj = cell.getBooleanCellValue();
                break;
            case ERROR:
                // 错误
                obj = cell.getErrorCellValue();
                break;
            case NUMERIC:
                // 数字||日期
                obj = cell.getNumericCellValue();
                boolean cellDateFormatted = DateUtil.isCellDateFormatted(cell);
                if (cellDateFormatted) {
                    Date dateCellValue = cell.getDateCellValue();
                    obj = fmt.format(dateCellValue);
                } else {
                    double numericCellValue = cell.getNumericCellValue();
                    obj = numericCellValue;
                }
                break;
            case STRING:
                obj = cell.getStringCellValue();
                break;

            default:
                break;
        }
        return obj;
    }

    /**
     * Execl 值转换
     *
     * @param cell
     * @return
     */
    public static String getStringValue(Cell cell) {
        if (null != cell) {
            Object obj = getObjectValue(cell);
            return String.valueOf(obj);
        } else {
            return "";
        }
    }
}
