package MVC;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Member_List extends JFrame implements MouseListener,ActionListener{

	Vector v;
	Vector cols;
	DefaultTableModel model;
	JTable jTable;
	JScrollPane pane;
	JPanel pbtn;
	JButton btnInsert;
	
	public Member_List()
	{
		super("회원관리 프로그램");
		MemberDAO dao= new MemberDAO();
		v=dao.getMemberList();
		cols=getColumn();
		
		model=new DefaultTableModel(v,cols) {
			public boolean isCellEditable(int row,int column) {
				return false;
			}
		};
		
		jTable = new JTable(model);
		jTable.getTableHeader().setReorderingAllowed(false);
		jTable.getTableHeader().setResizingAllowed(false);
		pane= new JScrollPane(jTable);
		add(pane);
		
		pbtn=new JPanel();
		btnInsert =new JButton("회원가입");
		pbtn.add(btnInsert);
		add(pbtn,BorderLayout.SOUTH);
		
		jTable.addMouseListener(this);
		btnInsert.addActionListener(this);
		
		setSize(600,200);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	private Vector getColumn() {
		// TODO Auto-generated method stub\
		Vector col=new Vector();
		col.add("아이디");
		col.add("비밀번호");
		col.add("이름");
		col.add("전화");
		col.add("주소");
		col.add("생일");
		col.add("직업");
		col.add("성별");
		col.add("이메일");
		col.add("자기소개");
		
		return col;
	}
	
	public void jTableRefresh() {
		MemberDAO dao=new MemberDAO();
		DefaultTableModel model=new DefaultTableModel(dao.getMemberList(),getColumn());
		jTable.setModel(model);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
			if(e.getSource()==btnInsert)
			{
				new MemberProc(this);
			}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int r=jTable.getSelectedRow();
		String id=(String) jTable.getValueAt(r,0);
		MemberProc mem=new MemberProc(id,this);
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
