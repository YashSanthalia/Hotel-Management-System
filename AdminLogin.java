import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class AdminLogin extends JFrame implements ActionListener{
	
	JLabel userNameLabel, passwordLabel, imageLabel;
	JTextField userNameField;
	JPasswordField passwordField;
	JButton loginButton, cancelButton;
	
	public AdminLogin() {
		setTitle("ADMIN LOGIN");
		setBounds(400, 200, 500, 250);
		setLayout(null);
		
		//Placing UserName and Password Labels
		userNameLabel = new JLabel("Username");
		userNameLabel.setBounds(40, 30, 100, 40);
		add(userNameLabel);
		
		passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(40, 70, 100, 40);
		add(passwordLabel);
	
		//Placing UserNameField and Password Fields
		userNameField = new JTextField();
		userNameField.setBounds(140, 30, 150, 30);
		userNameField.setFont(new Font("SAN_SERIF", Font.PLAIN, 15));
		add(userNameField);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(140, 70, 150, 30);
		passwordField.setFont(new Font("SAN_SERIF", Font.PLAIN, 15));
		add(passwordField);
		
		//Placing Buttons
		loginButton = new JButton("LOGIN");
		loginButton.setBackground(Color.white);
		loginButton.setForeground(Color.black);
		loginButton.setBounds(40, 140, 100, 40);
		add(loginButton);
		loginButton.addActionListener(this);
		
		cancelButton = new JButton("CANCEL");
		cancelButton.setBackground(Color.white);
		cancelButton.setForeground(Color.black);
		cancelButton.setBounds(160, 140, 100, 40);
		add(cancelButton);
		cancelButton.addActionListener(this);
		
		//Placing Image
		ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("./icons/second.jpg"));
		Image image = icon.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
		icon = new ImageIcon(image);
		imageLabel = new JLabel(icon);
		imageLabel.setBounds(300, 0, 200, 200);
		add(imageLabel);
		
		getContentPane().setBackground(Color.white);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == loginButton) {
			String userName = userNameField.getText();
			char[] pass = passwordField.getPassword();
			String password = new String(pass);
			DataBaseConnection con = new DataBaseConnection();
			String query = "select * from adminLogin where userName=? and password=?;";
			try {
				con.preparedStatement = con.connection.prepareStatement(query);
				con.preparedStatement.setString(1, userName);
				con.preparedStatement.setString(2, password);
				con.set = con.preparedStatement.executeQuery();
				if(con.set.next()) {
					this.setVisible(false);
					new AdminDashBoard();
				}
				else {
					JOptionPane.showMessageDialog(null, "Incorrect Details");
					userNameField.setText("");
					passwordField.setText("");
				}
				con.connection.close();
			}catch(Exception ex) {
				ex.printStackTrace();
			}
			
		}
		if(e.getSource() == cancelButton) {
			new HotelManagementSystem();
			this.setVisible(false);
		}
	}
	
	public static void main(String[] args) {
		new AdminLogin();
	}
	
}
