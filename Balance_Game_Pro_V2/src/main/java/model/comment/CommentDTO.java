package model.comment;

public class CommentDTO {
	
	private int cId;//댓글 PK
	
	private int qId; //질문 PK ->QUESTIONS 테이블의 QID를 참조
	
	private String loginId; //로그인 아이디 -> MEMBER 테이블의 LOGIN_ID를 참조
	
	private String content;//댓글 내용

	 

	//---------java----------
	
	private String searchCondition;//찾는 방법
	
	private String memberName;//유저 이름
	
	private String grade;//유저 후원 등급
	


	
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}



	@Override
	public String toString() {
		return "CommentDTO [cId=" + cId + ", qId=" + qId + ", loginId=" + loginId + ", content=" + content
				+ ", searchCondition=" + searchCondition + ", memberName=" + memberName + ", grade=" + grade + "]";
	}

}