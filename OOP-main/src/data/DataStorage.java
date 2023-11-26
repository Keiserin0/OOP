package data;

import java.util.Vector;

import gui.LoginScreen;

public class DataStorage {
	Vector<User> userStorage = new Vector<User>();
	Vector<Staff> staffStorage = new Vector<Staff>();
	Vector<PhonePlan> planStorage = new Vector<PhonePlan>();
	Vector<UserSubscription> userPlanStorage = new Vector<UserSubscription>();
	
	public Staff getStaff(String n){
		for(int i=0; i<staffStorage.size(); i++){
			Staff temp = staffStorage.get(i);
			if(temp.getUsername().equals(n))
				return temp;
		}
		return null;
	}
	
	public void storeStaff(Staff s){
		this.staffStorage.add(s);
	}
	
	public Staff[] getAllStaffs(){
		Staff[] staffArr = new Staff[this.staffStorage.size()];
		this.staffStorage.toArray(staffArr);
		return staffArr;
	}
	/**
	 * 
	 * @param phonePlan 
	 */
	public void addPlan(PhonePlan phonePlan) {
		System.out.println(phonePlan.getName() + phonePlan.getPrice() + phonePlan.getDataAmt() + phonePlan.getTalkTime() + phonePlan.getDataRoaming() + phonePlan.getSmsAmt());
		this.planStorage.add(phonePlan);
		for(int i=0; i<planStorage.size(); i++){
			PhonePlan temp = planStorage.get(i);
			System.out.println(temp.getName() + " " + temp.getPrice());
		}
	 }

	public void storeUser(User u){
		this.userStorage.add(u);
	}
	
	public User getUser(String n) { 
		for(int i=0; i<userStorage.size(); i++){
			User temp = userStorage.get(i);
			if(temp.getUsername().equals(n))
				return temp;
		}
		return null;
	}
	
	public User[] getAllUsers(){
		User[] userArr = new User[this.userStorage.size()];
		this.userStorage.toArray(userArr);
		return userArr;
	}
	
	/**
	 * 
	 * @return 
	 */
	public PhonePlan[] getAllPlans() {
		PhonePlan[] opArr = new PhonePlan[this.planStorage.size()];
		this.planStorage.toArray(opArr);
		return opArr;
	 }

	/**
	 * 
	 * @param index 
	 */
	public void deletePlan(int index) {
		this.planStorage.remove(index);
		for(int i=0; i<planStorage.size(); i++){
			PhonePlan temp = planStorage.get(i);
			System.out.println(temp.getName() + " " + temp.getPrice());
		}
	 }

	/**
	 * 
	 * @param index 
	 * @param phonePlan 
	 */
	public void editPlan(int index, PhonePlan phonePlan) { 
		this.planStorage.setElementAt(phonePlan, index);
	 } 
	
	public void addUserPlan(PhonePlan phonePlan){
		UserSubscription temp = new UserSubscription(phonePlan.getName(), phonePlan.getPrice(), phonePlan.getDataAmt(), phonePlan.getTalkTime(), phonePlan.getDataRoaming(), phonePlan.getSmsAmt());
		temp.setUsername(LoginScreen.name);
		this.userPlanStorage.add(temp);
	}
	
	public void deleteUserPlan(UserSubscription currentPlan) {
		this.userPlanStorage.remove(currentPlan);
		//this.userPlanStorage.remove(index);
	}

	public UserSubscription[] getAllCustomerPlans() {
		UserSubscription[] opArr = new UserSubscription[this.userPlanStorage.size()];
		this.userPlanStorage.toArray(opArr);
		return opArr;
	}
}