package model.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Util.JDBCUtil;

public class MemberDAO {
	private Connection conn;
	private PreparedStatement pstmt;

	// 회원가입 SQL
	private static final String INSERT = "INSERT INTO MEMBER (MID, LOGIN_ID, MPW, NAME, EMAIL, ADDRESS, GENDER, AGE) VALUES((SELECT NVL(MAX(MID),0) + 1 FROM MEMBER),?,?,?,?,?,?,?)";
	// 아이디 중복 체크 SQL
	private static final String SELECT_LOGIN_ID = "SELECT LOGIN_ID FROM MEMBER WHERE LOGIN_ID = ? ";
	// 로그인 SQL
	private static final String LOGIN = "SELECT MID, LOGIN_ID, MADMIN FROM MEMBER WHERE LOGIN_ID = ? AND MPW = ? ";
	// 비밀번호 2차인증 SQL
	private static final String CERTIFICATION = "SELECT LOGIN_ID, MPW FROM MEMBER WHERE LOGIN_ID = ? AND MPW = ? ";
	// 마이페이지 SQL
	private static final String MY_INFO = "SELECT LOGIN_ID, NAME, GENDER, EMAIL, ADDRESS FROM MEMBER WHERE LOGIN_ID = ? ";
	// 내정보 변경하기 SQL
	private static final String MY_INFO_UPDATE = "UPDATE MEMBER SET NAME = ?, EMAIL = ? WHERE LOGIN_ID = ? ";
	// 유저 전체 조회
	private static final String SELECTALL_USER = "SELECT MID, LOGIN_ID, MPW, NAME, EMAIL, ADDRESS, GENDER, AGE, GRADE FROM MEMBER";
	// 유저 상세 조회
	private static final String SELECTONE_USER = "SELECT MID, LOGIN_ID, MPW, NAME, EMAIL, ADDRESS, GENDER, AGE, GRADE FROM MEMBER WHERE LOGIN_ID = ?";
	
	public ArrayList<MemberDTO> selectAll(MemberDTO mDTO) { // 전체 검색
		ArrayList<MemberDTO> datas = new ArrayList<MemberDTO>();
		if (mDTO.getSearchCondition().equals("전체조회")) {
			System.out.println("1111");
			// 박찬우
			conn = JDBCUtil.connect();
			System.out.println("2222");
			try {
				System.out.println("3333");
				pstmt = conn.prepareStatement(SELECTALL_USER);
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					System.out.println("4444");
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
					datas.add(member);
				}
				System.out.println("555");
				rs.close();
			} catch (SQLException e) {
				System.out.println("6666");
				e.printStackTrace();
				return null;
			} finally {
				System.out.println("77777");
				JDBCUtil.disconnect(pstmt, conn);
			}
		}
		return datas;

	}

	public MemberDTO selectOne(MemberDTO mDTO) { // 단일 검색
		MemberDTO data = null;
		if (mDTO.getSearchCondition().equals("유저조회")) {
			// 박찬우
			conn = JDBCUtil.connect();
			try {
				pstmt = conn.prepareStatement(SELECTONE_USER);
				pstmt.setString(1, mDTO.getLoginId());
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()) {
					data = new MemberDTO();
					data.setmId(rs.getInt("MID"));
					data.setLoginId(rs.getString("LOGIN_ID"));
					data.setmPw(rs.getString("PW"));
					data.setName(rs.getString("NAME"));
					data.setEmail(rs.getString("EMAIL"));
					data.setAddress(rs.getString("ADDRESS"));
					data.setGender(rs.getString("GENDER"));
					data.setAge(rs.getInt("AGE"));
					data.setGrade(rs.getString("GRADE"));
					data.setmAdmin(rs.getString("ADMIN"));
				}
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}finally {
				JDBCUtil.disconnect(pstmt, conn);
			}
			
		} else if (mDTO.getSearchCondition().equals("로그인")) {
			// 손성용
			conn = JDBCUtil.connect();
			try {
				pstmt = conn.prepareStatement(LOGIN);
				pstmt.setString(1, mDTO.getLoginId());
				pstmt.setString(2, mDTO.getmPw());
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					data = new MemberDTO();
					data.setmId(rs.getInt("MID"));
					data.setLoginId(rs.getString("LOGIN_ID"));
					data.setmAdmin(rs.getString("MADMIN"));
				}
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}
		} else if (mDTO.getSearchCondition().equals("중복확인")) {
			// 조지훈
			conn = JDBCUtil.connect();
			try {
				pstmt = conn.prepareStatement(SELECT_LOGIN_ID);
				pstmt.setString(1, mDTO.getLoginId());
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					data = new MemberDTO();
				}
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}
		} else if (mDTO.getSearchCondition().equals("내정보")) {
			// 조지훈
			conn = JDBCUtil.connect();
			try {
				pstmt = conn.prepareStatement(MY_INFO);
				pstmt.setString(1, mDTO.getLoginId());
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					data = new MemberDTO();
					data.setLoginId(rs.getString("LOGIN_ID"));
					data.setName(rs.getString("NAME"));
					data.setGender(rs.getString("GENDER"));
					data.setEmail(rs.getString("EMAIL"));
					data.setAddress(rs.getString("ADDRESS"));
				}
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}
		}else if (mDTO.getSearchCondition().equals("2차인증")) {
			// 조지훈
			conn = JDBCUtil.connect();
			try {
				pstmt = conn.prepareStatement(CERTIFICATION);
				pstmt.setString(1, mDTO.getLoginId());
				pstmt.setString(2, mDTO.getmPw());
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					data = new MemberDTO();
					data.setLoginId(rs.getString("LOGIN_ID"));
					data.setmPw(rs.getString("MPW"));
				}
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}
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
		if (mDTO.getSearchCondition().equals("회원탈퇴")) {
			// 손성용
		} else if (mDTO.getSearchCondition().equals("회원변경")) { // input: loginId // output : 이메일, 이름 변경하기
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
		return false;
	}

}
