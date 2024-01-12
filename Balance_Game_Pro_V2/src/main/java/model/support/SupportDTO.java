package model.support;

import java.util.Date;

public class SupportDTO {
	private int suid;
	private int mid;
	private int amount;

   
    //-------java------
	
    private String searchCondition;


	public int getSuid() {
		return suid;
	}


	public void setSuid(int suid) {
		this.suid = suid;
	}


	public int getMid() {
		return mid;
	}


	public void setMid(int mid) {
		this.mid = mid;
	}


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}


	public String getSearchCondition() {
		return searchCondition;
	}


	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
    
    
    
    
	

}
