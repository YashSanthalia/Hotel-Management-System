import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

public class AdminDashBoard extends JFrame implements ActionListener{
	
	JMenuBar menuBar;
	JMenu hotelManagementMenu, adminMenu;
	JMenuItem reception, addEmployee, addDriver, addRoom, logout;
	private ServerSocket serverSocket;
	private Socket socket;
	private DataInputStream din;
	private DataOutputStream dout;
	
	
	public AdminDashBoard() {
		setTitle("ADMIN DASHBOARD");
		setBounds(0, 0, 1320, 700);
		setLayout(null);
	
		//Adding Menu Bar
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1320, 20);
		add(menuBar);
		
		//Adding Items on Menu Bar
		hotelManagementMenu = new JMenu("HOTEL MANAGEMENT");
		menuBar.add(hotelManagementMenu);
		
		adminMenu = new JMenu("ADMIN");
		menuBar.add(adminMenu);
		
		
		//Adding Items in Menu
		reception = new JMenuItem("RECEPTION");
		hotelManagementMenu.add(reception);
		reception.addActionListener(this);
		
		addEmployee = new JMenuItem("ADD EMPLOYEE");
		adminMenu.add(addEmployee);
		addEmployee.addActionListener(this);
		
		addDriver = new JMenuItem("ADD DRIVER");
		adminMenu.add(addDriver);
		addDriver.addActionListener(this);
		
		addRoom = new JMenuItem("ADD ROOM");
		adminMenu.add(addRoom);
		addRoom.addActionListener(this);
		
		logout = new JMenuItem("LOGOUT");
		adminMenu.add(logout);
		logout.addActionListener(this);
		
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == reception) {
			new Reception();
		}
		if(e.getSource() == addEmployee) {
			new AddEmployee();
		}
		else if(e.getSource() == addRoom) {
			new AddRoom();
		}
		else if(e.getSource() == addDriver) {
			new AddDriver();
		}
		else if(e.getSource() == logout) {
			System.exit(0);
		}
	}
	
//	public static void main(String[] args) {
//		new AdminDashBoard();
//	}
	
}
