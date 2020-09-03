package 회원관리;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class MainFrame extends JFrame {
	private JTabbedPane mainPanel;
	private JoinPanel joinPanel;
	private LoginPanel loginPanel;
	private CheckPanel SelectPanel;
	private DeletePanel DeletePanel;
	private UpdatePanel UpdatePanel;
	
	public MainFrame() {
		joinPanel=new JoinPanel();
		loginPanel =new LoginPanel();
		SelectPanel=new CheckPanel();
		DeletePanel=new DeletePanel();
		UpdatePanel=new UpdatePanel();
		
		mainPanel=new JTabbedPane();
		mainPanel.addTab("JOIN", joinPanel);
		mainPanel.addTab("Login", loginPanel);
		mainPanel.addTab("Select", SelectPanel);
		mainPanel.addTab("Delete", DeletePanel);
		mainPanel.addTab("Update", UpdatePanel);
		
		add(mainPanel,BorderLayout.CENTER);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600,400);
		setVisible(true);
		
		
	}
}
