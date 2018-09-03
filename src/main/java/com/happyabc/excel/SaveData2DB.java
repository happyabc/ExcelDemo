package com.happyabc.excel;

import com.happyabc.common.Common;
import com.happyabc.excel.util.DbUtil;
import com.happyabc.excel.vo.Student;

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
        ReadExcel xlsMain = new ReadExcel();
        Student student = null;
        List<Student> list = xlsMain.getStudentExcel();

        for (int i = 0; i < list.size(); i++) {
            student = list.get(i);
            List l = DbUtil.selectOne(Common.SELECT_STUDENT_SQL + "'%" + student.getName() + "%'", student);
            if (!l.contains(1)) {
                DbUtil.insert(Common.INSERT_STUDENT_SQL, student);
            } else {
                System.out.println("The Record was Exist : No. = " + student.getNo() + " , Name = " + student.getName() + ", Age = " + student.getAge() + ", and has been throw away!");
            }
        }
    }
}
