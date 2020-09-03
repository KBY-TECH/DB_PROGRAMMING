package BASIC_CONNECTING;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
	public static void main(String args[]) {
		String driver = "org.mariadb.jdbc.Driver";
		String url = "jdbc:mariadb://127.0.0.1:3306/mydb";
		String user = "root";
		String pwd = "xxxx";
		Connection conn;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, pwd);
			System.out.println("======================");
			System.out.println("JDBC Load & Connected");
			System.out.println("Good");
			System.out.println("======================");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from book");
			while (rs.next()) {
				System.out.println("name: " + rs.getString("bookname"));
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException | SQLException exception) {
			// TODO: handle exception
			exception.printStackTrace();
		}
	}
}
