package SCORE;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

public class DatabaseManager {
	private static String className = "org.mariadb.jdbc.Driver";
	private static String url = "jdbc:mariadb://127.0.0.1:3306/MyDB";
	private static String ID = "root";
	private static String PW = "xxxx";

	static {
		try {
			Class.forName(className);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
//	DB연결위한 함수
	public static Connection getConn() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(url,ID,PW);
		} catch (Exception e) {
			e.printStackTrace();

		}

		return con;
	}
	
	//insert 위한 쿼리문.
	public int insertScore(Score score) {
		Connection con = null;
		Statement stmt = null;
		int result = 0;
		try {
			con = getConn();
			stmt = con.createStatement();
			String sql = "Insert into score values('" + score.getName() + "'," + score.getKor() + "," + score.getEng()
					+ "," + score.getMath() + "," + score.getTotal() + "," + score.getAverage() + ")";
			result = stmt.executeUpdate(sql);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return result;
	}
	public int deleteScore(Score score) {
		Connection con = null;
		Statement stmt = null;
		int result = 0;
		try {
			con = getConn();
			stmt = con.createStatement();
			String sql = "delete from Score where Name= '"+score.getName()+"'";
			result = stmt.executeUpdate(sql);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return result;
	}
	public int updateScore(Score score) {
		Connection con = null;
		Statement stmt = null;
		int result = 0;
		try {
			con = getConn();
			stmt = con.createStatement();
			String sql = "update score set kor="+score.getKor()
					+ ", eng ="+score.getEng()
					+", math ="+score.getMath()
					+", total ="+score.getTotal()
					+", average ="+score.getAverage()
					+" where Name= '"+score.getName()+"'";
			result = stmt.executeUpdate(sql);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return result;
	}
	// data type 상관없이 읽기위한 Vector 함수.
	@SuppressWarnings("rawtypes")
	public Vector getScore() {
		Vector data = new Vector();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = getConn();
			String sql = "select dense_rank() over(order by total desc) as 등수,name,kor,eng,math,total,average from score";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				
				int rank=rs.getInt("등수");
				
				String name = rs.getString("name");
				int kor = rs.getInt("kor");
				int eng = rs.getInt("eng");
				int math = rs.getInt("math");
				int total = rs.getInt("total");
				int average = rs.getInt("average");
				Vector row = new Vector();
				row.add(rank);
				row.add(name);
				row.add(kor);
				row.add(eng);
				row.add(math);
				row.add(total);
				row.add(average);
				data.add(row);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		} finally {
			if (rs != null)
				try {
					if (rs != null)
						rs.close();
					if (stmt != null)
						stmt.close();
					if (con != null)
						con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return data;

	}

}
