package com.happyabc.common;

/**
 * @author happyabc
 * @created 2018-7-22
 */
public class Common {

    // connect the database
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String DB_NAME = "ft_cap_00";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "123456";
    public static final String IP = "127.0.0.1";
    public static final String PORT = "3306";
    public static final String URL = "jdbc:mysql://" + IP + ":" + PORT + "/" + DB_NAME;

    // common
    public static final String EXCEL_PATH = "doc/bank.xls";
    public static final String EXCEL_CAP_PATH = "doc/cap_template.xls";

    /**
     * sql
     */
    public static final String INSERT_STUDENT_SQL = "insert into student_info(no, name, age, score,a,b) values(?, ?, ?, ?, ?, ?)";
    public static final String UPDATE_STUDENT_SQL = "update student_info set no = ?, name = ?, age= ?, score = ? where id = ? ";
    public static final String SELECT_STUDENT_ALL_SQL = "select id,no,name,age,score from student_info";
    public static final String SELECT_STUDENT_SQL = "select * from student_info where name like ";
}
