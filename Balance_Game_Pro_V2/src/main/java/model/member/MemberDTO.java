package model.member;

public class MemberDTO {
	
	private int mId;//유저 pk
	
	private String loginId;//로그인 아이디
	
	private String mPw;//비밀번호
	
	private String name;//이름
	
	private String email;//이메일

	private String address;//주소

	private String gender;//성별

	private int age;//나이

	private String grade;//후원 등급

	private String mAdmin;//어드민 유무

	private String cellPhone;//전화번호

	// --------java--------

	private String ranking; //후원 등수
	private int total; //후원 금액
	private String searchCondition;//찾는 방법
	private int cnt;//회원수 

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public int getmId() {
		return mId;
	}

	public void setmId(int mId) {
		this.mId = mId;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getmPw() {
		return mPw;
	}

	public void setmPw(String mPw) {
		this.mPw = mPw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getmAdmin() {
		return mAdmin;
	}

	public void setmAdmin(String mAdmin) {
		this.mAdmin = mAdmin;
	}

	public String getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}

	public String getRanking() {
		return ranking;
	}

	public void setRanking(String ranking) {
		this.ranking = ranking;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "MemberDTO [mId=" + mId + ", loginId=" + loginId + ", mPw=" + mPw + ", name=" + name + ", email=" + email
				+ ", address=" + address + ", gender=" + gender + ", age=" + age + ", grade=" + grade + ", mAdmin="
				+ mAdmin + ", cellPhone=" + cellPhone + ", ranking=" + ranking + ", searchCondition=" + searchCondition
				+ "]";
	}

}