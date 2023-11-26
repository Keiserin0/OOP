package controller;
import data.DataStorage;
import data.PhonePlan;
import data.Staff;
import data.User;
import data.UserSubscription;

public class Controller {
	
	private DataStorage dataStorage = new DataStorage();
	
	public DataStorage getDataStorage() {
	 	 return dataStorage; 
	}
	public void setDataStorage(DataStorage dataStorage) { 
		 this.dataStorage = dataStorage; 
	}
	public void deletePlan(int index) {
		this.dataStorage.deletePlan(index);
	 }
	public void addPlanDetails(float dataRoaming, float price, float dataAmt, float talkTime, String name, int smsAmt) { 
		PhonePlan p = new PhonePlan(name, price, dataAmt, talkTime, dataRoaming, smsAmt);
		dataStorage.addPlan(p);
	 }
	public void editPlan(int index, PhonePlan phonePlan) {
		this.dataStorage.editPlan(index, phonePlan);
	 }
	public PhonePlan[] getAllPlans() { 
		return this.dataStorage.getAllPlans();
	 }
	
	public User[] getAllUsers(){
		return this.dataStorage.getAllUsers();
	}
	
	public void addStaff(){
		Staff staff = new Staff();
		staff.setUsername("admin");
		staff.setPassword("password");
		this.dataStorage.storeStaff(staff);
	}
	
	public void regUser(String n, String p) { 
		User user = new User();
		user.setUsername(n);
		user.setPassword(p);
		this.dataStorage.storeUser(user);
	 }

	public void regStaff(String n, String p){
		Staff staff = new Staff();
		staff.setUsername(n);
		staff.setPassword(p);
		this.dataStorage.storeStaff(staff);
	}
	
	public Staff[] getAllStaffs(){
		return this.dataStorage.getAllStaffs();
	}

	public void addUserPlan(PhonePlan phonePlan) {
		this.dataStorage.addUserPlan(phonePlan);
	}
	
	public void deleteUserPlan(UserSubscription currentPlan) {
		this.dataStorage.deleteUserPlan(currentPlan);
	}
	
	public UserSubscription[] getAllCustomerPlans() {
		return this.dataStorage.getAllCustomerPlans();
	} 

	public boolean verifyStaff(String nn, String p) { 
		String realp = p;
		String cc= "";
		Staff t = dataStorage.getStaff(nn);
		if(t!=null){
			cc = t.getPassword().toString();
			if(realp.equals(cc)){
				return true;
			}
			else{
				return false;
			}
		}
		else{
			return false;
		}
	 }
	
	public boolean validateUser(String name, String pwd) {
		String userPwd = pwd;
		String realPwd = "";
		User temp = dataStorage.getUser(name);
		if(temp!=null){
			realPwd = temp.getPassword().toString();
			if(userPwd.equals(realPwd))
				return true;
			else
				return false;
		}else
			return false;
	}

}