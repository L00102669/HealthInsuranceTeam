package db;


	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
	import java.util.Scanner;

import com.mysql.jdbc.Statement;

	public class Driver {

		public static void main(String[] args) throws SQLException {

			String url = "jdbc:mysql://localhost:3306/health";
			String user = "root";
			String password = "password";

			Connection myConn = null;
			PreparedStatement myStmt = null;

			Scanner scanner = null;

			try {
				// 0. read user input from command line: last name, first name and email
				scanner = new Scanner(System.in);
				
				// Current Table
				
				
				System.out.print("Enter ID: ");
				int userid = scanner.nextInt();
				
				System.out.print("Enter your first name: ");
				String firstName = scanner.nextLine();
				
				System.out.print("Enter your last name: ");
				String lastName = scanner.nextLine();

				System.out.print("Enter your age: ");
				int age = scanner.nextInt();

				// 1. Get a connection to database
				myConn = DriverManager.getConnection(url, user, password);

				// 2. Create a statement
				String sql = "insert into details "
						+ " (userid,first_name, last_name, age)" + " values (?,?, ?, ?)";

				myStmt = myConn.prepareStatement(sql);

				// set param values
				myStmt.setInt(1, userid);
				myStmt.setString(2, firstName);
				myStmt.setString(3, lastName);
				myStmt.setInt(4, age);

				// 3. Execute SQL query
				myStmt.executeUpdate();

				System.out.println("Insert complete.");
				System.out.print("Current Table: " + myStmt);
				//myStmt.executeQuery("select * from details");
			

			         ResultSet rs = myStmt.executeQuery
			         ("SELECT * FROM details");
			         System.out.println("userid  name    age");
			         while (rs.next()) {
			            int id = rs.getInt("userid");
			            String first_name = rs.getString("first_name");
			            String last_name = rs.getString("last_name");
			            int age1 = rs.getInt("age");
			            System.out.println(id+"   "+first_name+"    "+last_name +"  " + age);
			            
			         }
			} catch (Exception exc) {
				exc.printStackTrace();
			} finally {
				if (myStmt != null) {
					myStmt.close();
				}

				if (myConn != null) {
					myConn.close();
				}

				if (scanner != null) {
					scanner.close();
				}
			}
		}

	}