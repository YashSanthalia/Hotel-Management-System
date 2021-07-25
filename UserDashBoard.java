import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import javax.swing.*;

public class UserDashBoard extends JFrame implements ActionListener{
	
	JButton myProfile, cleaningService; 
	String[] details;
	
	public UserDashBoard(String[] data) {
		details = data;
		setTitle("USER DASHBOARD");
		setBounds(0, 0, 1200, 700);
		myProfile = new JButton("MY PROFILE");
		myProfile.setBounds(20, 20, 150, 40);
		add(myProfile);
		myProfile.addActionListener(this);
		
		cleaningService = new JButton("Cleaning Service");
		cleaningService.setBounds(20, 100, 150, 40);
		add(cleaningService);
		cleaningService.addActionListener(this);
		
		setLayout(null);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == myProfile) {
			new MyProfile(details);
		}
		if(e.getSource() == cleaningService) {
			String s = "2#" + details[7] + "#";
			String result = SocketConnection.getRequestOfClient(s);
		}
	}
	
	public static void main(String[] args) {
		
	}
}