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
	// 아이디 중복 체크
	private static final String SELECT_LOGIN_ID = "SELECT LOGIN_ID FROM MEMBER WHERE LOGIN_ID = ? ";

	public ArrayList<MemberDTO> selectAll(MemberDTO mDTO) { // 전체 검색
		if (mDTO.getSearchCondition().equals("전체조회")) {
			// 박찬우
		}

		return null;
	}

	public MemberDTO selectOne(MemberDTO mDTO) { // 단일 검색
		MemberDTO data = null;
		if (mDTO.getSearchCondition().equals("유저조회")) {
			// 박찬우
		} else if (mDTO.getSearchCondition().equals("로그인")) {
			// 손성용
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
		} else if (mDTO.getSearchCondition().equals("회원변경")) {
			// 모델
		}

		return false;
	}

	public boolean delete(MemberDTO mDTO) { // 회원탈퇴
		// 손성용
		return false;
	}

}
