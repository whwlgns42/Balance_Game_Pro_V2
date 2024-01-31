package model.suggestion;

public class SuggestionDTO {
	private int sugId;
	private String loginId;
	private String suggestion;
	private String title;
	@Override
	public String toString() {
		return "SuggestionDTO [sugId=" + sugId + ", loginId=" + loginId + ", suggestion=" + suggestion + ", title="
				+ title + "]";
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
