package gui;

import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import controller.MainFrame;
import data.PhonePlan;
import data.UserSubscription;

import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;

public class PlanRow extends JPanel{
	private MainFrame main;
	private PhonePlan phonePlan;
	private JLabel lblNewLabel;
	
	@Override
	public Dimension getPreferredSize(){
		return new Dimension(423, 146);
	}
	
	/*@Override
	public Dimension getMaximumSize(){
		return new Dimension(423, 146);
	}*/
	
	public PlanRow(PhonePlan phonePlan, MainFrame main) {
		this.main = main;
		this.phonePlan = phonePlan;
		setBackground(new Color(230, 230, 250));
		setLayout(null);
		
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		
		this.lblNewLabel = new JLabel("Image");
		lblNewLabel.setBounds(10, 11, 113, 119);
		add(lblNewLabel);
		
		ImageIcon img = new ImageIcon(this.getClass().getResource("/"+phonePlan.getName()+".jpg"));
        Image image = img.getImage().getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(),Image.SCALE_SMOOTH );
        ImageIcon resizedImg = new ImageIcon(image);
        lblNewLabel.setIcon(resizedImg);
		
		JLabel lblName = new JLabel("Name: " + phonePlan.getName());
		lblName.setBounds(133, 11, 161, 14);
		add(lblName);
		
		JLabel lblPrice = new JLabel("Price: " + phonePlan.getPrice());
		lblPrice.setBounds(133, 32, 161, 14);
		add(lblPrice);
		
		JLabel lblDataAmt = new JLabel("Data Amount: " + phonePlan.getDataAmt());
		lblDataAmt.setBounds(133, 53, 161, 14);
		add(lblDataAmt);
		
		JLabel lblTalkTime = new JLabel("Talk Time: " + phonePlan.getTalkTime());
		lblTalkTime.setBounds(133, 74, 161, 14);
		add(lblTalkTime);
		
		JLabel lblSMS = new JLabel("SMS: " + phonePlan.getSmsAmt());
		lblSMS.setBounds(133, 116, 161, 14);
		add(lblSMS);
		
		JLabel lblDataRoaming = new JLabel("Data Roaming: " + phonePlan.getDataRoaming());
		lblDataRoaming.setBounds(133, 95, 161, 14);
		add(lblDataRoaming);
		
		JButton btnCart = new JButton("Add/Remove");
		btnCart.setBackground(new Color(224, 255, 255));
		btnCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserSubscription[] userPlans = main.getController().getAllCustomerPlans();
				UserSubscription currentPlan = new UserSubscription(phonePlan);
				currentPlan.setUsername(LoginScreen.name);
				boolean isAdded = false;
				for(UserSubscription userPlan : userPlans){
					if(userPlan.equals(currentPlan)){
						isAdded = true;
						System.out.println(isAdded);
						break;
					}else if(!userPlan.equals(currentPlan)){
						isAdded = false;
						System.out.println(isAdded);
						break;
					}else{
						continue;
					}
				}
				if(!isAdded){
					main.getController().addUserPlan(phonePlan);
					SubscribeScreen.addRowToTable(new Object[]{
							phonePlan.getName(),
							phonePlan.getPrice()
					});
					System.out.println(LoginScreen.name);
					userPlans = main.getController().getAllCustomerPlans();
					for(UserSubscription userPlan : userPlans){
						System.out.println(userPlan.getUsername() + userPlan.getPrice());
					}
					isAdded = true;
					SubscribeScreen.totalPrice += phonePlan.getPrice();
					SubscribeScreen.lblPrice.setText("Total Price: $" + String.valueOf(SubscribeScreen.totalPrice));
					btnCart.setText("Remove");
				}else if(isAdded){
					userPlans = main.getController().getAllCustomerPlans();
					currentPlan = new UserSubscription(phonePlan);
					currentPlan.setUsername(LoginScreen.name);
					System.out.println(currentPlan.getUsername() + " " + currentPlan.getName() + " " + currentPlan.getPrice());
					for(UserSubscription userPlan : userPlans){
						if(userPlan.equals(currentPlan)){
							main.getController().deleteUserPlan(userPlan);
							System.out.println("Deleted");
						}
					}
					UserSubscription[] newUserPlans = main.getController().getAllCustomerPlans();
					for(UserSubscription userPlan : newUserPlans){
						System.out.println(userPlan.getUsername() + " " + userPlan.getName() + " " + userPlan.getPrice());
					}
					SubscribeScreen.model.setRowCount(0);
					for(UserSubscription userPlan : newUserPlans){
						if(userPlan.getUsername().equals(LoginScreen.name)){
							SubscribeScreen.model.addRow(new Object[]{
									userPlan.getName(),
									userPlan.getPrice()
							});
						}
					}
					SubscribeScreen.refreshTable();
					SubscribeScreen.totalPrice -= phonePlan.getPrice();
					SubscribeScreen.lblPrice.setText("Total Price: $" + String.valueOf(SubscribeScreen.totalPrice));
					isAdded = false;
					btnCart.setText("Add to Cart");
				}
			}
		});
		btnCart.setBounds(304, 59, 113, 23);
		add(btnCart);
		
	}
}
