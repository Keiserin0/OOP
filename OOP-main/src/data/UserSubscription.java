package data;

import java.util.Objects;

import gui.PlanRow;

public class UserSubscription {
	private String username;
	private float talkTime;
	private int smsAmt;
	private float dataRoaming;
	private String name;
	private float dataAmt;
	private float price;
	
	 @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        UserSubscription userSub = (UserSubscription) o;
	        return Objects.equals(username, userSub.username) &&
	               Objects.equals(name, userSub.name) &&
	               Objects.equals(price, userSub.price) &&
	               Objects.equals(dataAmt, userSub.dataAmt)&&
	               Objects.equals(talkTime, userSub.talkTime)&&
	               Objects.equals(dataRoaming, userSub.dataRoaming)&&
	               Objects.equals(smsAmt, userSub.smsAmt);
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hash(username, name, price, dataAmt, talkTime, dataRoaming, smsAmt);
	    }
	
	public UserSubscription(String n, float p, float dataAmt, float t, float dataRoaming, int sms){
		this.username = null;
		this.name = n;
		this.price = p;
		this.dataAmt = dataAmt;
		this.talkTime = t;
		this.dataRoaming = dataRoaming;
		this.smsAmt = sms;
	}
	
	public UserSubscription(PhonePlan phonePlan){
		this.username = null;
		this.name = phonePlan.getName();
		this.price = phonePlan.getPrice();
		this.dataAmt = phonePlan.getDataAmt();
		this.talkTime = phonePlan.getTalkTime();
		this.dataRoaming = phonePlan.getDataRoaming();
		this.smsAmt = phonePlan.getSmsAmt();
	}
	
	public String getUsername() {
	 	 return username; 
	}
	
	public void setUsername(String username) { 
		 this.username = username; 
	}
	
	public float getTalkTime() {
	 	 return talkTime; 
	}
	/**
	 * Setter of talkTime
	 */
	public void setTalkTime(float talkTime) { 
		 this.talkTime = talkTime; 
	}
	/**
	 * Getter of smsAmt
	 */
	public int getSmsAmt() {
	 	 return smsAmt; 
	}
	/**
	 * Setter of smsAmt
	 */
	public void setSmsAmt(int smsAmt) { 
		 this.smsAmt = smsAmt; 
	}
	/**
	 * Getter of dataRoaming
	 */
	public float getDataRoaming() {
	 	 return dataRoaming; 
	}
	/**
	 * Setter of dataRoaming
	 */
	public void setDataRoaming(float dataRoaming) { 
		 this.dataRoaming = dataRoaming; 
	}
	/**
	 * Getter of name
	 */
	public String getName() {
	 	 return name; 
	}
	/**
	 * Setter of name
	 */
	public void setName(String name) { 
		 this.name = name; 
	}
	/**
	 * Getter of dataAmt
	 */
	public float getDataAmt() {
	 	 return dataAmt; 
	}
	/**
	 * Setter of dataAmt
	 */
	public void setDataAmt(float dataAmt) { 
		 this.dataAmt = dataAmt; 
	}
	/**
	 * Getter of price
	 */
	public float getPrice() {
	 	 return price; 
	}
	/**
	 * Setter of price
	 */
	public void setPrice(float price) { 
		 this.price = price; 
	} 

}