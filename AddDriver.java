import java.awt.event.*;
import javax.swing.*;

public class AddDriver extends JFrame implements ActionListener{
	
	private JButton addButton, cancelButton;
	private JTextField nameField, ageField, carCompanyField, modelField, locationField;
	private JComboBox avlStatusComboBox;
	private JRadioButton maleRadioButton, femaleRadioButton;
	
	public AddDriver() {
		setBounds(400, 100, 700, 420);
		setTitle("ADD DRIVER");
		
		//Adding Labels
		JLabel nameLabel, ageLabel, genderLabel, carCompanyLabel, modeLabel, avlStatusLabel, locationLabel;
		nameLabel= new JLabel("NAME");
		nameLabel.setBounds(20, 20, 100, 40);
		add(nameLabel);
		
		ageLabel= new JLabel("AGE");
		ageLabel.setBounds(20, 60, 100, 40);
		add(ageLabel);
		
		genderLabel = new JLabel("GENDER");
		genderLabel.setBounds(20, 100, 100, 40);
		add(genderLabel);
		
		carCompanyLabel= new JLabel("CAR COMPANY");
		carCompanyLabel.setBounds(20, 140, 100, 40);
		add(carCompanyLabel);
		
		modeLabel= new JLabel("MODEL");
		modeLabel.setBounds(20, 180, 100, 40);
		add(modeLabel);
		
		avlStatusLabel= new JLabel("AVAILABILITY");
		avlStatusLabel.setBounds(20, 220, 100, 40);
		add(avlStatusLabel);
		
		locationLabel= new JLabel("LOCATION");
		locationLabel.setBounds(20, 260, 100, 40);
		add(locationLabel);
		
		
		//Adding TextFields
		nameField = new JTextField();
		nameField.setBounds(120, 20, 150, 30);
		add(nameField);
		
		ageField= new JTextField();
		ageField.setBounds(120, 60, 150, 30);
		add(ageField);
		
		carCompanyField= new JTextField();
		carCompanyField.setBounds(120, 140, 150, 30);
		add(carCompanyField);
		
		modelField= new JTextField();
		modelField.setBounds(120, 180, 150, 30);
		add(modelField);
		
		locationField= new JTextField();
		locationField.setBounds(120, 260, 150, 30);
		add(locationField);
		
		
		//Adding GenderRadioButtons
		
		maleRadioButton = new JRadioButton("male");
		maleRadioButton.setBounds(120, 100, 60, 30);
		add(maleRadioButton);
		maleRadioButton.addActionListener(this);
		
		femaleRadioButton = new JRadioButton("female");
		femaleRadioButton.setBounds(180, 100, 100, 30);
		add(femaleRadioButton);
		femaleRadioButton.addActionListener(this);
		
		
		//Adding JComboBox
		avlStatusComboBox = new JComboBox(new String[] {"YES", "NO"});
		avlStatusComboBox.setBounds(120, 220, 100, 30);
		add(avlStatusComboBox);
		
		//Adding Buttons
		addButton = new JButton("ADD");
		addButton.setBounds(50, 320, 100, 40);
		add(addButton);
		addButton.addActionListener(this);
		
		cancelButton = new JButton("CANCEL");
		cancelButton.setBounds(160, 320, 100, 40);
		add(cancelButton);
		cancelButton.addActionListener(this);
		
		
		
		
		setLayout(null);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == addButton) {
			String name = nameField.getText();
			String age = ageField.getText();
			String carCompany = carCompanyField.getText();
			String model = modelField.getText();
			String location = locationField.getText();
			String avlStatus = (String)avlStatusComboBox.getSelectedItem();
			String gender = "";
			if(maleRadioButton.isSelected()) gender="male";
			else if(femaleRadioButton.isSelected()) gender ="female";
			if(name.equals("") || age.equals("") || carCompany.equals("") || model.equals("") || location.equals("") || avlStatus.equals("") || gender.equals("")) {
				JOptionPane.showMessageDialog(null, "Please Enter Correct Details");
			}
			else {
				try {
					DataBaseConnection con = new DataBaseConnection();
					String query="insert into driver_details values(?, ?, ?, ?, ?, ?, ?)";
					con.preparedStatement = con.connection.prepareStatement(query);
					con.preparedStatement.setString(1, name);
					con.preparedStatement.setString(2, age);
					con.preparedStatement.setString(4, carCompany);
					con.preparedStatement.setString(5, model);
					con.preparedStatement.setString(6, location);
					con.preparedStatement.setString(7, avlStatus);
					con.preparedStatement.setString(3, gender);
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
		else if(e.getSource() == maleRadioButton) {
			femaleRadioButton.setSelected(false);
		}
		else if(e.getSource() == femaleRadioButton) {
			maleRadioButton.setSelected(false);
		}
	}
	
	public static void main(String[] args) {
		new AddDriver();
	}
}
