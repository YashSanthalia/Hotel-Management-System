import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import net.proteanit.sql.*;


public class AllCustomersInfo extends JFrame implements ActionListener{
	
	private JTable table;
	private JButton backButton;
	
	public AllCustomersInfo() {
		
		setTitle("All Employee Info");
		setLayout(null);
		setBounds(100, 20, 1055, 630);
		
		
		
		table = new JTable();
		table.setBounds(20, 30, 1000, 500);
		add(table);
		
		try {
			DataBaseConnection con = new DataBaseConnection();
			String query = "select * from customer_details;";
			con.statement = con.connection.createStatement();
			ResultSet set = con.statement.executeQuery(query);
			table.setModel(DbUtils.resultSetToTableModel(set));	
			con.connection.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		add(table);
	
		backButton = new JButton("BACK");
		backButton.setBounds(20, 540, 100, 40);
		add(backButton);
		backButton.addActionListener(this);
		setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == backButton) {
			new Reception();
			this.setVisible(false);
		}
	}
	
	public static void main(String [] args) {
		new AllCustomersInfo();
	}
}
