package gui;

import controller.MainFrame;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

public class RegUserScreen extends JPanel{
	private MainFrame main;
	private JTextField usernameTxt;
	private JTextField passwordTxt;
	private JRadioButton rdbtnStaff;
	private JRadioButton rdbtnCustomer;
	private ButtonGroup radioButtonGroup;

	public RegUserScreen(MainFrame main) {
		this.main = main;
		setLayout(null);
		
		JLabel lblUserRegistration = new JLabel("Account Registration");
		lblUserRegistration.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserRegistration.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblUserRegistration.setBounds(121, 11, 184, 29);
		add(lblUserRegistration);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblUsername.setBounds(10, 81, 128, 29);
		add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPassword.setBounds(10, 136, 128, 29);
		add(lblPassword);
		
		this.usernameTxt = new JTextField();
		usernameTxt.setBounds(87, 79, 231, 35);
		add(usernameTxt);
		usernameTxt.setColumns(10);
		
		this.passwordTxt = new JTextField();
		passwordTxt.setColumns(10);
		passwordTxt.setBounds(87, 130, 231, 35);
		add(passwordTxt);

		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

					String n = usernameTxt.getText();
					String p = passwordTxt.getText();
					String status = "";
					if (rdbtnStaff.isSelected()){
						status = rdbtnStaff.getText();	
						main.getController().regStaff(n, p);
					}
					if (rdbtnCustomer.isSelected()){
						status = rdbtnCustomer.getText();
						main.getController().regUser(n, p);
					}
				//	main.getController().regUser(n, p);
				//	main.getController().setUsername(n);
				//	main.getController().setPassword(p);
					main.showLogin();
					

				}
			
			
		});
		
		
		btnRegister.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnRegister.setBounds(52, 226, 119, 23);
		add(btnRegister);
		
		JButton btnLoginScreen = new JButton("Go to Login");
		btnLoginScreen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.showLogin();
			}
		});
		btnLoginScreen.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLoginScreen.setBounds(211, 226, 155, 23);
		add(btnLoginScreen);
		
		this.rdbtnStaff = new JRadioButton("Staff");
		rdbtnStaff.setBounds(82, 177, 127, 25);
		
		this.rdbtnCustomer = new JRadioButton("Customer");
		rdbtnCustomer.setBounds(239, 178, 127, 25);	
		
		ButtonGroup radioButtonGroup = new ButtonGroup();
		radioButtonGroup.add(rdbtnCustomer);
		radioButtonGroup.add(rdbtnStaff);
		add(rdbtnCustomer);		
		add(rdbtnStaff);
	}

} 
