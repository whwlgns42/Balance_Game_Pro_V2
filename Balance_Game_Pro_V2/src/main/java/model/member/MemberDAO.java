package model.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.question.QuestionDTO;
import model.util.JDBCUtil;

public class MemberDAO {
	private Connection conn;
	private PreparedStatement pstmt;

	// 회원가입 SQL
	private static final String INSERT = "INSERT INTO MEMBER (MID, LOGIN_ID, MPW, NAME, EMAIL, ADDRESS, GENDER, AGE, CELL_PHONE) VALUES((SELECT NVL(MAX(MID),0) + 1 FROM MEMBER),?,?,?,?,?,?,?,?)";

	// 아이디 중복 체크 SQL
	private static final String SELECT_LOGIN_ID = "SELECT LOGIN_ID FROM MEMBER WHERE LOGIN_ID = ? ";
	// 로그인 SQL
	private static final String LOGIN = "SELECT MID, LOGIN_ID, MADMIN FROM MEMBER WHERE LOGIN_ID = ? AND MPW = ? ";
	// 비밀번호 2차인증 SQL
	private static final String CERTIFICATION = "SELECT LOGIN_ID, MPW FROM MEMBER WHERE LOGIN_ID = ? AND MPW = ? ";
	
	// 내정보 변경하기 SQL
	private static final String MY_INFO_UPDATE = "UPDATE MEMBER SET NAME = ?, EMAIL = ? WHERE LOGIN_ID = ? ";



	//유저 전체 조회 SQL
	//유저들을 가져올때
	//후원 랭킹,후원 총 금액를 가져옴
	//후원 랭킹을 가져올때 랭킹순으로 정렬
	//후원 총 금액을를 가져올때 총 금액이 0원이라면 - 으로 해야함
	
	//(1)
	//TOTAL로 RANK() 함수를 이용해서 큰 순으로 정렬하고 TOTAL이 같은 값이면 먼저 후원한 순으로 정렬한다
	
	//(2)
	//만약 TOTAL이 0이라면 - 을 넣는다
	//하지만 - 는 문자형이기 때문에 RANK()로 나온 값을 TO_CHAR를 형변환하여 가져온다
	private static final String SELECTALL_USER = "SELECT \r\n"
			+ "			 M.MID, M.LOGIN_ID, M.MPW, M.NAME, M.EMAIL, M.ADDRESS, M.GENDER, M.AGE, M.GRADE, M.CELL_PHONE, \r\n"
			+ "			  NVL(SUM(S.AMOUNT), 0) AS TOTAL, \r\n" + "			CASE\r\n"
			+ "        		WHEN NVL(SUM(S.AMOUNT), 0) = 0 THEN '-'\r\n"
			+ "       			ELSE TO_CHAR(RANK() OVER (ORDER BY NVL(SUM(S.AMOUNT), 0) DESC, MIN(S.REG_DATE)))\r\n"
			+ "    		END AS RANKING  \r\n" + "    		FROM \r\n"
			+ "			 MEMBER M LEFT JOIN     SUPPORT S ON M.LOGIN_ID = S.LOGIN_ID GROUP BY \r\n"
			+ "			 M.MID, M.LOGIN_ID, M.MPW, M.NAME, M.EMAIL, M.ADDRESS, M.GENDER, M.AGE, M.GRADE, M.CELL_PHONE";
	
	
	//유저 전체 조회 SQL + 이름으로 검색
	//(3)
	//LIKE문으로 입력된 문자열을 포함하는 이름을 찾아 가져온다
	private static final String SELECTALL_USER_NAME="SELECT \r\n"
			+ "			M.MID, M.LOGIN_ID, M.MPW, M.NAME, M.EMAIL, M.ADDRESS, M.GENDER, M.AGE, M.GRADE, M.CELL_PHONE,\r\n"
			+ "				  NVL(SUM(S.AMOUNT), 0) AS TOTAL,	CASE\r\n"
			+ "				WHEN NVL(SUM(S.AMOUNT), 0) = 0 THEN '-'\r\n"
			+ "				ELSE TO_CHAR(RANK() OVER (ORDER BY NVL(SUM(S.AMOUNT), 0) DESC, MIN(S.REG_DATE)))\r\n"
			+ "			END AS RANKING 		FROM\r\n"
			+ "				 MEMBER M LEFT JOIN     SUPPORT S ON M.LOGIN_ID = S.LOGIN_ID \r\n"
			+ "			WHERE M.NAME LIKE '%'||?||'%'\r\n"
			+ "		GROUP BY \r\n"
			+ "			 M.MID, M.LOGIN_ID, M.MPW, M.NAME, M.EMAIL, M.ADDRESS, M.GENDER, M.AGE, M.GRADE, M.CELL_PHONE"
			+ "";
	//유저 전체 조회 SQL + 아이디로 검색
	//(3)
	//LIKE문으로 입력된 문자열을 포함하는 로그인아이디를 찾아 가져온다
	private static final String SELECTALL_USER_LOGIN_ID="SELECT \r\n"
			+ "			M.MID, M.LOGIN_ID, M.MPW, M.NAME, M.EMAIL, M.ADDRESS, M.GENDER, M.AGE, M.GRADE, M.CELL_PHONE,\r\n"
			+ "				  NVL(SUM(S.AMOUNT), 0) AS TOTAL,	CASE\r\n"
			+ "				WHEN NVL(SUM(S.AMOUNT), 0) = 0 THEN '-'\r\n"
			+ "				ELSE TO_CHAR(RANK() OVER (ORDER BY NVL(SUM(S.AMOUNT), 0) DESC, MIN(S.REG_DATE)))\r\n"
			+ "			END AS RANKING 		FROM\r\n"
			+ "				 MEMBER M LEFT JOIN     SUPPORT S ON M.LOGIN_ID = S.LOGIN_ID \r\n"
			+ "			WHERE M.LOGIN_ID LIKE '%'||?||'%'\r\n"
			+ "		GROUP BY \r\n"
			+ "			 M.MID, M.LOGIN_ID, M.MPW, M.NAME, M.EMAIL, M.ADDRESS, M.GENDER, M.AGE, M.GRADE, M.CELL_PHONE";
	

	// 유저 상세 조회 SQL
	private static final String SELECTONE_USER = "SELECT LOGIN_ID, NAME, EMAIL, ADDRESS, GENDER, AGE, CELL_PHONE "
			+ "FROM MEMBER WHERE LOGIN_ID = ?";

	// 유저 삭제 SQL
	private static final String DELETE = "DELETE FROM MEMBER WHERE LOGIN_ID = ?";

	// 회원 수 SQL
	// 1로 한 이유
	//성능면에서는 차이가 거의 없다
	//하지만 데이터를 받아오면 코드를 읽는 사람들이 해당 열이 사용된다고 오해를 할 수 있다
	//따라서 이를 방지하기 위해 1로 지정하여 가독성을 높였습니다  
	private static final String SELECT_CNT = "SELECT COUNT(1) AS CNT FROM MEMBER";



	public ArrayList<MemberDTO> selectAll(MemberDTO mDTO) { // 전체 검색
		ArrayList<MemberDTO> datas = new ArrayList<MemberDTO>();
		conn = JDBCUtil.connect();
		try {
			//조회하여 가져오는 데이터는 모두 같기 때문에 하나의 while으로 사용
			if (mDTO.getSearchCondition().equals("전체조회")) {
				pstmt = conn.prepareStatement(SELECTALL_USER);
				
			}else if(mDTO.getSearchCondition().equals("이름조회")) {
				pstmt = conn.prepareStatement(SELECTALL_USER_NAME);
				pstmt.setString(1, mDTO.getName());
				
				
			}else if(mDTO.getSearchCondition().equals("아이디조회")) {
				pstmt = conn.prepareStatement(SELECTALL_USER_LOGIN_ID);
				pstmt.setString(1, mDTO.getLoginId());
			}
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				MemberDTO member = new MemberDTO();
				member.setmId(rs.getInt("MID"));
				member.setLoginId(rs.getString("LOGIN_ID"));
				member.setName(rs.getString("NAME"));
				member.setEmail(rs.getString("EMAIL"));
				member.setAddress(rs.getString("ADDRESS"));
				member.setGender(rs.getString("GENDER"));
				member.setAge(rs.getInt("AGE"));
				member.setTotal(rs.getInt("TOTAL"));
				member.setRanking(rs.getString("RANKING"));

				datas.add(member);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}

		return datas;

	}

	public MemberDTO selectOne(MemberDTO mDTO) { // 단일 검색
		MemberDTO data = null;
		ResultSet rs = null;
		conn = JDBCUtil.connect();
		try {
			if (mDTO.getSearchCondition().equals("유저조회")) {//유저 상세 조회
				// 박찬우
				pstmt = conn.prepareStatement(SELECTONE_USER);
				pstmt.setString(1, mDTO.getLoginId());
				rs = pstmt.executeQuery();
				if (rs.next()) {
					data = new MemberDTO();
					data.setLoginId(rs.getString("LOGIN_ID"));
					data.setName(rs.getString("NAME"));
					data.setEmail(rs.getString("EMAIL"));
					data.setAddress(rs.getString("ADDRESS"));
					data.setGender(rs.getString("GENDER"));
					data.setAge(rs.getInt("AGE"));
					data.setCellPhone(rs.getString("CELL_PHONE"));
				}

			} else if (mDTO.getSearchCondition().equals("로그인")) {//로그인 조회

				pstmt = conn.prepareStatement(LOGIN);
				pstmt.setString(1, mDTO.getLoginId());
				pstmt.setString(2, mDTO.getmPw());
				rs = pstmt.executeQuery();
				if (rs.next()) {
					data = new MemberDTO();
					data.setmId(rs.getInt("MID"));
					data.setLoginId(rs.getString("LOGIN_ID"));
					data.setmAdmin(rs.getString("MADMIN"));
				}

			} else if (mDTO.getSearchCondition().equals("중복확인")) {//회원가입시 아이디 중복확인
				// 조지훈

				pstmt = conn.prepareStatement(SELECT_LOGIN_ID);
				pstmt.setString(1, mDTO.getLoginId());
				rs = pstmt.executeQuery();
				if (rs.next()) {
					data = new MemberDTO();
					data.setLoginId(rs.getString("LOGIN_ID"));
				}

			}  else if (mDTO.getSearchCondition().equals("2차인증")) {//마이페이지 접근시 비밀번호 확인
				// 조지훈

				pstmt = conn.prepareStatement(CERTIFICATION);
				pstmt.setString(1, mDTO.getLoginId());
				pstmt.setString(2, mDTO.getmPw());
				rs = pstmt.executeQuery();
				if (rs.next()) {
					data = new MemberDTO();
					data.setLoginId(rs.getString("LOGIN_ID"));
					data.setmPw(rs.getString("MPW"));
				}

			} else if (mDTO.getSearchCondition().equals("회원인원수")) {//회원수

				pstmt = conn.prepareStatement(SELECT_CNT);

				rs = pstmt.executeQuery();
				if (rs.next()) {
					data = new MemberDTO();
					data.setCnt(rs.getInt("CNT"));
				}

			} 
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return data;
	}

	public boolean insert(MemberDTO mDTO) { // 회원가입
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(INSERT);
			pstmt.setString(1, mDTO.getLoginId());
			pstmt.setString(2, mDTO.getmPw());
			pstmt.setString(3, mDTO.getName());
			pstmt.setString(4, mDTO.getEmail());
			pstmt.setString(5, mDTO.getAddress());
			pstmt.setString(6, mDTO.getGender());
			pstmt.setInt(7, mDTO.getAge());
			pstmt.setString(8, mDTO.getCellPhone());
			int result = pstmt.executeUpdate();
			if (result <= 0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return true;
	}

	public boolean update(MemberDTO mDTO) { // 개인정보 변경 
		if (mDTO.getSearchCondition().equals("내정보변경")) { 
			// 모델
			conn = JDBCUtil.connect();
			try {
				pstmt = conn.prepareStatement(MY_INFO_UPDATE);
				pstmt.setString(1, mDTO.getName());
				pstmt.setString(2, mDTO.getEmail());
				pstmt.setString(3, mDTO.getLoginId());
				int result = pstmt.executeUpdate();
				if (result <= 0) {
					return false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	public boolean delete(MemberDTO mDTO) { // 회원탈퇴

		// 손성용

		conn = JDBCUtil.connect();

		try {
			pstmt = conn.prepareStatement(DELETE);
			pstmt.setString(1, mDTO.getLoginId());
			int result = pstmt.executeUpdate();
			if (result <= 0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return true;

	}
}