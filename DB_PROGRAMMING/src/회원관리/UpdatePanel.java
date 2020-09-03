package 회원관리;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class UpdatePanel extends JPanel{
	private JLabel lKind;
	private JLabel lWhere;
	private JLabel Change_value;
	private JLabel  empty;
	private JLabel lId;
	private JLabel lPw;
	private JLabel lName;
	private JLabel lEmail;
	
	
	private JTextField TId;
	private JPasswordField TPw;
	private JTextField TName;
	private JTextField TEmail;
	
	private JTextField CId;
	private JPasswordField CPw;
	private JTextField CName;
	private JTextField CEmail;
	
	
	private JButton Update;
	private JButton Reset;
	
	
	
	private UpdatePanel panel;
	public UpdatePanel()
	{
		panel=this;
		setLayout(new GridLayout(6,3));
		lKind=new JLabel("종류 ",JLabel.CENTER);
		lWhere=new JLabel("조건 ",JLabel.CENTER);
		Change_value=new JLabel("바꿀 값 ",JLabel.CENTER);
		empty=new JLabel();
		
		lId=new JLabel("ID : ",JLabel.CENTER);
		lPw=new JLabel("Password : ",JLabel.CENTER);
		lName=new JLabel("Name : ",JLabel.CENTER);
		lEmail=new JLabel("Email : ",JLabel.CENTER);
		
		TId=new JTextField();
		TPw=new JPasswordField();
		TName=new JTextField();
		TEmail=new JTextField();
		
		CId=new JTextField();
		CPw=new JPasswordField();
		CName=new JTextField();
		CEmail=new JTextField();
		
		
		Update=new JButton("Update");
		Reset=new JButton("Reset");
		
		
		Update.addActionListener(actionListener);
		Reset.addActionListener(actionListener);
		add(lKind,JLabel.CENTER_ALIGNMENT);
		add(lWhere,JLabel.CENTER_ALIGNMENT);
		add(Change_value,JLabel.CENTER_ALIGNMENT);
		
		add(lId,JLabel.CENTER_ALIGNMENT);
		add(TId);
		add(CId);
		
		
		add(lPw,JLabel.CENTER_ALIGNMENT);
		add(TPw);
		add(CPw);
		
		
		add(lName,JLabel.CENTER_ALIGNMENT);
		add(TName);
		add(CName);
		
		add(lEmail,JLabel.CENTER_ALIGNMENT);
		add(TEmail);
		add(CEmail);
		
		add(empty);
		add(Update,JLabel.CENTER_ALIGNMENT);
		add(Reset);
		
		setSize(400,200);
				
	}
	public ActionListener actionListener=new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
						Object source=event.getSource();
						if(source==Update)
						{
							Connection connection=null;
							Statement stmt=null;
							ResultSet rs=null;
							
							try {
								connection=DatabaseManager.getConnection();
								if(connection!=null) {
									stmt=connection.createStatement();
									
									String sql_sel="Select Name,Email from membar where Id='"
											+TId.getText()+"' and Password= '"+TPw.getText()+"'";
									rs=stmt.executeQuery(sql_sel);
									
									
									if(rs.next())
									{
										TName.setText(rs.getString("Name"));
										TName.setForeground(Color.GREEN);
										TEmail.setText(rs.getString("Email"));
										TEmail.setForeground(Color.GREEN);
										String sql_Up="Update  membar set Id='"+CId.getText()+"'"+",Password ='"+CPw.getText()+"'"+", Name='"+
										CName.getText()+"', Email='"+CEmail.getText()+"' where Id='"
												+TId.getText()+"' and Password= '"+TPw.getText()+"'";
										int count=stmt.executeUpdate(sql_Up);
										
										String sql_sel2="Select * from membar where Id='"
												+CId.getText()+"' and Password= '"+CPw.getText()+"'";

										
										rs=stmt.executeQuery(sql_sel2);
										if(rs.next())
										{
											System.out.println("============");
											CId.setText("sadasd");
											CId.setText(rs.getString("Id"));
											CId.setForeground(Color.BLUE);
											CPw.setText("asdasd");
											CPw.setText(rs.getString("Password"));
											CPw.setForeground(Color.BLUE);
											CName.setText("asdasd");
											CName.setText(rs.getString("Name"));
											CName.setForeground(Color.BLUE);
											CEmail.setText("asdasd");
											CEmail.setText(rs.getString("Email"));
											CEmail.setForeground(Color.BLUE);
										}
										
									}
									else
									{
										TName.setText("존재하지 않습니다.");
										TName.setForeground(Color.RED);
										TEmail.setText("존재하지 않습니다.");
										TEmail.setForeground(Color.RED);
										JOptionPane.showMessageDialog(panel, "업데이트할 항목이 존재하지 않습니다.");
									}
								}
							} catch (Exception e) {
								// TODO: handle exception
								e.printStackTrace();
							}
							finally {
								try {
									if(stmt!=null)
									{
										stmt.close();
									}
									if(connection!=null)
									{
										connection.close();
									}
									if(stmt!=null)
									{
										stmt.close();
									}
								} catch (Exception ee) {
									// TODO: handle exception
									ee.printStackTrace();
								}
							}
						}
						else if(source==Reset)
						{
							reset();
						}
						
					
			
		}

		
	};
	private void reset() {
		// TODO Auto-generated method stub
		TId.setText("");
		TPw.setText("");
		TName.setText("");
		TEmail.setText("");
		CId.setText("");
		CPw.setText("");
		CName.setText("");
		CEmail.setText("");
	}
}
