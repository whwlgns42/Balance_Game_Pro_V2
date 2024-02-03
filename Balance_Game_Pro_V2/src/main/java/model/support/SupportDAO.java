package model.support;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.question.QuestionDTO;
import model.util.JDBCUtil;

public class SupportDAO {
	private Connection conn;
	private PreparedStatement pstmt;

	private static final String SUPPPORT_AMOUNT = "INSERT INTO SUPPORT(SUID, LOGIN_ID, AMOUNT) VALUES((SELECT NVL(MAX(SUID),0) + 1 FROM SUPPORT), ?, ?) ";

	private static final String SELECTALL = "SELECT S.LOGIN_ID, SUM(S.AMOUNT) \"TOTAL\", M.NAME FROM SUPPORT S JOIN MEMBER M ON S.LOGIN_ID = M.LOGIN_ID GROUP BY S.LOGIN_ID, M.NAME ORDER BY total DESC";

	private static final String SELECTALL_RANKING = "SELECT  NVL( S.LOGIN_ID,'탈퇴한 사용자') AS LOGIN_ID,\r\n"
			+ "			SUM(S.AMOUNT) AS TOTAL, NVL( M.NAME,'탈퇴한 사용자')AS NAME,\r\n"
			+ "			 RANK() OVER (ORDER BY SUM(S.AMOUNT) DESC) AS RANKING FROM SUPPORT S \r\n"
			+ "			LEFT OUTER JOIN  MEMBER M ON S.LOGIN_ID = M.LOGIN_ID GROUP BY \r\n"
			+ "			 S.LOGIN_ID, M.NAME ORDER BY RANKING";

	// 회원탈퇴시 'Support'을 null 값으로 변경
	private static final String SP_UPDATE = "UPDATE SUPPORT SET LOGIN_ID = NULL WHERE LOGIN_ID = ?";
	
	//총 후원 금액
		private static final String SELECT_CNT="SELECT SUM(AMOUNT) AS TOTAL FROM SUPPORT";

	public ArrayList<SupportDTO> selectAll(SupportDTO sDTO) {
		ArrayList<SupportDTO> datas = new ArrayList<SupportDTO>();

		conn = JDBCUtil.connect();
		try {
			if (sDTO.getSearchCondition().equals("후원목록")) {
				// 모델
				pstmt = conn.prepareStatement(SELECTALL_RANKING);

				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					SupportDTO data = new SupportDTO();
					data.setTotal(rs.getInt("TOTAL"));
					data.setLoginId(rs.getString("LOGIN_ID"));
					data.setName(rs.getString("NAME"));
					data.setRanking(rs.getInt("RANKING"));
					datas.add(data);
				}

				rs.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return datas;

	}

	public SupportDTO selectOne(SupportDTO sDTO) {
		conn = JDBCUtil.connect();
		SupportDTO data = null;
		try {
			if (sDTO.getSearchCondition().equals("총후원금액")) {
				pstmt = conn.prepareStatement(SELECT_CNT);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					data = new SupportDTO();
					data.setTotal(rs.getInt("TOTAL"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}

		return data;
	}

	public boolean insert(SupportDTO sDTO) {

		System.out.println("후원dao도착");
		// 모델
		conn = JDBCUtil.connect();
		try {
			if (sDTO.getSearchCondition().equals("후원")) {
				pstmt = conn.prepareStatement(SUPPPORT_AMOUNT);
				pstmt.setString(1, sDTO.getLoginId());
				pstmt.setInt(2, sDTO.getAmount());
				int result = pstmt.executeUpdate();
				if (result <= 0) {
					return false;
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}

		return true;
	}

	public boolean update(SupportDTO sDTO) {

		conn = JDBCUtil.connect();

		try {
			// 손성용
			if (sDTO.getSearchCondition().equals("support_null")) {
				pstmt = conn.prepareStatement(SP_UPDATE);
				pstmt.setString(1, sDTO.getLoginId());
				int result = pstmt.executeUpdate();
				if (result <= 0) {
					return false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return true;
	}

	public boolean delete(SupportDTO sDTO) {
		return false;
	}
}
