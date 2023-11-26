package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import controller.MainFrame;

import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.JRadioButton;

public class LoginScreen extends JPanel{
	private JTextField txtUsername;
	private MainFrame main;
	private JLabel errorLabel;
	private JPasswordField txtPassword;
	private JRadioButton rdbtnCustomer;
	private JRadioButton rdbtnStaff;
	public static String name = "";
	public LoginScreen(MainFrame main) {
		setBackground(new Color(255, 255, 255));
		this.main = main;
		setLayout(null);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setBounds(115, 11, 96, 25);
		add(lblLogin);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(Color.BLACK);
		lblUsername.setBounds(37, 52, 75, 14);
		add(lblUsername);
		
		txtUsername = new JTextField();
		txtUsername.setBackground(new Color(245, 245, 245));
		txtUsername.setBounds(37, 77, 259, 20);
		add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(37, 108, 61, 14);
		add(lblPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBackground(new Color(224, 255, 255));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rdbtnCustomer.isSelected()){
					name = txtUsername.getText();
					String pwd = String.valueOf(txtPassword.getPassword());
					boolean userValidity = main.getController().validateUser(name, pwd);
					if(userValidity){
						main.showSub();
					}else{
						errorLabel.setText("Login failed. Please try again.");
					}
				}
				if(rdbtnStaff.isSelected()){
					main.getController().addStaff();
					name = txtUsername.getText();
					String pwd = String.valueOf(txtPassword.getPassword());
					boolean staffValidity = main.getController().verifyStaff(name, pwd);
					if(staffValidity){
						main.showAdd();
					}else{
						errorLabel.setText("Login failed. Please try again.");
					}
				}
			}
		});
		btnLogin.setBounds(89, 264, 157, 23);
		add(btnLogin);
		
		this.errorLabel = new JLabel("");
		errorLabel.setForeground(Color.RED);
		errorLabel.setBounds(36, 164, 260, 25);
		add(errorLabel);
		
		txtPassword = new JPasswordField();
		txtPassword.setBackground(new Color(245, 245, 245));
		txtPassword.setBounds(34, 133, 262, 20);
		add(txtPassword);
		
		JLabel lblImg = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/newsignin.png")).getImage();
		lblImg.setIcon(new ImageIcon(img));
		lblImg.setBounds(397, -28, 400, 400);
		add(lblImg);
		
		JButton btnRegister = new JButton("Register Account");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				main.showRegScreen();
			}
		});
		btnRegister.setBackground(new Color(224, 255, 255));
		btnRegister.setBounds(89, 298, 157, 23);
		add(btnRegister);
		
		this.rdbtnCustomer = new JRadioButton("Customer");
		rdbtnCustomer.setBackground(new Color(255, 255, 255));
		rdbtnCustomer.setBounds(37, 215, 109, 23);
		add(rdbtnCustomer);
		
		this.rdbtnStaff = new JRadioButton("Staff");
		rdbtnStaff.setBackground(new Color(255, 255, 255));
		rdbtnStaff.setBounds(148, 215, 109, 23);
		add(rdbtnStaff);
		
		ButtonGroup radioButtonGroup = new ButtonGroup();
		radioButtonGroup.add(rdbtnCustomer);
		radioButtonGroup.add(rdbtnStaff);
	}
}