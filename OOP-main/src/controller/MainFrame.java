package controller;
import java.awt.CardLayout;

import javax.swing.JFrame;

import gui.AddScreen;
import gui.LoginScreen;
import gui.OrderScreen;
import gui.RegUserScreen;
import gui.SubscribeScreen;

public class MainFrame extends JFrame{
	public MainFrame() {
		this.controller = new Controller();
		this.card = new CardLayout();
		this.setTitle("DataPLUS Phone Plan System");
		this.setSize(900, 400);
		getContentPane().setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(this.card);
		this.showLogin();
		this.setVisible(true);
	}

	private CardLayout card;
	/**
	 * 
	 */
	private Controller controller;
	/**
	 * 
	 */
	public LoginScreen loginscreen;
	/**
	 * 
	 */
	public AddScreen addscreen;
	/**
	 * Getter of controller
	 */
	public Controller getController() {
	 	 return this.controller; 
	}
	/**
	 * Setter of controller
	 */
	public void setController(Controller controller) { 
		 this.controller = controller; 
	}
	/**
	 * Getter of loginscreen
	 */
	public LoginScreen getLoginscreen() {
	 	 return loginscreen; 
	}
	/**
	 * Setter of loginscreen
	 */
	public void setLoginscreen(LoginScreen loginscreen) { 
		 this.loginscreen = loginscreen; 
	}
	/**
	 * Getter of addscreen
	 */
	public AddScreen getAddscreen() {
	 	 return addscreen; 
	}
	/**
	 * Setter of addscreen
	 */
	public void setAddscreen(AddScreen addscreen) { 
		 this.addscreen = addscreen; 
	}
	/**
	 * 
	 */
	public void showRegScreen() { 
		RegUserScreen p1 = new RegUserScreen(this);
		this.add(p1,"Panel1");
		this.card.show(this.getContentPane(), "Panel1");
	 }
	
	public void showLogin() {
		LoginScreen p2 = new LoginScreen(this);
		this.add(p2, "Login");
		this.card.show(this.getContentPane(), "Login");
	 }
	/**
	 * 
	 * @param args[] 
	 */
	public static void main(String args[]) {
		MainFrame gui = new MainFrame();
	 }
	/**
	 * 
	 */
	public void showAdd() { 
		AddScreen p3 = new AddScreen(this);
		this.add(p3, "Add");
		this.card.show(this.getContentPane(), "Add");
	 } 

	public void showSub() { 
		SubscribeScreen p4 = new SubscribeScreen(this);
		this.add(p4, "Subscribe");
		this.card.show(this.getContentPane(), "Subscribe");
	 }
	
	public void showOrder(){
		OrderScreen p5 = new OrderScreen(this);
		this.add(p5, "Panel6");
		this.card.show(this.getContentPane(), "Panel6");
	}
}