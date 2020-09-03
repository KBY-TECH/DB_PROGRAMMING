package MVC;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings({ "serial", "rawtypes", "unused", "unchecked", "deprecation" })
public class MemberProc extends JFrame implements ActionListener {
	JPanel p;
	JTextField tfId, tfName, tfAddr, tfEmail;
	JTextField tfTel1, tfTel2, tfTel3;
	JComboBox cbJob;
	JPasswordField pfPwd;
	JTextField tfYear, tfMonth, tfDate;
	JRadioButton rbMan, rbWoman;
	JTextArea taIntro;
	JButton btnInsert, btnCancel, btnUpdate, btnDelete;

	GridBagLayout gb;
	GridBagConstraints gbc;
	Member_List mList;

	public MemberProc() {
		createUI();
		btnUpdate.setEnabled(false);
		btnUpdate.setVisible(false);
		btnDelete.setEnabled(false);
		btnDelete.setVisible(false);
	}

	public MemberProc(Member_List mList) {
		createUI();
		btnUpdate.setEnabled(false);
		btnUpdate.setVisible(false);
		btnDelete.setEnabled(false);
		btnDelete.setVisible(false);
		this.mList = mList;
	}

	public MemberProc(String id, Member_List mList) {
		createUI();
		btnInsert.setEnabled(false);
		btnInsert.setVisible(false);
		this.mList = mList;

		MemberDAO dao = new MemberDAO();
		MemberDTO vMem = dao.getMemberDTO(id);
		viewData(vMem);
	}

	// MemberDTO의 회원 정보를 가지고 화면에 셋팅해주는 메소드
	private void viewData(MemberDTO vMem) {
		String id = vMem.getId();
		String pwd = vMem.getPwd();
		String name = vMem.getName();
		String tel = vMem.getTel();
		String addr = vMem.getAddr();
		String birth = vMem.getBirth();
		String job = vMem.getJob();
		String gender = vMem.getGender();
		String email = vMem.getEmail();
		String intro = vMem.getIntro();

		tfId.setText(id);
		tfId.setEditable(false); // id 편집 불가
		pfPwd.setText("");
		tfName.setText(name);
		String[] tels = tel.split("-");
		tfTel1.setText(tels[0]);
		tfTel2.setText(tels[1]);
		tfTel3.setText(tels[2]);
		tfAddr.setText(addr);
		
		try{
			tfYear.setText(birth.substring(0, 4));
			tfMonth.setText(birth.substring(4, 6));
			tfDate.setText(birth.substring(6, 8));
		}catch (Exception e) {
			System.out.println("error");
		}
		
		cbJob.setSelectedItem(job);

		if (gender.equals("M")) {
			rbMan.setSelected(true);
		} else if (gender.equals("W")) {
			rbWoman.setSelected(true);
		}

		tfEmail.setText(email);
		taIntro.setText(intro);
	}

	private void createUI() {
		this.setTitle("회원정보");
		gb = new GridBagLayout();
		setLayout(gb);
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;

		JLabel bId = new JLabel("아이디 : ");
		tfId = new JTextField(20);
		gbAdd(bId, 0, 0, 1, 1);
		gbAdd(tfId, 1, 0, 3, 1);

		JLabel bPwd = new JLabel("비밀번호 : ");
		pfPwd = new JPasswordField(20);
		gbAdd(bPwd, 0, 1, 1, 1);
		gbAdd(pfPwd, 1, 1, 3, 1);

		JLabel bName = new JLabel("이름 :");
		tfName = new JTextField(20);
		gbAdd(bName, 0, 2, 1, 1);
		gbAdd(tfName, 1, 2, 3, 1);

		JLabel bTel = new JLabel("전화 :");
		JPanel pTel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		tfTel1 = new JTextField(3);
		tfTel2 = new JTextField(4);
		tfTel3 = new JTextField(4);
		pTel.add(tfTel1);
		pTel.add(new JLabel(" - "));
		pTel.add(tfTel2);
		pTel.add(new JLabel(" - "));
		pTel.add(tfTel3);
		gbAdd(bTel, 0, 3, 1, 1);
		gbAdd(pTel, 1, 3, 3, 1);

		JLabel bAddr = new JLabel("주소: ");
		tfAddr = new JTextField(20);
		gbAdd(bAddr, 0, 4, 1, 1);
		gbAdd(tfAddr, 1, 4, 3, 1);

		JLabel bBirth = new JLabel("생일: ");
		tfYear = new JTextField(6);
		tfMonth = new JTextField(6);
		tfDate = new JTextField(6);
		JPanel pBirth = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pBirth.add(tfYear);
		pBirth.add(new JLabel("/"));
		pBirth.add(tfMonth);
		pBirth.add(new JLabel("/"));
		pBirth.add(tfDate);
		gbAdd(bBirth, 0, 5, 1, 1);
		gbAdd(pBirth, 1, 5, 3, 1);

		JLabel bJob = new JLabel("직업 : ");
		String[] arrJob = { "---", "Student", "Office Worker", "Other" };
		cbJob = new JComboBox(arrJob);
		JPanel pJob = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pJob.add(cbJob);
		gbAdd(bJob, 0, 6, 1, 1);
		gbAdd(pJob, 1, 6, 3, 1);

		JLabel bGender = new JLabel("성별 : ");
		JPanel pGender = new JPanel(new FlowLayout(FlowLayout.LEFT));
		rbMan = new JRadioButton("남", true);
		rbWoman = new JRadioButton("여", true);
		ButtonGroup group = new ButtonGroup();
		group.add(rbMan);
		group.add(rbWoman);
		pGender.add(rbMan);
		pGender.add(rbWoman);
		gbAdd(bGender, 0, 7, 1, 1);
		gbAdd(pGender, 1, 7, 3, 1);

		JLabel bEmail = new JLabel("이메일 : ");
		tfEmail = new JTextField(20);
		gbAdd(bEmail, 0, 8, 1, 1);
		gbAdd(tfEmail, 1, 8, 3, 1);

		JLabel bIntro = new JLabel("자기 소개: ");
		taIntro = new JTextArea(5, 20);
		JScrollPane pane = new JScrollPane(taIntro);
		gbAdd(bIntro, 0, 9, 1, 1);
		gbAdd(pane, 1, 9, 3, 1);

		JPanel pButton = new JPanel();
		btnInsert = new JButton("가입");
		btnUpdate = new JButton("수정");
		btnDelete = new JButton("탈퇴");
		btnCancel = new JButton("취소");
		pButton.add(btnInsert);
		pButton.add(btnUpdate);
		pButton.add(btnDelete);
		pButton.add(btnCancel);
		gbAdd(pButton, 0, 10, 4, 1);

		btnInsert.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnCancel.addActionListener(this);
		btnDelete.addActionListener(this);

		setSize(350, 500);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	// 그리드백레이아웃에 붙이는 메소드
	private void gbAdd(JComponent c, int x, int y, int w, int h) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		gb.setConstraints(c, gbc);
		gbc.insets = new Insets(2, 2, 2, 2);
		add(c, gbc);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == btnInsert) {
			insertMember();
		} else if (ae.getSource() == btnCancel) {
			this.dispose(); // 창닫기 (현재창만 닫힘)
		} else if (ae.getSource() == btnUpdate) {
			UpdateMember();
		} else if (ae.getSource() == btnDelete) {
			int x = JOptionPane.showConfirmDialog(this, "정말 삭제하시겠습니까?", "삭제", JOptionPane.YES_NO_OPTION);

			if (x == JOptionPane.OK_OPTION) {
				deleteMember();
			} else {
				JOptionPane.showMessageDialog(this, "삭제를 취소하였습니다.");
			}
		}

		mList.jTableRefresh();
	}

	private void deleteMember() {
		String id = tfId.getText();
		String pwd = pfPwd.getText();
		if (pwd.length() == 0) {
			JOptionPane.showMessageDialog(this, "비밀번호를 꼭 입력하세요!");
			return;
		}
		MemberDAO dao = new MemberDAO();
		boolean ok = dao.deleteMember(id, pwd);

		if (ok) {
			JOptionPane.showMessageDialog(this, "삭제완료");
			dispose(); // 창닫기 (현재창만 닫힘)
		} else {
			JOptionPane.showMessageDialog(this, "삭제실패");
		}
	}

	private void UpdateMember() {
		MemberDTO dto = getViewData();
		MemberDAO dao = new MemberDAO();
		boolean ok = dao.updateMember(dto);

		if (ok) {
			JOptionPane.showMessageDialog(this, "수정되었습니다.");
			this.dispose(); // 창닫기 (현재창만 닫힘)
		} else {
			JOptionPane.showMessageDialog(this, "수정실패: 값을 확인하세요");
		}
	}

	private void insertMember() {
		MemberDTO dto = getViewData();
		MemberDAO dao = new MemberDAO();
		boolean ok = dao.insertMember(dto);

		if (ok) {
			JOptionPane.showMessageDialog(this, "가입이 완료되었습니다.");
			dispose(); // 창닫기 (현재창만 닫힘)
		} else {
			JOptionPane.showMessageDialog(this, "가입이 정상적으로 처리되지 않았습니다.");
		}
	}

	public MemberDTO getViewData() {
		// 화면에서 사용자가 입력한 내용을 얻는다.
		MemberDTO dto = new MemberDTO();
		String id = tfId.getText();
		String pwd = pfPwd.getText();
		String name = tfName.getText();
		String tel1 = tfTel1.getText();
		String tel2 = tfTel2.getText();
		String tel3 = tfTel3.getText();
		String tel = tel1 + "-" + tel2 + "-" + tel3;
		String addr = tfAddr.getText();
		String birth1 = tfYear.getText();
		String birth2 = tfMonth.getText();
		String birth3 = tfDate.getText();
		String birth = birth1 + birth2 + birth3;
		String job =  (String)cbJob.getSelectedItem().toString();
		
		String gender = "";
		
		if (rbMan.isSelected()) {
			gender = "M";
		} else if (rbWoman.isSelected()) {
			gender = "W";
		}

		String email = tfEmail.getText();
		String intro = taIntro.getText();

		// DTO에 담는다.
		dto.setId(id);
		dto.setPwd(pwd);
		dto.setName(name);
		dto.setTel(tel);
		dto.setAddr(addr);
		dto.setBirth(birth);
		dto.setJob(job);
		dto.setGender(gender);
		dto.setEmail(email);
		dto.setIntro(intro);

		return dto;
	}
}

