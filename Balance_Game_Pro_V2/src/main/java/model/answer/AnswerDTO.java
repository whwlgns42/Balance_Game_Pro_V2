package model.answer;

public class AnswerDTO {
	private int aId;

	private String loginId;

	private int qId;

	private String answer;

	 

	//------------java-------

	private String searchCondition;

	private String ansTitle;
	private int answerCntA;

	private int answerCntB;

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

	public int getAnswerCntA() {
		return answerCntA;
	}

	public void setAnswerCntA(int answerCntA) {
		this.answerCntA = answerCntA;
	}

	public int getAnswerCntB() {
		return answerCntB;
	}

	public void setAnswerCntB(int answerCntB) {
		this.answerCntB = answerCntB;
	}

	public String getAnsTitle() {
		return ansTitle;
	}

	public void setAnsTitle(String ansTitle) {
		this.ansTitle = ansTitle;
	}

	@Override
	public String toString() {
		return "AnswerDTO [aId=" + aId + ", loginId=" + loginId + ", qId=" + qId + ", answer=" + answer
				+ ", searchCondition=" + searchCondition + ", ansTitle=" + ansTitle + ", answerCntA=" + answerCntA
				+ ", answerCntB=" + answerCntB + "]";
	}

	
  
}