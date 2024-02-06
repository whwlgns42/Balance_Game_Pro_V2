package model.save;

public class SaveDTO {
	private int sId;// 찜 PK

	private int qId;// 질문 PK ->QUESTIONS 테이블의 QID를 참조

	private String loginId;// 로그인 아이디 -> MEMBER 테이블의 LOGIN_ID를 참조

	// ------java-------

	private String searchCondition;// 찾는 방법
	private String saveTitle;// 질문 제목
	private String saveWriter;// 질문 작성자

	public int getsId() {
		return sId;
	}

	public void setsId(int sId) {
		this.sId = sId;
	}

	public int getqId() {
		return qId;
	}

	public void setqId(int qId) {
		this.qId = qId;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}

	public String getSaveTitle() {
		return saveTitle;
	}

	public void setSaveTitle(String saveTitle) {
		this.saveTitle = saveTitle;
	}

	public String getSaveWriter() {
		return saveWriter;
	}

	public void setSaveWriter(String saveWriter) {
		this.saveWriter = saveWriter;
	}

	@Override
	public String toString() {
		return "SaveDTO [sId=" + sId + ", qId=" + qId + ", loginId=" + loginId + ", searchCondition=" + searchCondition
				+ ", saveTitle=" + saveTitle + ", saveWriter=" + saveWriter + "]";
	}

}
