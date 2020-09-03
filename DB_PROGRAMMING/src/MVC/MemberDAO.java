package MVC;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.JOptionPane;

/*
 * 데이터 베이스 접근 위한 클래스.
 * ---특징---
 * 데이터에 접근 조작하기 위한 객체
 * SQL 처리 등을 담당.
 * 
*/
@SuppressWarnings("rawtypes")
public class MemberDAO {

	private static final String DRIVER = "org.mariadb.jdbc.Driver";
	private static final String URL = "jdbc:mariadb://127.0.0.1:3306/MyDB";
	private static final String PASS = "xxxx";
	private static final String USER = "root";

	Member_List mList;

	// 데이터베이스 접근
	public Connection getConn() {
		Connection con = null;
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASS);
//			System.out.println("===DB연결===");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return con;
	}
	// 해당 정보를 입력받기

	public MemberDTO getMemberDTO(String id) {
		MemberDTO dto = new MemberDTO();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			// 접근하고 질의 처리하고
			con = getConn();
			String sql = "SELECT * FROM tb_member WHERE id=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);

			rs = ps.executeQuery();

			if (rs.next()) {
				dto.setId(rs.getString("id"));
				dto.setPwd(rs.getString("Pwd"));
				dto.setName(rs.getString("Name"));
				dto.setTel(rs.getString("tel"));
				dto.setAddr(rs.getString("addr"));
				dto.setBirth(rs.getString("birth"));
				dto.setJob(rs.getString("job"));
				dto.setGender(rs.getString("gender"));
				dto.setEmail(rs.getString("email"));
				dto.setIntro(rs.getString("intro"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dto;

	}

	// 화면 ui 수정하여 작성하시오.
	public Vector getMemberList() {
		Vector data = new Vector();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = getConn();
			String sql = "select * from tb_member";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String tel = rs.getString("tel");

				String addr = rs.getString("addr");
				String birth = rs.getString("birth");
				String job = rs.getString("job");
				String gender = rs.getString("gender");
				String email = rs.getString("email");
				String intro = rs.getString("intro");

				Vector row = new Vector();

				row.add(id);
				row.add(pwd);
				row.add(name);
				row.add(tel);
				row.add(addr);
				row.add(birth);
				row.add(job);
				row.add(gender);
				row.add(email);
				row.add(intro);

				data.add(row);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return data;
	}

	// 수정 요망.. 한글이 안들어 가집니다...
	public boolean insertMember(MemberDTO dto) {
		boolean ok = false;

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = getConn();
			String sql = "Insert into tb_member(ID,PWD,NAME,TEL,ADDR,BIRTH,JOB,GENDER,EMAIL,INTRO) values(?,?,?,?,?,?,?,?,?,?)";

			ps = con.prepareStatement(sql);

			ps.setString(1, dto.getId());
			ps.setString(2, dto.getPwd());
			ps.setString(3, dto.getName());
			ps.setString(4, dto.getTel());
			ps.setString(5, dto.getAddr());
			ps.setString(6, dto.getBirth());
			ps.setString(7, dto.getJob());
			ps.setString(8, dto.getGender());
			ps.setString(9, dto.getEmail());
			ps.setString(10, dto.getIntro());

			int r = ps.executeUpdate();

			if (r > 0)
				ok = true;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return ok;
	}

	// 수정. 어떻게 수정해야 하는지 강의영상 다시 시청.
	public boolean updateMember(MemberDTO vMem) {
		boolean ok = false;
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = getConn();
//			String sql = "update tb_member  set name=?, tel=? where id=? AND pwd=?";
			String sql = "update tb_member  set name=?, tel=? ,ADDR=?,BIRTH=?,"
					+ "JOB=?, GENDER=?, EMAIL=?, INTRO=? where id=? AND pwd=?";
			
			ps = con.prepareStatement(sql);

			ps.setString(1, vMem.getName());
			ps.setString(2, vMem.getTel());
			ps.setString(3, vMem.getAddr());
			ps.setString(4, vMem.getBirth());
			ps.setString(5, vMem.getJob());
			ps.setString(6, vMem.getGender());
			ps.setString(7, vMem.getEmail());
			ps.setString(8, vMem.getIntro());
			ps.setString(9, vMem.getId()); // ID는 고정
			ps.setString(10, vMem.getPwd()); // PW 를 치고

			
			int r = ps.executeUpdate();

			if (r > 0) {
				ok = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ok;
	}

	public boolean deleteMember(String id, String pwd) {
		boolean ok = false;
		Connection con = null;
		PreparedStatement ps = null;

		try {

			con = getConn();
			String sql = "delete from tb_member where id=? AND pwd=? ";

			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pwd);

			int r = ps.executeUpdate();

			if (r > 0)
				ok = true;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return ok;
	}
}
