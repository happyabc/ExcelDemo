package com.happyabc.client;

import com.happyabc.excel.SaveData2DB;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author happyabc
 * @created 2018-7-22
 */
public class Client {

	public static void main(String[] args) throws IOException, SQLException {
		SaveData2DB saveData2DB = new SaveData2DB();
		saveData2DB.save();
		System.out.println("end");
	}
}
