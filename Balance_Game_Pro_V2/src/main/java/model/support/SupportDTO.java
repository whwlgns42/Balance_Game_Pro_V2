package model.support;

import java.util.Date;

public class SupportDTO {

	private int suId;

	private String loginId;

	private int amount;

	 

	 

	//-------java------

	 

	private String searchCondition;

	private String name;
	
	private int total;
	
	private int ranking;


	

	public int getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getSuId() {
		return suId;
	}

	public void setSuId(int suId) {
		this.suId = suId;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
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
