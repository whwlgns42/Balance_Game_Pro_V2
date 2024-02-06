package model.support;

import java.util.Date;

public class SupportDTO {

	private int suId;//후원 PK

	private String loginId;//로그인 아이디 -> MEMBER 테이블의 LOGIN_ID를 참조

	private int amount;//후원 금액

	private Date date;//후원 날짜

	 

	//-------java------

	 

	private String searchCondition;//찾는 방법

	private String name;//후원자 이름
	
	private int total;//총액
	
	private int ranking;//등수


	

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

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

	@Override
	public String toString() {
		return "SupportDTO [suId=" + suId + ", loginId=" + loginId + ", amount=" + amount + ", date=" + date
				+ ", searchCondition=" + searchCondition + ", name=" + name + ", total=" + total + ", ranking="
				+ ranking + "]";
	}

}
