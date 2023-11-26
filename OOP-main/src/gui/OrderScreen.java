package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import data.DataStorage;
import data.PhonePlan;
import data.Staff;
import data.User;
import data.UserSubscription;
import controller.MainFrame;
import controller.Controller;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.ComboBox;

public class OrderScreen extends JPanel{
	private MainFrame main;

	private JTable saleTable;
	private JButton btnViewPlans;
	private JButton btnSales;
	private JComboBox cbCustomer;
	private ComboBoxModel ComboModel;
	private User[] user;
	private UserSubscription[] plans;
	private JLabel lblPrice;
	private JScrollPane scrollPane;
	private DefaultTableModel Tmodel;
	private UserSubscription[] customerPlans;

	private JLabel lblTotalSales;
	
	public OrderScreen(MainFrame mainFrame) {
		this.main= mainFrame;
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Choose a Customer:");
		lblNewLabel.setBounds(12, 75, 127, 28);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Order Viewer");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(12, 13, 127, 19);
		add(lblNewLabel_1);

		cbCustomer = new JComboBox();
		cbCustomer.setBounds(135, 80, 141, 19);
		add(cbCustomer);
		
		this.btnViewPlans = new JButton("View Customer's Plans");
		btnViewPlans.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	populateCustomerPlans(cbCustomer.getSelectedItem().toString());	
		    }
		});
		btnViewPlans.setBounds(159, 434, 141, 25);
		add(btnViewPlans);
	
		scrollPane = new JScrollPane();
		scrollPane.setBounds(310, 13, 480, 456);
		add(scrollPane);
		
		this.Tmodel = new DefaultTableModel(){
			@Override
		    public boolean isCellEditable(int row, int column) {
			       return false;
			}
		};
        saleTable = new JTable(Tmodel);
        Object[] column = {"Name", "Data Amount", "Talk Time", "Data Roaming", "SMS", "Price"};
        Tmodel.setColumnIdentifiers(column);
        scrollPane.setViewportView(saleTable);
        
        JButton btnBacklogin = new JButton("Back to Plan Management");
        btnBacklogin.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		main.showLogin();
        	}
        });
        btnBacklogin.setBounds(12, 470, 288, 25);
        add(btnBacklogin);
        
        this.lblPrice = new JLabel("");
        lblPrice.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblPrice.setBounds(544, 284, 194, 16);
        add(lblPrice);
        
        JButton btnTotalSales = new JButton("Show Total Sales");
        btnTotalSales.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		lblTotalSales.setText("Total Sales: " + "$"+calculateTotalPrice());
        	}
        });
        btnTotalSales.setBounds(12, 434, 137, 25);
        add(btnTotalSales);
        
        this.lblTotalSales = new JLabel("");
        lblTotalSales.setBounds(649, 481, 141, 14);
        add(lblTotalSales);
        
        this.populateComboBox();
		}
	
	public void populateComboBox(){
		this.user = main.getController().getAllUsers();
		if (user==null){
			JOptionPane.showMessageDialog(this, "Please choose a customer!");
		}
		else{
			Set<String> uniqueNames = new HashSet<>();
			for(int i=0;i<user.length;i++){
				uniqueNames.add(this.user[i].getUsername());
			}
			String[] uniqueArray = new String[uniqueNames.size()];
			int index = 0;
			for(String name: uniqueNames){
				uniqueArray[index++] = name;
				this.cbCustomer.addItem(name);
			}
		}
		
	}
	
	public void populateCustomerPlans(String username){
		this.customerPlans = main.getController().getAllCustomerPlans();
		String[] column = {"Name", "Data Amount", "Talk Time", "SMS", "Roaming", "Price($)"};
	    DefaultTableModel model = new DefaultTableModel(column, 0);
	    for(UserSubscription userPlan : customerPlans){
	    	if(userPlan.getUsername().equals(username)){
		    	Object[] rowData = {userPlan.getName(), userPlan.getDataAmt(), userPlan.getTalkTime(), userPlan.getSmsAmt(), userPlan.getDataRoaming(), userPlan.getPrice()};
		    	model.addRow(rowData);
	    	}
		}
	    this.saleTable.setModel(model);
	}
	
	public double calculateTotalPrice(){
		this.customerPlans = main.getController().getAllCustomerPlans();
	    String[] column = {"Customer Name", "Sales($)"};
	    DefaultTableModel model = new DefaultTableModel(column, 0);
	    
	    double totalSales = 0; // Reset the total sales before recalculating
	    
	    for(UserSubscription userPlan : customerPlans){
	        Object[] rowData = {userPlan.getUsername(), "$" + userPlan.getPrice()};
	        model.addRow(rowData);
	        
	        // Add the price to totalSales
	        totalSales += Float.valueOf(userPlan.getPrice());

	    }
	    
	    this.saleTable.setModel(model);
		return totalSales;
	}
}
		







