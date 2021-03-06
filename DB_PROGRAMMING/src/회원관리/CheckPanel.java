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
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class CheckPanel extends JPanel{
	private JLabel lId;
	private JLabel lPw;
	private JLabel lName;
	private JLabel lEmail;
	
	private JTextField TId;
	private JPasswordField TPw;
	private JTextField TName;
	private JTextField TEmail;
	
	
	private JButton Check;
	private JButton Reset;
	
	
	
	private CheckPanel panel;
	
	public  CheckPanel() {
		panel=this;
		setLayout(new GridLayout(5,2));
		
		lId=new JLabel("ID : ",JLabel.CENTER);
		lPw=new JLabel("Password : ",JLabel.CENTER);
		lName=new JLabel("Name : ",JLabel.CENTER);
		lEmail=new JLabel("Email : ",JLabel.CENTER);
		TId=new JTextField();
		TPw=new JPasswordField();
		TName=new JTextField();
		TEmail=new JTextField();
		
		
		Check=new JButton("Check");
		Reset=new JButton("Reset");
		
		Check.addActionListener(actionListener);
		Reset.addActionListener(actionListener);
		add(lId,JLabel.CENTER);
		add(TId);
		
		
		add(lPw,JLabel.CENTER_ALIGNMENT);
		add(TPw);
		
		add(lName,JLabel.CENTER_ALIGNMENT);
		add(TName);
		
		add(lEmail,JLabel.CENTER_ALIGNMENT);
		add(TEmail);
		
		add(Check,JLabel.CENTER_ALIGNMENT);
		add(Reset);
		
		setSize(400,200);
				
		
	}
	private ActionListener actionListener=new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			Object source=event.getSource();
			if(source==Check)
			{
				Connection connection=null;
				Statement stmt=null;
				ResultSet rs=null;
				try {
					connection=DatabaseManager.getConnection();
					if(connection!=null) {
						stmt=connection.createStatement();
						String sql="Select Name,Email from membar where Id='"
						+TId.getText()+"' and Password= '"+TPw.getText()+"'";
						rs=stmt.executeQuery(sql);
						
						if(rs.next())
						{
							TName.setText(rs.getString("Name"));
							TName.setForeground(Color.BLUE);
							TEmail.setText(rs.getString("Email"));
							TEmail.setForeground(Color.BLUE);
						}
						else
						{
							TName.setText("존재하지 않습니다.");
							TName.setForeground(Color.BLUE);
							TEmail.setText("존재하지 않습니다.");
							TEmail.setForeground(Color.BLUE);
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
	private void reset()
	{
		TId.setText("");
		TPw.setText("");
		TName.setText("");
		TEmail.setText("");
	}
	
}
