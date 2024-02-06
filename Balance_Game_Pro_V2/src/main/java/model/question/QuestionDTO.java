package model.question;

public class QuestionDTO {
	private int qId;// 질문 PK

	private String loginId; // 로그인 아이디 -> MEMBER 테이블의 LOGIN_ID를 참조

	private String title;// 질문 제목

	private String answer_A;// 답변 A

	private String answer_B;// 답변 B

	private String explanation;// 설명

	private int category;// 카테고리 PK ->CATEGORY 테이블의 CID를 참조

	private String qAccess;// 질문 승인 여부

	private String regdate;// 질문 생성 날짜

	// ------java---------------

	private String searchCondition;// 찾는 방법
	private int save;// 찜 여부
	private int answerCntA;// 답변 A 개수
	private int answerCntB;// 답변 B 개수
	private String s_category;// 카테고리
	private int cnt;// 질문 개수

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
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

	public String getS_category() {
		return s_category;
	}

	public void setS_category(String s_category) {
		this.s_category = s_category;
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

	public int getSave() {
		return save;
	}

	public void setSave(int save) {
		this.save = save;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAnswer_A() {
		return answer_A;
	}

	public void setAnswer_A(String answer_A) {
		this.answer_A = answer_A;
	}

	public String getAnswer_B() {
		return answer_B;
	}

	public void setAnswer_B(String answer_B) {
		this.answer_B = answer_B;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public String getqAccess() {
		return qAccess;
	}

	public void setqAccess(String qAccess) {
		this.qAccess = qAccess;
	}

	public String getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "QuestionDTO [qId=" + qId + ", 작성자 =" + loginId + ", 제목 =" + title + ", 답변A =" + answer_A + ", 답변B ="
				+ answer_B + ", 문제 설명 =" + explanation + ", 카테고리 =" + category + ", 상태값 =" + searchCondition + "]";
	}

}
