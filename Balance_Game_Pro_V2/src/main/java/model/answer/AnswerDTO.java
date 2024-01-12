package model.answer;

public class AnswerDTO {
    private int aid;
    private int mid; // USERS 테이블의 IDX를 참조
    private int qid; // QUESTIONS 테이블의 IDX를 참조
    private String answer;
    
    
    //------------java-------
    private String searchCondition;
    private int answerCntA;
    private int answerCntB;
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public int getQid() {
		return qid;
	}
	public void setQid(int qid) {
		this.qid = qid;
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