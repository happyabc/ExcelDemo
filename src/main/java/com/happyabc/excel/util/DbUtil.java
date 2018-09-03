package com.happyabc.excel.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.happyabc.common.Common;
import com.happyabc.excel.vo.BankDO;

/**
 * @author happyabc
 * @created 2018-7-22
 */
public class DbUtil {

    /**
     * @param sql
     */
    public static void insert(String sql, BankDO bankDO) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            Class.forName(Common.DRIVER);
            conn = DriverManager.getConnection(Common.URL, Common.USERNAME, Common.PASSWORD);
            ps = conn.prepareStatement(sql);
            ps.setString(1, bankDO.getNo());
            ps.setString(2, bankDO.getName());
            ps.setString(3, bankDO.getAge());
            ps.setString(4, bankDO.getScore());
            ps.setString(5, bankDO.getA());
            ps.setString(6, bankDO.getB());
            boolean flag = ps.execute();
            if (!flag) {
                System.out.println("Save data : No. = " + bankDO.getNo() + " , Name = " + bankDO.getName() + ", Age = " + bankDO.getAge() + " succeed!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static List selectOne(String sql, BankDO bankDO) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List list = new ArrayList();
        try {
            Class.forName(Common.DRIVER);
            conn = DriverManager.getConnection(Common.URL, Common.USERNAME, Common.PASSWORD);
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("no").equals(bankDO.getNo()) || rs.getString("name").equals(bankDO.getName()) || rs.getString("age").equals(bankDO.getAge())) {
                    list.add(1);
                } else {
                    list.add(0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }


    public static ResultSet selectAll(String sql) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Class.forName(Common.DRIVER);
            conn = DriverManager.getConnection(Common.URL, Common.USERNAME, Common.PASSWORD);
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return rs;
    }

}
