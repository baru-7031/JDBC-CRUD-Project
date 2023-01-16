/*
 * Hello Everyone this class for Simple JDBC Program i do.
 * In this class i have CRUD method to do 
 * operation with MySQL database using JDBC
 * 
 * */

package com.jdbcjava;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCProject {

//	Configuration Variables
	private final String driver = "com.mysql.cj.jdbc.Driver";
	private String tableName;
	private String userName;
	private String password;
	private Connection con = null;
	private final String url = "jdbc:mysql://localhost:3306/motivity_labs";

//	DataBase Field Variables
	private String eName;
	private String email;
	private String eDesignation;
	private int eSalary;
	private String eDept;

//	Input Methods
	private Scanner sc;
	private BufferedReader br;

//	Initialization Database Name, User Name, and Password
	public JDBCProject(String tableName, String userName, String password) {
		super();
		this.tableName = tableName;
		this.userName = userName;
		this.password = password;
		sc = new Scanner(System.in);
		br = new BufferedReader(new InputStreamReader(System.in));
	}

	public String getDatabaseName() {
		return tableName;
	}

	public void setDatabaseName(String databaseName) {
		this.tableName = databaseName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

//	Update data with your choice column
	public void insertDataMyChoice() {

		
		try {
			
			System.out.println("ename, email, edesignation, esalary, edept");
			System.out.println("This is are the fields you can choice any one");
			System.out.print("Enter your choice : ");
			String fields = br.readLine();
			
			System.out.print("Enter employee id : ");
			int id = sc.nextInt();
			
			String query = "update "+tableName+" set "+fields+"=? where eid="+id;
			PreparedStatement state = con.prepareStatement(query);
			switch(fields) {
			case "ename":
				System.out.print("Enter ename value : ");
				String tempName = br.readLine();
				state.setString(1, tempName);
				break;
			case "email":
				System.out.print("Enter ename value : ");
				String tempEmail = br.readLine();
				state.setString(1, tempEmail);
				break;
			case "edesignation":
				System.out.print("Enter ename value : ");
				String tempDesignation = br.readLine();
				state.setString(1, tempDesignation);
				break;
			case "esalary":
				System.out.print("Enter ename value : ");
				int tempSalary = sc.nextInt();
				state.setInt(1, tempSalary);
				break;
			case "edept":
				System.out.print("Enter ename value : ");
				String tempDept = br.readLine();
				state.setString(1, tempDept);
				break;
			default:
				System.out.println("Sorry Wrong Choice please exter again");
			}
			
			int row = state.executeUpdate();
			System.out.println(row + " Row Effected");
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

//	Get JDBC Connection 
	public boolean CheckConnection() {

		try {
			Class.forName(driver);

			this.con = DriverManager.getConnection(url, userName, password);

			if (this.con != null) {
				System.out.println("Connected to JDBC....");
				return true;
			} else {
				System.out.println("Sorry Connection not Connected...");
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

//	Insert Data
	public void insertData() {

		String query = "INSERT INTO " + this.tableName + " (ename,email,edesignation,esalary,edept) VALUES (?,?,?,?,?)";

		System.out.print("How many times Your want to insert data : ");
		int n = sc.nextInt();

		try {
			Class.forName(driver);

			for (int i = 0; i < n; i++) {

				userInput();

				PreparedStatement statement = con.prepareStatement(query);
				statement.setString(1, this.eName);
				statement.setString(2, this.email);
				statement.setString(3, this.eDesignation);
				statement.setInt(4, this.eSalary);
				statement.setString(5, this.eDept);

				int row = statement.executeUpdate();

				System.out.println("Row Effected " + row);
				
				statement.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

//	User Input Method
	public void userInput() {
		try {

//		Enter Ename		//1
			System.out.print("Enter Name : ");
			this.eName = br.readLine();

//		Enter email		//2
			System.out.print("Enter Email : ");
			this.email = br.readLine();

//		Enter edesignation		//3
			System.out.print("Enter Designation : ");
			this.eDesignation = br.readLine();

//		Enter esalary	//4
			System.out.print("Enter Salary : ");
			this.eSalary = sc.nextInt();

//		Enter edept		//5
			System.out.print("Enter Dept : ");
			this.eDept = br.readLine();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	Update Data Using JDBC
	public void updateData() {

		System.out.print("Enter Id which you want to Update : ");
		int id = sc.nextInt();

		String query = "update " + this.tableName + " set ename=?, "
				+ "email=?, edesignation=?, esalary=?,edept=? where eid=" + id;

		try {
			Class.forName(driver);

			userInput();

			PreparedStatement statement = con.prepareStatement(query);
			statement.setString(1, this.eName);
			statement.setString(2, this.email);
			statement.setString(3, this.eDesignation);
			statement.setInt(4, this.eSalary);
			statement.setString(5, this.eDept);

			int row = statement.executeUpdate();

			System.out.println("Row Effected " + row);

			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

//	Delete Data Using JDBC
	public void deleteData() {

		System.out.print("Enter eid which employee data you want to delete : ");
		int id = sc.nextInt();

		String query = "delete from " + this.tableName + " where eid=?";

		try {

			PreparedStatement statement = this.con.prepareStatement(query);
			statement.setInt(1, id);

			int row = statement.executeUpdate();

			System.out.println("Row Effected " + row);

			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

//	Read Data Using JDBC
	public void readData() {

		String query = "SELECT * FROM " + tableName;

		try {
			Statement state = this.con.createStatement();

			ResultSet result = state.executeQuery(query);

			System.out.println("eid" + "\t" + "ename" + "\t" + "email" + "\t" + "edesignation" + "\t" + "esalary" + "\t"
					+ "edept");
			while (result.next()) {

				System.out.println(result.getInt(1) + "\t" + result.getString(2) + "\t" + result.getString(3) + "\t"
						+ result.getString(4) + "\t" + result.getInt(5) + "\t" + result.getString(6));
			}

			state.close();
			result.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

//	Connection Close
	public void connectClose() {
		try {
			con.close();
			sc.close();
			br.close();
		} catch (Exception e) {

		}

	}

}
