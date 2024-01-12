package model.comment;

public class CommentDTO {
	private int cId;

	private int qId; // questions 테이블의 idx를 참조

	private String loginId; // users 테이블의 idx를 참조

	private String content;

	 

	//---------java----------

	private String searchCondition;

	private String memberName;

	private String memberLoginId;

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

	public String getMemberLoginId() {
		return memberLoginId;
	}

	public void setMemberLoginId(String memberLoginId) {
		this.memberLoginId = memberLoginId;
	}


  
}