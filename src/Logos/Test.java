package Logos;

import java.sql.Statement;
import java.util.*;

import org.omg.Messaging.SyncScopeHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {

	static Connection con;

	public static void main(String[] args) throws SQLException {
		String dUrl = "jdbc:mysql://localhost:3306/university?useSSL = false";
		String userName = "root";
		String password = "12345";

		con = DriverManager.getConnection(dUrl, userName, password);
		System.out.println("Connected ? :" + !con.isClosed());

		createTable();

		for (int i = 1; i < 30; i++) {
			addStudents(i);
		}
		selectStudents();

	}

	private static void createTable() throws SQLException {
		String drop = "drop table if exists student;";
		String query = "create table(" + "id int primary key auto_increment," + "full_name varchar(255) not null,"
				+ "city varchar(255) not null," + "age int not null" + ");";
		Statement st = con.createStatement();
		st.execute(drop);
		st.execute(query);
		System.out.println("Table is created");
		st.close();
	}

	public static void addStudents(int i) throws SQLException {
		String query = "insert into student(name , city , age)values (?,?,?)";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, "Vasyl");
		ps.setString(2, "Novoyavorivsk");
		ps.setInt(3, i + 10);
		ps.executeUpdate();
		ps.close();

	}

	public static void selectStudents() throws SQLException {
		String query = "select * from student;";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();

		List<String> students = new ArrayList<>();

		while (rs.next()) {
			students.add("ID :" + rs.getInt("id") + "\t |" + "Full_name :" + rs.getString("full_name") + "\t |"
					+ "City :" + rs.getString("city") + "\t |" + "Age :" + rs.getString("age") + "\t |");
		}
		students.forEach(System.out::println);
		rs.close();
		ps.close();

	}
	
	public static void testMethod() {
		
	}

}