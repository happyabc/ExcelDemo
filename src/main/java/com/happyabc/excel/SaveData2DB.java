package com.happyabc.excel;

import com.happyabc.common.Common;
import com.happyabc.excel.util.DbUtil;
import com.happyabc.excel.vo.BankDO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author happyabc
 * @created 2018-7-22
 */
public class SaveData2DB {

    @SuppressWarnings({"rawtypes"})
    public void save() throws IOException, SQLException {
        ReadStudentExcel xlsMain = new ReadStudentExcel();
        BankDO bankDO = null;
        List<BankDO> list = xlsMain.getStudentExcel();

        for (int i = 0; i < list.size(); i++) {
            bankDO = list.get(i);
            List l = DbUtil.selectOne(Common.SELECT_STUDENT_SQL + "'%" + bankDO.getName() + "%'", bankDO);
            if (!l.contains(1)) {
                DbUtil.insert(Common.INSERT_STUDENT_SQL, bankDO);
            } else {
                System.out.println("The Record was Exist : No. = " + bankDO.getNo() + " , Name = " + bankDO.getName() + ", Age = " + bankDO.getAge() + ", and has been throw away!");
            }
        }
    }
}
