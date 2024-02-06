package model.answer;

public class AnswerDTO {
	
	private int aId;//답변 pk
	
	private String loginId;//로그인 아이디 -> MEMBER 테이블의 LOGIN_ID를 참조
	
	private int qId; //질문 pk -> QUESTIONS 테이블의 QID를 참조
	
	private String answer;//답변

	 

	//------------java-------
	
	private String searchCondition;//찾는 방식



	public int getaId() {
		return aId;
	}

	public void setaId(int aId) {
		this.aId = aId;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public int getqId() {
		return qId;
	}

	public void setqId(int qId) {
		this.qId = qId;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}


	@Override
	public String toString() {
		return "AnswerDTO [aId=" + aId + ", loginId=" + loginId + ", qId=" + qId + ", answer=" + answer
				+ ", searchCondition=" + searchCondition + "]";
	}

	
  
}