package model.comment;

public class CommentDTO {
    private int cid;
    private int qid; // questions 테이블의 idx를 참조
    private int mid; // users 테이블의 idx를 참조
    private String content;
    
    
    //---------java----------
    
    private String searchCondition;
    private String memberName;
    private String memberLoginId;
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getQid() {
		return qid;
	}
	public void setQid(int qid) {
		this.qid = qid;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
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