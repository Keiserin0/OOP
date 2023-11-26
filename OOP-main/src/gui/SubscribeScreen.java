package gui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.MainFrame;
import data.PhonePlan;
import data.UserSubscription;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SubscribeScreen extends JPanel{
	static DefaultTableModel model;
	private static JTable table;
	private MainFrame main;
	private UserSubscription[] userPlans;
	private JLabel lblUsername;
	static JLabel lblPrice;
	static double totalPrice = 0;

	public SubscribeScreen(MainFrame main) {
		this.main = main;
		setLayout(null);
		
		JPanel cont = new JPanel();
		cont.setLayout(new BoxLayout(cont, BoxLayout.Y_AXIS));
		
		JScrollPane scrollPane = new JScrollPane(cont);
		scrollPane.setBounds(10, 91, 480, 340);
		add(scrollPane);
		
		if(main.getController().getAllPlans().equals(null)){
			return;
		}else{
			PhonePlan[] phonePlans = main.getController().getAllPlans();
			for(PhonePlan i:phonePlans){
				PlanRow row = new PlanRow(i, main);
				cont.add(row);
				scrollPane.repaint();
				scrollPane.revalidate();
			}
		}
		
		JLabel lblPlanCart = new JLabel("Plan Cart");
		lblPlanCart.setBounds(611, 66, 73, 14);
		add(lblPlanCart);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(500, 91, 287, 269);
		add(scrollPane_1);
		
		this.model = new DefaultTableModel(){
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		this.table = new JTable(model);
		table.setBackground(new Color(255, 255, 255));
		Object[] column = {"Name", "Price"};
		model.setColumnIdentifiers(column);
		scrollPane_1.setViewportView(table);
		
		this.lblPrice = new JLabel("");
		lblPrice.setBounds(694, 371, 96, 14);
		add(lblPrice);
		
		JLabel lblAvailablePlans = new JLabel("Available Plans");
		lblAvailablePlans.setBounds(10, 66, 132, 14);
		add(lblAvailablePlans);
		
		JLabel lblPlanSubscriptionMenu = new JLabel("Plan Subscription Menu");
		lblPlanSubscriptionMenu.setBounds(391, 11, 169, 14);
		add(lblPlanSubscriptionMenu);
		
		this.lblUsername = new JLabel(LoginScreen.name);
		lblUsername.setBounds(717, 11, 73, 14);
		add(lblUsername);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				main.showLogin();
			}
		});
		btnLogout.setBounds(595, 408, 120, 23);
		add(btnLogout);
		this.populateTable();
	}
	
	private void populateTable(){
		this.userPlans = this.main.getController().getAllCustomerPlans();
		for(UserSubscription temp : userPlans){
			if(temp.getUsername().equals(LoginScreen.name)){
				model.insertRow(table.getRowCount(), new Object[] {temp.getName(), temp.getPrice()});
			}
		}
		this.table.setModel(model);
	}
	
	public static void addRowToTable(Object[] dataRow){
		table.setModel(model);
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		model.insertRow(table.getRowCount(), dataRow);
	}
	
	public static void refreshTable(){
		table.setModel(model);
	}
}