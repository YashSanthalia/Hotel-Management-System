import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.Socket;

public class UserLogin extends JFrame implements ActionListener{
	
	private JLabel idNumberLabel, nameLabel, imageLabel;
	private JTextField idNumberField, nameField;
	private JButton loginButton, cancelButton;
	public UserLogin() {
		setTitle("USER LOGIN");
		setBounds(400, 200, 500, 250);
		setLayout(null);
		
		//Placing UserName and Password Labels
		idNumberLabel = new JLabel("Id Number");
		idNumberLabel.setBounds(40, 30, 100, 40);
		add(idNumberLabel);
		
		nameLabel = new JLabel("Name");
		nameLabel.setBounds(40, 70, 100, 40);
		add(nameLabel);
	
		//Placing UserNameField and Password Fields
		idNumberField = new JTextField();
		idNumberField.setBounds(140, 30, 150, 30);
		idNumberField.setFont(new Font("SAN_SERIF", Font.PLAIN, 15));
		add(idNumberField);
		
		nameField = new JTextField();
		nameField.setBounds(140, 70, 150, 30);
		nameField.setFont(new Font("SAN_SERIF", Font.PLAIN, 15));
		add(nameField);
		
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
			String idNumber = idNumberField.getText();
			String name = nameField.getText();
			if(idNumber.equals("") || name.equals("")) {
				JOptionPane.showMessageDialog(null, "Enter Correct Details");
			}
			else {
				String s = "1#" + idNumber + "#" + name + "#";
				String result = SocketConnection.getRequestOfClient(s);
				String[] data = result.split("#");
				System.out.println("result" + result);
				if(data[0].equals("OK")) {
					new UserDashBoard(data);
					this.setVisible(false);
				}
				else {
					JOptionPane.showMessageDialog(null, "Enter Correct Details");
				}
			}
		}
		if(e.getSource() == cancelButton) {
			new HotelManagementSystem();
			this.setVisible(false);
		}
	}
	
	public static void main(String[] args) {
		new UserLogin();
	}
	
}
