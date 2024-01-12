package model.support;

import java.util.Date;

public class SupportDTO {

	private int suId;

	private int mId;

	private int amount;

	 

	 

	//-------java------

	 

	private String searchCondition;

	private String title;



	public String getTitle() {
		return title;
	}





	public void setTitle(String title) {
		this.title = title;
	}





	public int getSuId() {
		return suId;
	}





	public void setSuId(int suId) {
		this.suId = suId;
	}





	public int getmId() {
		return mId;
	}





	public void setmId(int mId) {
		this.mId = mId;
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
