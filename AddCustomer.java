import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class AddCustomer extends JFrame implements ActionListener{
	
	private JTextField idNumField, nameField, ageField, countryField, priceField, bedTypeField, phoneField, depositField;
	private JComboBox idTypeComboBox, allocateRoomNoComboBox, daysComboBox, checkedInComboBox;
	JRadioButton maleRadioButton, femaleRadioButton;
	JButton addButton, cancelButton;
	DataBaseConnection con = new DataBaseConnection();
	
	
	public AddCustomer() {
		setTitle("ADD CUSTOMER");
		JLabel idType, idNumber, name, gender, age, country, allocatedRoomNo, days, price, bedType, phone, checkedIn, deposit;
		idType = new JLabel("ID TYPE");
		idType.setBounds(20, 20, 100, 40);
		add(idType);
		idNumber = new JLabel("ID NUMBER");
		idNumber.setBounds(20, 60, 100, 40);
		add(idNumber);
		name = new JLabel("NAME");
		name.setBounds(20, 100, 100, 40);
		add(name);
		age = new JLabel("AGE");
		age.setBounds(20, 140, 100, 40);
		add(age);
		gender = new JLabel("GENDER");
		gender.setBounds(20, 180, 100, 40);
		add(gender);
		country = new JLabel("COUNTRY");
		country.setBounds(20, 220, 100, 40);
		add(country);
		allocatedRoomNo = new JLabel("ROOM NO");
		allocatedRoomNo.setBounds(20, 260, 100, 40);
		add(allocatedRoomNo);
		days = new JLabel("DAYS");
		days.setBounds(20, 300, 100, 40);
		add(days);
		price = new JLabel("PRICE");
		price.setBounds(20, 340, 100, 40);
		add(price);
		bedType = new JLabel("BED TYPE");
		bedType.setBounds(20, 380, 100, 40);
		add(bedType);
		phone = new JLabel("PHONE NO");
		phone.setBounds(20, 420, 100, 40);
		add(phone);
		checkedIn = new JLabel("CHECKED IN");
		checkedIn.setBounds(20, 460, 100, 40);
		add(checkedIn);
		deposit = new JLabel("DEPOSIT");
		deposit.setBounds(20, 500, 100, 40);
		add(deposit);
		
		
		//Adding Fields
		idTypeComboBox = new JComboBox(new String[] {"Passport", "Aadhar", "Driving License", "PAN"});
		idTypeComboBox.setBounds(140, 20, 200, 30);
		add(idTypeComboBox);
		
		idNumField = new JTextField();
		idNumField.setBounds(140, 60, 200, 30);
		add(idNumField);
		
		nameField = new JTextField();
		nameField.setBounds(140, 100, 200, 30);
		add(nameField);
		
		ageField = new JTextField();
		ageField.setBounds(140, 140, 200, 30);
		add(ageField);
		
		maleRadioButton = new JRadioButton("male");
		maleRadioButton.setBounds(140, 180, 80, 30);
		add(maleRadioButton);
		maleRadioButton.addActionListener(this);
		
		femaleRadioButton = new JRadioButton("female");
		femaleRadioButton.setBounds(220, 180, 100, 30);
		add(femaleRadioButton);
		femaleRadioButton.addActionListener(this);
		
		countryField = new JTextField();
		countryField.setBounds(140, 220, 200, 30);
		add(countryField);
		
		allocateRoomNoComboBox = new JComboBox();
		try { 
			String query = "select roomNo from room_details where avlStatus='Available'";
			con.set = con.statement.executeQuery(query);
			while(con.set.next()) {
				allocateRoomNoComboBox.addItem(con.set.getString("roomNo"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		allocateRoomNoComboBox.setBounds(140, 260, 200, 30);
		add(allocateRoomNoComboBox);
		allocateRoomNoComboBox.addActionListener(this);
		
		daysComboBox = new JComboBox(new Integer[] {1, 2, 3, 4, 5, 6, 7});
		daysComboBox.setBounds(140, 300, 200, 30);
		add(daysComboBox);
		daysComboBox.addActionListener(this);
		
		
		priceField = new JTextField();
		priceField.setBounds(140, 340, 200, 30);
		priceField.setEditable(false);
		add(priceField);

		bedTypeField = new JTextField();
		bedTypeField.setBounds(140, 380, 200, 30);
		bedTypeField.setEditable(false);
		add(bedTypeField);
		
		settingPriceAndBedType();
		
		phoneField = new JTextField();
		phoneField.setBounds(140, 420, 200, 30);
		add(phoneField);
		
		checkedInComboBox = new JComboBox(new String[] {"YES", "NO"});
		checkedInComboBox.setBounds(140, 460, 200, 30);
		add(checkedInComboBox);
		
		depositField = new JTextField();
		depositField.setBounds(140, 500, 200, 30);
		add(depositField);
		
		
		//Adding JButtons
		addButton = new JButton("ADD");
		addButton.setBounds(70, 550, 100, 40);
		addButton.setBackground(Color.black);
		addButton.setForeground(Color.white);
		add(addButton);
		addButton.addActionListener(this);
		
		cancelButton = new JButton("CANCEL");
		cancelButton.setBounds(190, 550, 100, 40);
		cancelButton.setBackground(Color.black);
		cancelButton.setForeground(Color.white);
		add(cancelButton);
		cancelButton.addActionListener(this);
		
		setLayout(null);
		setBounds(100, 10 , 700, 650);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == maleRadioButton) {
			femaleRadioButton.setSelected(false);
		}
		else if(e.getSource() == femaleRadioButton) {
			maleRadioButton.setSelected(false);
		}
		else if(e.getSource() == addButton) {
			String idType = (String)idTypeComboBox.getSelectedItem();
			String idNumber = idNumField.getText();
			String name = nameField.getText();
			String age = ageField.getText();
			String gender="";
			if(maleRadioButton.isSelected()) gender="male";
			else if(femaleRadioButton.isSelected()) gender="female";
			String country = countryField.getText();
			String room = (String)allocateRoomNoComboBox.getSelectedItem();
			String days = String.valueOf((Integer)daysComboBox.getSelectedItem());
			String price = priceField.getText();
			String bedType = bedTypeField.getText();
			String phone = phoneField.getText();
			String checkedIn = (String)checkedInComboBox.getSelectedItem();
			String deposit = depositField.getText();
			
			if(idType.equals("") || idNumber.equals("") || name.equals("") || age.equals("") || gender.equals("") || country.equals("") || 
					room.equals("") || days.equals("") || price.equals("") || bedType.equals("") || phone.equals("") || checkedIn.equals("") || deposit.equals("")) {
				JOptionPane.showMessageDialog(null, "Enter Correct Details");
			}
			else {
				try {
					String query = "insert into customer_details values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
					con.preparedStatement = con.connection.prepareStatement(query);
					con.preparedStatement.setString(1, idType);
					con.preparedStatement.setString(2, idNumber);
					con.preparedStatement.setString(3, name);
					con.preparedStatement.setString(4, age);
					con.preparedStatement.setString(5, gender);
					con.preparedStatement.setString(6, country);
					con.preparedStatement.setString(7, room);
					con.preparedStatement.setString(8, days);
					con.preparedStatement.setString(9, price);
					con.preparedStatement.setString(10, bedType);
					con.preparedStatement.setString(11, checkedIn);
					con.preparedStatement.setString(12, phone);
					con.preparedStatement.setString(13, deposit);
					String dueAmount = String.valueOf(Integer.parseInt(price) - Integer.parseInt(deposit));
					con.preparedStatement.setString(14, dueAmount);
					con.preparedStatement.executeUpdate();
				
					String query1 = "update room_details set avlStatus='Occupied' where roomNo=?;";
					con.preparedStatement = con.connection.prepareStatement(query1);
					con.preparedStatement.setString(1, room);
					con.preparedStatement.executeUpdate();
				
					con.connection.close();
					this.setVisible(false);
					new Reception();
				}catch(Exception ex) {}
			}
		}
		else if(e.getSource() == cancelButton) {
			try {
				con.connection.close();
			}catch(Exception ex) {}
			new Reception();
			this.setVisible(false);
		}
		else if(e.getSource() == allocateRoomNoComboBox || e.getSource() == daysComboBox) {
			settingPriceAndBedType();
		}
	}
	
	public void settingPriceAndBedType() {
		try {
			String room = (String)allocateRoomNoComboBox.getSelectedItem();
			String query = "select price, bedType from room_details where roomNo=?";
			con.preparedStatement = con.connection.prepareStatement(query);
			con.preparedStatement.setString(1, room);
			ResultSet set = con.preparedStatement.executeQuery();
			if(set.next()) {
				priceField.setText( String.valueOf( (Integer.parseInt(set.getString("price")) * (Integer)daysComboBox.getSelectedItem())));
				bedTypeField.setText(set.getString("bedType"));
			}
		}catch(Exception ex) {
			System.out.println("1");
			ex.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new AddCustomer();
	}
}