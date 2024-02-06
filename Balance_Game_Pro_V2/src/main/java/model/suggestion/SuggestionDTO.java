package model.suggestion;

public class SuggestionDTO {
	private int sugId;//건의사항 PK
	private String loginId;//로그인 아이디 -> MEMBER 테이블의 LOGIN_ID를 참조
	private String suggestion;//건의사항 내용
	private String title;//제목
	
	//------java-------
	private String searchCondition;//찾는 방식
	private String name;//건의한 유저 이름
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}

	@Override
	public String toString() {
		return "SuggestionDTO [sugId=" + sugId + ", loginId=" + loginId + ", suggestion=" + suggestion + ", title="
				+ title + ", searchCondition=" + searchCondition + ", name=" + name + "]";
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getSugId() {
		return sugId;
	}
	public void setSugId(int sugId) {
		this.sugId = sugId;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getSuggestion() {
		return suggestion;
	}
	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}

}
