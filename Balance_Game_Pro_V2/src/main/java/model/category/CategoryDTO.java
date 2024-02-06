package model.category;

public class CategoryDTO {
	
	private int cgId;//카테고리 pk
	
	private String category;//카테고리 이름

	// ------java-------
	
	private String searchCondition;//찾는 방식

	public int getCgId() {
		return cgId;
	}

	public void setCgId(int cgId) {
		this.cgId = cgId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}

}
