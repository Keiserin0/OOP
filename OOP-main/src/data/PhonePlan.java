package data;


public class PhonePlan {
	private float talkTime;
	private int smsAmt;
	private float dataRoaming;
	private String name;
	private float dataAmt;
	private float price;
	
	public PhonePlan(String n, float p, float dataAmt, float t, float dataRoaming, int sms){
		this.name = n;
		this.price = p;
		this.dataAmt = dataAmt;
		this.talkTime = t;
		this.dataRoaming = dataRoaming;
		this.smsAmt = sms;
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