package com.happyabc.excel.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExeclUtils {
//    public static String getValue(Cell hssfCell) {
//        if (hssfCell.getCellType() == CellType.BOOLEAN.getCode()) {
//            // 返回布尔类型的值
//            return String.valueOf(hssfCell.getBooleanCellValue());
//        } else if (hssfCell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
//            // 返回数值类型的值
//            return String.valueOf(hssfCell.getNumericCellValue());
//        } else {
//            // 返回字符串类型的值
//            return String.valueOf(hssfCell.getStringCellValue());
//        }
//    }

    public static String getValue(Cell cell) {
        String temp = "";
        if (cell == null) {
            return temp;
        }
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                return cell.getRichStringCellValue().getString();
            case Cell.CELL_TYPE_NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    Date date = cell.getDateCellValue();
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    temp = df.format(date);
                } else {
                    return String.valueOf(cell.getNumericCellValue());
                }
            case Cell.CELL_TYPE_FORMULA:
                cell.setCellType(Cell.CELL_TYPE_STRING);
                return cell.getStringCellValue();

            default:
                return temp;

        }
    }
}