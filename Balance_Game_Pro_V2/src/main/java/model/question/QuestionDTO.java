package model.question;

import java.util.Date;

public class QuestionDTO {
	private int qId;

	private String writer; // 이 경우, 작성자는 User 테이블의 idx 컬럼을 참조할 수 있습니다.

	private String title;

	private String answer_A;

	private String answer_B;

	private String explanation;

	private String category;

	private String qAccess;

	 

	//------java---------------

	 

	private String searchCondition;
	private boolean save;


	public boolean isSave() {
		return save;
	}



	public void setSave(boolean save) {
		this.save = save;
	}



	public int getqId() {
		return qId;
	}



	public void setqId(int qId) {
		this.qId = qId;
	}






	public String getWriter() {
		return writer;
	}



	public void setWriter(String writer) {
		this.writer = writer;
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



	public String getCategory() {
		return category;
	}



	public void setCategory(String category) {
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



	@Override
	public String toString() {
		return "QuestionDTO [qId=" + qId + ", 작성자 =" + writer + ", 제목 =" + title + ", 답변A =" + answer_A
				+ ", 답변B =" + answer_B + ", 문제 설명 =" + explanation + ", 카테고리 =" + category
				+ ", 상태값 =" + searchCondition + "]";
	}
	
	
    
    
	

}
