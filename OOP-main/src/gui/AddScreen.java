package gui;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import controller.MainFrame;
import data.PhonePlan;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.awt.Font;

public class AddScreen extends JPanel{
	private JTable table;
	private DefaultTableModel model;
	private JTextField txtName;
	private JTextField txtPrice;
	private JTextField txtDataAmt;
	private JTextField txtTalkTime;
	private JTextField txtDataRoam;
	private JTextField txtSMS;
	private MainFrame main;
	private PhonePlan[] phonePlans;
	
	public AddScreen(MainFrame main) {
		setBackground(new Color(255, 255, 255));
		this.main = main;
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(312, 11, 558, 394);
		add(scrollPane);
		
		this.model = new DefaultTableModel(){
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		this.table = new JTable(model);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int i = table.getSelectedRow();
				txtName.setText(model.getValueAt(i, 0).toString());
				txtPrice.setText(model.getValueAt(i, 1).toString());
				txtDataAmt.setText(model.getValueAt(i, 2).toString());
				txtTalkTime.setText(model.getValueAt(i, 3).toString());
				txtDataRoam.setText(model.getValueAt(i, 4).toString());
				txtSMS.setText(model.getValueAt(i, 5).toString());
			}
		});
		table.setBackground(new Color(255, 255, 255));
		Object[] column = {"Name", "Price", "Data Amount", "Talk Time", "Data Roaming", "SMS"};
		model.setColumnIdentifiers(column);
		scrollPane.setViewportView(table);
		
		JLabel lblName = new JLabel("Name: ");
		lblName.setBounds(10, 41, 46, 14);
		add(lblName);
		
		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setBounds(10, 76, 46, 14);
		add(lblPrice);
		
		JLabel lblData = new JLabel("Data Amount:");
		lblData.setBounds(10, 110, 83, 14);
		add(lblData);
		
		JLabel lblTalkTime = new JLabel("Talk Time:");
		lblTalkTime.setBounds(10, 146, 65, 14);
		add(lblTalkTime);
		
		JLabel lblDataRoaming = new JLabel("Data Roaming:");
		lblDataRoaming.setBounds(10, 184, 83, 14);
		add(lblDataRoaming);
		
		JLabel lblSms = new JLabel("SMS:");
		lblSms.setBounds(10, 220, 46, 14);
		add(lblSms);
		
		txtName = new JTextField();
		txtName.setBounds(98, 38, 198, 20);
		add(txtName);
		txtName.setColumns(10);
		
		txtPrice = new JTextField();
		txtPrice.setBounds(98, 73, 198, 20);
		txtPrice.setColumns(10);
		add(txtPrice);
		
		txtDataAmt = new JTextField();
		txtDataAmt.setBounds(98, 107, 198, 20);
		txtDataAmt.setColumns(10);
		add(txtDataAmt);
		
		txtTalkTime = new JTextField();
		txtTalkTime.setBounds(98, 143, 198, 20);
		txtTalkTime.setColumns(10);
		add(txtTalkTime);
		
		txtDataRoam = new JTextField();
		txtDataRoam.setBounds(98, 181, 198, 20);
		txtDataRoam.setColumns(10);
		add(txtDataRoam);
		
		txtSMS = new JTextField();
		txtSMS.setBounds(98, 217, 198, 20);
		txtSMS.setColumns(10);
		add(txtSMS);
		
		JButton btnAddPlan = new JButton("Add Plan");
		btnAddPlan.setBackground(new Color(224, 255, 255));
		btnAddPlan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtName.getText().trim().equals("")||txtPrice.getText().trim().equals("")){
					JOptionPane.showMessageDialog(null, "Please fill in name and price");
				}else if(!isNumber(txtPrice.getText())||!isNumber(txtDataAmt.getText())||!isNumber(txtDataRoam.getText())||!isNumber(txtSMS.getText())||!isNumber(txtTalkTime.getText())){
					JOptionPane.showMessageDialog(null, "Please enter numbers in the following fields: Price, Data Amount, Talk Time, Data Roaming, SMS");
				}else if(txtPrice.getText().trim().charAt(0)=='-'||txtDataAmt.getText().trim().charAt(0)=='-'||txtDataRoam.getText().trim().charAt(0)=='-'||txtSMS.getText().trim().charAt(0)=='-'||txtTalkTime.getText().trim().charAt(0)=='-'){
					JOptionPane.showMessageDialog(null, "Please enter positive numbers in the following fields: Price, Data Amount, Talk Time, Data Roaming, SMS");
				}else{
					String name = txtName.getText().trim();
					float price = Float.valueOf(txtPrice.getText().trim());
					float dataAmt = Float.valueOf(txtDataAmt.getText().trim());
					float talkTime = Float.valueOf(txtTalkTime.getText().trim());
					float dataRoaming = Float.valueOf(txtDataRoam.getText().trim());
					int smsAmt = Integer.valueOf(txtSMS.getText().trim());
					main.getController().addPlanDetails(dataRoaming, price, dataAmt, talkTime, name, smsAmt);
					model.setRowCount(0);
					populateTable();
					txtName.setText("");
					txtDataAmt.setText("");
					txtDataRoam.setText("");
					txtTalkTime.setText("");
					txtSMS.setText("");
					txtPrice.setText("");
				}
			}
		});
		btnAddPlan.setBounds(31, 255, 109, 23);
		add(btnAddPlan);
		
		JButton btnUpdate = new JButton("Update Plan");
		btnUpdate.setBackground(new Color(224, 255, 255));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtName.getText().trim().equals("")||txtPrice.getText().trim().equals("")){
					JOptionPane.showMessageDialog(null, "Please fill in name and price");
				}else if(!isNumber(txtPrice.getText())||!isNumber(txtDataAmt.getText())||!isNumber(txtDataRoam.getText())||!isNumber(txtSMS.getText())||!isNumber(txtTalkTime.getText())){
					JOptionPane.showMessageDialog(null, "Please enter numbers in the following fields: Price, Data Amount, Talk Time, Data Roaming, SMS");
				}else if(txtPrice.getText().trim().charAt(0)=='-'||txtDataAmt.getText().trim().charAt(0)=='-'||txtDataRoam.getText().trim().charAt(0)=='-'||txtSMS.getText().trim().charAt(0)=='-'||txtTalkTime.getText().trim().charAt(0)=='-'){
					JOptionPane.showMessageDialog(null, "Please enter positive numbers in the following fields: Price, Data Amount, Talk Time, Data Roaming, SMS");
				}else{
					int index = table.getSelectedRow();
					if(index == -1)
						return;
					String name = txtName.getText().trim();
					float price = Float.valueOf(txtPrice.getText().trim());
					float dataAmt = Float.valueOf(txtDataAmt.getText().trim());
					float talkTime = Float.valueOf(txtTalkTime.getText().trim());
					float dataRoaming = Float.valueOf(txtDataRoam.getText().trim());
					int smsAmt = Integer.valueOf(txtSMS.getText().trim());
					PhonePlan newPlan = new PhonePlan(name, price, dataAmt, talkTime, dataRoaming, smsAmt);
					main.getController().editPlan(index, newPlan);
					model.setRowCount(0);
					populateTable();
				}
			}
		});
		btnUpdate.setBounds(164, 255, 109, 23);
		add(btnUpdate);
		
		JButton btnDeletePlan = new JButton("Delete Plan");
		btnDeletePlan.setBackground(new Color(224, 255, 255));
		btnDeletePlan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow();
				if(index == -1)
					return;
				model.removeRow(index);
				PhonePlan p = phonePlans[index];
				main.getController().deletePlan(index);
				txtName.setText("");
				txtDataAmt.setText("");
				txtDataRoam.setText("");
				txtTalkTime.setText("");
				txtSMS.setText("");
				txtPrice.setText("");
			}
		});
		btnDeletePlan.setBounds(30, 295, 109, 23);
		add(btnDeletePlan);
		
		JLabel lblPlanManagement = new JLabel("Phone Plan Management");
		lblPlanManagement.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPlanManagement.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlanManagement.setBounds(42, 11, 231, 18);
		add(lblPlanManagement);
		
		JButton btnGg = new JButton("View Customer Summary");
		btnGg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.showOrder();
			}
		});
		btnGg.setBounds(32, 333, 242, 23);
		add(btnGg);
		
		JButton btnOrder = new JButton("Load from File");
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Files", "csv");
				fileChooser.setFileFilter(filter);
				
				int returnValue = fileChooser.showOpenDialog(null);
				if(returnValue == JFileChooser.APPROVE_OPTION) {
					 try {
						String directory = fileChooser.getSelectedFile().getAbsolutePath();
						loadCSVData(directory);
						model.setRowCount(0);
						populateTable();
					}
					catch (IOException e){
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "error loading csv");
					}
				 }
			}
		});
		btnOrder.setBounds(164, 295, 109, 23);
		add(btnOrder);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.showLogin();
			}
		});
		btnLogout.setBounds(31, 367, 242, 23);
		add(btnLogout);
		this.populateTable();
	}

	private void populateTable(){
		this.phonePlans = this.main.getController().getAllPlans();
		for(int i=0; i<phonePlans.length; i++){
			PhonePlan temp = phonePlans[i];
			model.insertRow(table.getRowCount(), new Object[] {temp.getName(), temp.getPrice(), temp.getDataAmt(), temp.getTalkTime(), temp.getDataRoaming(), temp.getSmsAmt()});
		}
		this.table.setModel(model);
	}
	
	private void loadCSVData(String directory) throws IOException {
	    try (BufferedReader reader = new BufferedReader(new FileReader(directory))) {
	    	reader.readLine();
	        String line;
	        while ((line = reader.readLine()) != null) {
	            String[] fields = line.split(",");
	            if (fields.length == 6) {
	                String name = fields[0].trim();
	                float dataAmt = Float.parseFloat(fields[1].trim());
	                float talkTime = Float.parseFloat(fields[2].trim());
	                int smsAmt = Integer.parseInt(fields[3].trim());
	                float dataRoaming = Float.parseFloat(fields[4].trim());
	                float price = Float.parseFloat(fields[5].trim());

	                PhonePlan plan = new PhonePlan(name, price, dataAmt, talkTime, dataRoaming, smsAmt);
	                main.getController().addPlanDetails(dataRoaming, price, dataAmt, talkTime, name, smsAmt);
	            }
	        }
	    } catch (IOException | NumberFormatException e){
	    	e.printStackTrace();
	    	JOptionPane.showMessageDialog(null, "Error loading csv");
	    }
	    
	}
	
	private boolean isNumber(String s){
		try{
			Float.parseFloat(s);
		}catch(NumberFormatException ex){
			return false;
		}
		return true;
	}
}