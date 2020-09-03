package 회원관리;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class JoinPanel extends JPanel {
	private JLabel lbId;
	private JLabel lbPassword;
	private JLabel lbName;
	private JLabel lbEmail;

	private JTextField tfId;
	private JPasswordField tfPassword;
	private JTextField tfName;
	private JTextField tfEmail;

	private JButton btJoin;
	private JButton btReset;

	private JoinPanel panel;

	public JoinPanel() {
		panel = this;
		setLayout(new GridLayout(5, 2));

		lbId = new JLabel(" ID : ", JLabel.CENTER);
		lbPassword = new JLabel(" Password : ", JLabel.CENTER);
		lbName = new JLabel(" Name : ", JLabel.CENTER);
		lbEmail = new JLabel(" E-mail : ", JLabel.CENTER);

		tfId = new JTextField();
		tfPassword = new JPasswordField();
		tfName = new JTextField();
		tfEmail = new JTextField();

		btJoin = new JButton("Join");
		btReset = new JButton("Reset");

		btJoin.addActionListener(actionListener);
		btReset.addActionListener(actionListener);
		add(lbId, JLabel.CENTER);
		add(tfId);
		
		add(lbPassword, JLabel.CENTER_ALIGNMENT);
		add(tfPassword);
		
		
		add(lbName, JLabel.CENTER_ALIGNMENT);
		add(tfName);
		
		
		add(lbEmail, JLabel.CENTER_ALIGNMENT);
		add(tfEmail);
		
		add(btJoin, JLabel.CENTER_ALIGNMENT);
		add(btReset);

		setSize(400, 200);

	}

	private ActionListener actionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent event) {
			Object source = event.getSource();
			if (source == btJoin) {
				Connection connection = null;
				Statement stmt = null;

				try {
					connection = DatabaseManager.getConnection();
					if (connection != null) {
						stmt = connection.createStatement();
						String sql = "Insert into membar values('" + tfId.getText() + "','" + tfPassword.getText()
								+ "','" + tfName.getText() + "','" + tfEmail.getText() + "')";
						int count = stmt.executeUpdate(sql);

						if (count == 1) {
							JOptionPane.showMessageDialog(panel, "JOIN SUCEESS");
						} else
							JOptionPane.showMessageDialog(panel, "JOIN FAIL");

						reset();

					}

				} catch (SQLException e) {
					// TODO: handle exception
					e.printStackTrace();
				} finally {
					try {
						if (stmt != null)
							stmt.close();
						if (connection != null)
							connection.close();
					} catch (Exception ee) {
						// TODO: handle exception
						ee.printStackTrace();
					}
				}
			} else if (source == btReset) {
				reset();
			}
		}

		

		

	};

	private void reset() {
		tfId.setText("");
		tfPassword.setText("");
		tfName.setText("");
		tfEmail.setText("");
	}
}
