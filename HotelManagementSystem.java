import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class HotelManagementSystem extends JFrame implements ActionListener{
	
	JLabel bgLabel, l1;
	JButton adminButton, userButton;
	
	public HotelManagementSystem() {
		setTitle("HOTEL MANAGEMENT SYSTEM");
		//setting Frame Location and Size
		setBounds(150, 100, 1000, 450);
		
		//Inserting BGImage
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("./icons/first.jpg"));
		Image i2 = i1.getImage().getScaledInstance(1000, 450, Image.SCALE_DEFAULT);
		i1 = new ImageIcon(i2);
		bgLabel = new JLabel(i1);
		add(bgLabel);
		
		//Placing adminButton on bgLabel
		adminButton = new JButton("ADMIN LOGIN");
		adminButton.setBounds(600, 350, 160, 40);
		adminButton.setBackground(Color.white);
		adminButton.setForeground(Color.black);
		bgLabel.add(adminButton);
		adminButton.addActionListener(this);
		
		//Placing userButton on bgLabel
		userButton = new JButton("USER LOGIN");
		userButton.setBounds(800, 350, 160, 40);
		userButton.setBackground(Color.white);
		userButton.setForeground(Color.black);
		bgLabel.add(userButton);
		userButton.addActionListener(this);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == adminButton) {
			new AdminLogin();
			this.setVisible(false);
		}
		if(e.getSource() == userButton) {
			new UserLogin();
			this.setVisible(false);
		}
	}
	
	public static void main(String [] args) {
		new HotelManagementSystem();
		new ServerConnection();
	}
}