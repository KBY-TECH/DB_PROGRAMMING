package 회원관리;
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseManager {
	private static String className="org.mariadb.jdbc.Driver";
	private static String url="jdbc:mariadb://127.0.0.1:3306/MyDB";
	private static String ID="root";
	private static String PW="xxxx";
	static {
		try {
			Class.forName(className);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	


	public static  Connection getConnection() {
		// TODO Auto-generated method stub
		Connection connection=null;
		try {
			connection=DriverManager.getConnection(url,ID,PW);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return connection;
	}

}
