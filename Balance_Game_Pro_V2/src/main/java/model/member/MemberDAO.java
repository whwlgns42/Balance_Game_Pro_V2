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

	// CELL_PHONE
	// 회원가입 SQL
	private static final String INSERT = "INSERT INTO MEMBER (MID, LOGIN_ID, MPW, NAME, EMAIL, ADDRESS, GENDER, AGE, CELL_PHONE) VALUES((SELECT NVL(MAX(MID),0) + 1 FROM MEMBER),?,?,?,?,?,?,?,?)";

	// 아이디 중복 체크 SQL
	private static final String SELECT_LOGIN_ID = "SELECT LOGIN_ID FROM MEMBER WHERE LOGIN_ID = ? ";
	// 로그인 SQL
	private static final String LOGIN = "SELECT MID, LOGIN_ID, MADMIN FROM MEMBER WHERE LOGIN_ID = ? AND MPW = ? ";
	// 비밀번호 2차인증 SQL
	private static final String CERTIFICATION = "SELECT LOGIN_ID, MPW FROM MEMBER WHERE LOGIN_ID = ? AND MPW = ? ";

	// CELL_PHONE
	// 마이페이지 SQL
	private static final String MY_INFO = "SELECT LOGIN_ID, NAME, AGE, GENDER, EMAIL, ADDRESS, CELL_PHONE FROM MEMBER WHERE LOGIN_ID = ? ";

	// 내정보 변경하기 SQL
	private static final String MY_INFO_UPDATE = "UPDATE MEMBER SET NAME = ?, EMAIL = ? WHERE LOGIN_ID = ? ";

	// CELL_PHONE
	// 유저 전체 조회
	private static final String SELECTALL_USER = "SELECT \r\n"
			+ "    M.MID, M.LOGIN_ID, M.MPW, M.NAME, M.EMAIL, M.ADDRESS, M.GENDER, M.AGE, M.GRADE, M.CELL_PHONE, \r\n"
			+ "    NVL(SUM(S.AMOUNT), 0) AS TOTAL, \r\n"
			+ "    RANK() OVER (ORDER BY COALESCE(SUM(S.AMOUNT), 0) DESC) AS RANKING \r\n" + "FROM \r\n"
			+ "    MEMBER M\r\n" + "LEFT JOIN \r\n" + "    SUPPORT S ON M.LOGIN_ID = S.LOGIN_ID\r\n" + "GROUP BY \r\n"
			+ "    M.MID, M.LOGIN_ID, M.MPW, M.NAME, M.EMAIL, M.ADDRESS, M.GENDER, M.AGE, M.GRADE, M.CELL_PHONE";

	// CELL_PHONE
	// 유저 상세 조회
	private static final String SELECTONE_USER = "SELECT MID, LOGIN_ID, MPW, NAME, EMAIL, ADDRESS, GENDER, AGE, GRADE, CELL_PHONE "
			+ "FROM MEMBER WHERE LOGIN_ID = ?";

	// 유저 삭제
	private static final String DELETE = "DELETE FROM MEMBER WHERE LOGIN_ID = ?";

	// 회원 수
	private static final String SELECT_CNT = "SELECT COUNT(1) AS CNT FROM MEMBER";

	//어드민 확인
	private static final String SELECT_ADMIN="SELECT MADMIN FROM MEMBER WHERE LOGIN_ID = ?";
	
	public ArrayList<MemberDTO> selectAll(MemberDTO mDTO) { // 전체 검색
      ArrayList<MemberDTO> datas = new ArrayList<MemberDTO>();
      if (mDTO.getSearchCondition().equals("전체조회")) {
         // 박찬우
         conn = JDBCUtil.connect();
         try {
            pstmt = conn.prepareStatement(SELECTALL_USER);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
               MemberDTO member = new MemberDTO();
               member.setmId(rs.getInt("MID"));
               member.setLoginId(rs.getString("LOGIN_ID"));
               member.setmPw(rs.getString("MPW"));
               member.setName(rs.getString("NAME"));
               member.setEmail(rs.getString("EMAIL"));
               member.setAddress(rs.getString("ADDRESS"));
               member.setGender(rs.getString("GENDER"));
               member.setAge(rs.getInt("AGE"));
               member.setGrade(rs.getString("GRADE"));
               member.setCellPhone(rs.getString("CELL_PHONE"));
               member.setTotal(rs.getInt("TOTAL"));
               member.setRanking(rs.getInt("RANKING"));
               datas.add(member);
            }
            rs.close();
 		} catch (SQLException e) {
 			e.printStackTrace();
 			return null;
 		} finally {
 			JDBCUtil.disconnect(pstmt, conn);
 		}

      }
      return datas;

   }

	public MemberDTO selectOne(MemberDTO mDTO) { // 단일 검색
		MemberDTO data = null;
		ResultSet rs = null;
		conn = JDBCUtil.connect();
		try {
			if (mDTO.getSearchCondition().equals("유저조회")) {
				// 박찬우
				pstmt = conn.prepareStatement(SELECTONE_USER);
				pstmt.setString(1, mDTO.getLoginId());
				rs = pstmt.executeQuery();
				if (rs.next()) {
					data = new MemberDTO();
					data.setmId(rs.getInt("MID"));
					data.setLoginId(rs.getString("LOGIN_ID"));
					data.setmPw(rs.getString("MPW"));
					data.setName(rs.getString("NAME"));
					data.setEmail(rs.getString("EMAIL"));
					data.setAddress(rs.getString("ADDRESS"));
					data.setGender(rs.getString("GENDER"));
					data.setAge(rs.getInt("AGE"));
					data.setGrade(rs.getString("GRADE"));
					data.setCellPhone(rs.getString("CELL_PHONE"));
				}

			} else if (mDTO.getSearchCondition().equals("로그인")) {
				// 손성용

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

			} else if (mDTO.getSearchCondition().equals("중복확인")) {
				// 조지훈

				pstmt = conn.prepareStatement(SELECT_LOGIN_ID);
				pstmt.setString(1, mDTO.getLoginId());
				rs = pstmt.executeQuery();
				if (rs.next()) {
					data = new MemberDTO();
					data.setLoginId(rs.getString("LOGIN_ID"));
				}

			} else if (mDTO.getSearchCondition().equals("내정보")) {
				// 조지훈

				pstmt = conn.prepareStatement(MY_INFO);
				pstmt.setString(1, mDTO.getLoginId());
				rs = pstmt.executeQuery();
				if (rs.next()) {
					data = new MemberDTO();
					data.setLoginId(rs.getString("LOGIN_ID"));
					data.setName(rs.getString("NAME"));
					data.setGender(rs.getString("GENDER"));
					data.setEmail(rs.getString("EMAIL"));
					data.setAddress(rs.getString("ADDRESS"));
					data.setAge(rs.getInt("AGE"));
					data.setCellPhone(rs.getString("CELL_PHONE"));
				}

			} else if (mDTO.getSearchCondition().equals("2차인증")) {
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

			} else if (mDTO.getSearchCondition().equals("회원인원수")) {

				pstmt = conn.prepareStatement(SELECT_CNT);

				rs = pstmt.executeQuery();
				if (rs.next()) {
					data = new MemberDTO();
					data.setCnt(rs.getInt("CNT"));
				}

			}else if(mDTO.getSearchCondition().equals("관리자")) {
				pstmt = conn.prepareStatement(SELECT_ADMIN);
				pstmt.setString(1, mDTO.getLoginId());
				rs = pstmt.executeQuery();
				if (rs.next()) {
					data = new MemberDTO();
					data.setmAdmin(rs.getString("MADMIN"));
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

	public boolean update(MemberDTO mDTO) { // 개인정보 변경 (추후 구현 예정)
		if (mDTO.getSearchCondition().equals("내정보변경")) { // input: loginId // output : 이메일, 이름 변경하기
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