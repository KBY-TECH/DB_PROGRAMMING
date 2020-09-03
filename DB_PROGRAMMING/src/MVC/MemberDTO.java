package MVC;
/* ==> 데이터베이스 접근을 위한 회원 한명의 정보 저장 용도.
 * Data Transfer Object
 * ----특징-----
 *	 데이터 교환을 위한 객체
	Model에 접근 이용
	특별 로직 없이 데이터 교환을 위한 get/set 메소드만 있음.
*/
public class MemberDTO {
	private String id; 
	private String pwd;
	private String name;
	private String tel;
	private String addr;
	private String birth;
	private String job;
	private String gender;
	private String email;
	private String intro;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPwd() {
		return pwd;
	}
	
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getTel() {
		return tel;
	}
	
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public String getAddr() {
		return addr;
	}
	
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	public String getJob() {
		return job;
	}
	
	public void setJob(String job) {
		this.job = job;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}
	
}
