import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class AddEmployee extends JFrame implements ActionListener{
	
	JButton submitButton, cancelButton;
	JTextField nameField, ageField, salaryField, phoneField, aadharField, emailField; 
	JRadioButton maleButton, femaleButton;
	JComboBox jobBox;
	
	
	public AddEmployee(){
		setTitle("ADD EMPLOYEE");
		setBounds(400, 100, 500, 460);
		
		//Adding JLabels
		JLabel name, age, gender, jobTitle, salary, phone, aadhar, emailId;
		name = new JLabel("NAME");
		name.setBounds(30, 20, 100, 40);
		add(name);
		age = new JLabel("AGE");
		add(age);
		age.setBounds(30, 60, 100, 40);
		gender = new JLabel("GENDER");
		add(gender);
		gender.setBounds(30, 100, 100, 40);
		jobTitle = new JLabel("JOB TITLE");
		add(jobTitle);
		jobTitle.setBounds(30, 140, 100, 40);
		salary = new JLabel("SALARY");
		add(salary);
		salary.setBounds(30, 180, 100, 40);
		phone = new JLabel("PHONE No.");
		add(phone);
		phone.setBounds(30, 220, 100, 40);
		aadhar = new JLabel("AADHAR No.");
		add(aadhar);
		aadhar.setBounds(30, 260, 100, 40);
		emailId = new JLabel("EMAIL ID");
		add(emailId);
		emailId.setBounds(30, 300, 100, 40);
		
		
		//Adding TextFields
		nameField = new JTextField();
		nameField.setBounds(130, 20, 150, 30);
		add(nameField);
		ageField = new JTextField();
		ageField.setBounds(130, 60, 150, 30);
		add(ageField);
		salaryField = new JTextField();
		salaryField.setBounds(130, 180, 150, 30);
		add(salaryField);
		phoneField = new JTextField();
		phoneField.setBounds(130, 220, 150, 30);
		add(phoneField);
		aadharField = new JTextField();
		aadharField.setBounds(130, 260, 150, 30);
		add(aadharField);
		emailField = new JTextField();
		emailField.setBounds(130, 300, 150, 30);
		add(emailField);
		
		
		//Adding RadioButtons for gender
		maleButton = new JRadioButton("male");
		maleButton.setBounds(130, 100, 60, 20);
		add(maleButton);
		maleButton.addActionListener(this);
		femaleButton = new JRadioButton("female");
		femaleButton.setBounds(200, 100, 100, 20);
		add(femaleButton);
		femaleButton.addActionListener(this);
		
		
		
		//Adding combobox for job title
		String items[] = {"Front Desk Clerks","Porters","Housekeeping","Kitchen Staff","Room Service","Waiter/Waitress","Manager","Accountant","Chef"}; 
		jobBox = new JComboBox(items);
		jobBox.setBounds(130, 140, 150, 30);
		add(jobBox);
		
		
		//Adding Button
		submitButton = new JButton("SUBMIT");
		submitButton.setBounds(70, 360, 100, 30);
		add(submitButton);
		submitButton.addActionListener(this);
		
		cancelButton = new JButton("CANCEL");
		cancelButton.setBounds(190, 360, 100, 30);
		add(cancelButton);
		cancelButton.addActionListener(this);
		
		setLayout(null);
		setVisible(true);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == submitButton) {
			String name = nameField.getText();
			String age = ageField.getText();
			String salary = salaryField.getText();
			String phone = phoneField.getText();
			String aadhar = aadharField.getText();
			String email = emailField.getText();
			String jobTitle = (String)jobBox.getSelectedItem();
			String gender="";
			if(maleButton.isSelected()) gender = "male";
			else if(femaleButton.isSelected()) gender = "female";
			
			if(name.equals("") || age.equals("") || salary.equals("") || jobTitle.equals("") || email.equals("") || phone.equals("") || aadhar.equals("") || gender.equals("")) {
				JOptionPane.showMessageDialog(null, "Please fill the details correctly");
			}
			try {
				DataBaseConnection con = new DataBaseConnection();
				String query = "insert into employee_details values(?, ?, ?, ?, ?, ?, ?, ?);";
				con.preparedStatement = con.connection.prepareStatement(query);
				con.preparedStatement.setString(1, name);
				con.preparedStatement.setString(2, age);
				con.preparedStatement.setString(3, gender);
				con.preparedStatement.setString(4, jobTitle);
				con.preparedStatement.setString(5, salary);
				con.preparedStatement.setString(6, phone);
				con.preparedStatement.setString(7, aadhar);
				con.preparedStatement.setString(8, email);
				con.preparedStatement.executeUpdate();
				con.connection.close();
				this.setVisible(false);
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		
		else if(e.getSource() == cancelButton) {
			this.setVisible(false);
		}
		
		else if(e.getSource() == maleButton) {
			femaleButton.setSelected(false);
		}
		else if(e.getSource() == femaleButton) {
			maleButton.setSelected(false);
		}
	}
	
//	public static void main(String[] args) {
//		new AddEmployee();
//	}
	
}