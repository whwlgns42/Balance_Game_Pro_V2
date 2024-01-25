package model.save;

import java.util.Date;

public class SaveDTO {
	private int sId;

	private int qId;

	private String loginId;

	 

	 

	//------java-------

	private String searchCondition;
	private String saveTitle;
	private String saveWriter;
	private String saveAnswer_A;
	private String saveAnswer_B;
	private String saveExplanation;



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

	public String getSaveWriter() {
		return saveWriter;
	}

	public void setSaveWriter(String saveWriter) {
		this.saveWriter = saveWriter;
	}

	public String getSaveAnswer_A() {
		return saveAnswer_A;
	}

	public void setSaveAnswer_A(String saveAnswer_A) {
		this.saveAnswer_A = saveAnswer_A;
	}


	public String getSaveAnswer_B() {
		return saveAnswer_B;
	}


	public void setSaveAnswer_B(String saveAnswer_B) {
		this.saveAnswer_B = saveAnswer_B;
	}

	public String getSaveExplanation() {
		return saveExplanation;
	}


	public void setSaveExplanation(String saveExplanation) {
		this.saveExplanation = saveExplanation;
	}


	@Override
	public String toString() {
		return "SaveDTO [sId=" + sId + ", qId=" + qId + ", loginId=" + loginId + ", searchCondition=" + searchCondition
				+ ", saveTitle=" + saveTitle + "]";
	}

    
}
