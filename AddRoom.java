import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AddRoom extends JFrame implements ActionListener{
	
	private JTextField roomNoField, priceField;
	private JComboBox availabilityStatusComboBox, cleaningStatusComboBox, bedTypeComboBox;
	private JButton addButton, cancelButton;
	
	public AddRoom() {
		setTitle("ADD ROOM");
		setBounds(400, 100, 700, 370);
		JLabel roomNoLabel, availabilityLabel, cleaningStatusLabel, priceLabel, bedTypeLabel;
	
		
		//Adding Labels
		roomNoLabel = new JLabel("ROOM NUMBER");
		roomNoLabel.setBounds(20, 20, 100, 40);
		add(roomNoLabel);
		availabilityLabel = new JLabel("AVAILABILITY");
		availabilityLabel.setBounds(20, 60, 100, 40);
		add(availabilityLabel);
		cleaningStatusLabel = new JLabel("CLEANING STATUS");
		cleaningStatusLabel.setBounds(20, 100, 120, 40);
		add(cleaningStatusLabel);
		bedTypeLabel = new JLabel("BED TYPE");
		bedTypeLabel.setBounds(20, 140, 100, 40);
		add(bedTypeLabel);
		priceLabel = new JLabel("PRICE");
		priceLabel.setBounds(20, 180, 100, 40);
		add(priceLabel);
		
		//Adding TextFields
		roomNoField = new JTextField();
		roomNoField.setBounds(150, 20, 150, 30);
		add(roomNoField);
		priceField = new JTextField();
		priceField.setBounds(150, 180, 150, 30);
		add(priceField);
		
		//Adding ComboBox
		availabilityStatusComboBox = new JComboBox(new String[] {"Available", "Occupied"});
		availabilityStatusComboBox.setBounds(150, 60, 150, 30);
		add(availabilityStatusComboBox);
		
		cleaningStatusComboBox = new JComboBox(new String[] {"Cleaned", "Occupied"});
		cleaningStatusComboBox.setBounds(150, 100, 150, 30);
		add(cleaningStatusComboBox);
		
		bedTypeComboBox = new JComboBox(new String[] {"Single", "Double"});
		bedTypeComboBox.setBounds(150, 140, 150, 30);
		add(bedTypeComboBox);
		
		
		//Adding Buttons
		addButton = new JButton("ADD");
		addButton.setBounds(50, 240, 100, 40);
		add(addButton);
		addButton.addActionListener(this);
		
		cancelButton = new JButton("CANCEL");
		cancelButton.setBounds(170, 240, 100, 40);
		add(cancelButton);
		cancelButton.addActionListener(this);
		
		
		
		
		setLayout(null);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == addButton) {
			String roomNo = roomNoField.getText();
			String availabilityStatus = (String)availabilityStatusComboBox.getSelectedItem();
			String cleaningStatus = (String)cleaningStatusComboBox.getSelectedItem();
			String price = priceField.getText();
			String bedType = (String)bedTypeComboBox.getSelectedItem();
			if(roomNo.equals("") || availabilityStatus.equals("") || cleaningStatus.equals("") || price.equals("") || bedType.equals("")) {
				JOptionPane.showMessageDialog(null, "Enter Correct Details");
			}
			else {
				try {
					DataBaseConnection con = new DataBaseConnection();
					String query = "insert into room_details values(?, ?, ?, ?, ?)";
					con.preparedStatement = con.connection.prepareStatement(query);
					con.preparedStatement.setString(1, roomNo);
					con.preparedStatement.setString(2, availabilityStatus);
					con.preparedStatement.setString(3, cleaningStatus);
					con.preparedStatement.setString(4, price);
					con.preparedStatement.setString(5, bedType);
					con.preparedStatement.executeUpdate();
					con.connection.close();
					this.setVisible(false);
				}catch(Exception ex) {
					ex.printStackTrace();
				}
			}
 		}
		else if(e.getSource() == cancelButton) {
			this.setVisible(false);
		}
	}
	
//	public static void main(String[] args) {
//		new AddRoom();
//	}
}
