package db;
import java.sql.*;
import java.util.Scanner;

public class BasicConnection 
{
	// Working DB Connection !!!!
	public static void main(String[] args) throws SQLException {
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
	
		Scanner scanner = new Scanner( System.in );

		try {
			// 1. Get a connection to database
			 myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/health", "root" , "password");
			// 2. Create a statement
			myStmt = myConn.createStatement();
			
			// 3. Execute SQL query
			myRs = myStmt.executeQuery("select * from details");
			
			// create a Statement from the connection
			Statement statement = myConn.createStatement();
			
			String first_name;
			System.out.print("Enter your first name: ");
			
			first_name =scanner.next( );

			// insert the data
			statement.executeUpdate("INSERT INTO details " + "VALUES (5,first_name, 'O hanlon.', '28')");
			
			
			
			// 4. Process the result set
			while (myRs.next()) {
				System.out.println(myRs.getString("userid")+","+myRs.getString("first_name") + ", " + myRs.getString("last_name")+","+myRs.getString("age"));
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		finally {
			if (myRs != null) {
				myRs.close();
			}
			
			if (myStmt != null) {
				myStmt.close();
			}
			
			if (myConn != null) {
				myConn.close();
			}
		}
	}
}