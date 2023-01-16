package com.jdbcjava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class JDBCTest {

	public static void main(String[] args) throws IOException {
		
//		Configuration variable
		JDBCProject databaseObject=null;
		String tableName;
		String userName;
		String password;

//		Input Variables
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Scanner sc = new Scanner(System.in);

//		Connection
		boolean connect = false;

//		Exit Variable
//		String exit = "";

		System.out.println("******* All Copy Right Reserved By Subham Kr Gupta *******");
		System.out.println("Welcome to My JDBC CRUD Operation Program\n\n");

//		Checking Connection
		while (!connect) {
			
			System.out.print("Enter table Name : ");
			tableName = br.readLine();
			
			System.out.print("Enter User Name : ");
			userName = br.readLine();
			
			System.out.print("Enter Password : ");
			password = br.readLine();
			
			databaseObject = new JDBCProject(tableName, userName, password);
			
			connect = databaseObject.CheckConnection();
			
			if(!connect) {
				System.out.println("Sorry Try Again :(");
			}
			
		}
		
//		CRUD Operation
		while(connect) {
			
			try {
				
				System.out.println("Enter 1 for Read Data in Database.");
				System.out.println("Enter 2 for Insert Data in Database.");
				System.out.println("Enter 3 for Update Data in Database.");
				System.out.println("Enter 4 for Remove Data in Database.");
				System.out.println("Enter 5 for update 1 field.");
				System.out.println("Enter 6 for 'exit'.");
				System.out.print("Enter Your Choice : ");
				int n = sc.nextInt();
				
				
				switch(n) {
				case 1:
					System.out.println("\n\n");
					databaseObject.readData();
					System.out.println("\n\n");
					break;
				case 2:
					System.out.println("\n\n");
					databaseObject.insertData();
					System.out.println("\n\n");
					break;
				case 3:
					System.out.println("\n\n");
					databaseObject.updateData();
					System.out.println("\n\n");
					break;
				case 4:
					System.out.println("\n\n");
					databaseObject.deleteData();
					System.out.println("\n\n");
					break;
				case 5:
					System.out.println("\n\n");
					databaseObject.insertDataMyChoice();
					System.out.println("\n\n");
					break;
				case 6:
					System.out.println("**** Thank You For Using Created By 'Subham Kr Gupta' ****");
					connect = false;
					databaseObject.connectClose();
					break;
				default:
					System.out.println("Wrong Choice Try Again");
				}
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
			
		}

	}

}
