package model.answer;

public class AnswerDTO {
	private int aId;

	private int mId;

	private int qId;

	private String answer;

	 

	//------------java-------

	private String searchCondition;

	private int answerCntA;

	private int answerCntB;

	public int getaId() {
		return aId;
	}

	public void setaId(int aId) {
		this.aId = aId;
	}

	public int getmId() {
		return mId;
	}

	public void setmId(int mId) {
		this.mId = mId;
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


    

  
}