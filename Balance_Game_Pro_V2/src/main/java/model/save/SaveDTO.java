package model.save;

import java.util.Date;

public class SaveDTO {
	private int sId;

	private int qId;

	private String loginId;

	 

	 

	//------java-------

	private String searchCondition;
	private String saveTitle;





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


	@Override
	public String toString() {
		return "SaveDTO [sId=" + sId + ", qId=" + qId + ", loginId=" + loginId + ", searchCondition=" + searchCondition
				+ ", saveTitle=" + saveTitle + "]";
	}

    
}
