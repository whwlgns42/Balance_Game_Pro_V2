package model.support;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import model.util.JDBCUtil;

public class SupportDAO {
	private Connection conn;
	private PreparedStatement pstmt;

	private static final String SUPPPORT_AMOUNT = "INSERT INTO SUPPORT(SUID, LOGIN_ID, AMOUNT) VALUES((SELECT NVL(MAX(SUID),0) + 1 FROM SUPPORT), ?, ?) ";

	private static final String SELECTALL = "SELECT S.LOGIN_ID, SUM(S.AMOUNT) \"TOTAL\", M.NAME FROM SUPPORT S JOIN MEMBER M ON S.LOGIN_ID = M.LOGIN_ID GROUP BY S.LOGIN_ID, M.NAME ORDER BY total DESC";

	private static final String SELECTALL_RANKING = "SELECT \r\n"
			+ "    S.LOGIN_ID,\r\n"
			+ "    SUM(S.AMOUNT) AS TOTAL,\r\n"
			+ "    M.NAME,\r\n"
			+ "    RANK() OVER (ORDER BY SUM(S.AMOUNT) DESC, MIN(S.REG_DATE)) AS RANKING \r\n"
			+ "FROM \r\n"
			+ "    SUPPORT S \r\n"
			+ "LEFT OUTER JOIN \r\n"
			+ "    MEMBER M ON S.LOGIN_ID = M.LOGIN_ID \r\n"
			+ "WHERE \r\n"
			+ "    S.LOGIN_ID IS NOT NULL\r\n"
			+ "GROUP BY  \r\n"
			+ "    S.LOGIN_ID, M.NAME";

	
	private static final String SELECTALL_RANKING_ADMIN="SELECT \r\n"
			+ "			 S.LOGIN_ID,\r\n"
			+ "			SUM(S.AMOUNT) AS TOTAL,\r\n"
			+ "			M.NAME,\r\n"
			+ "			RANK() OVER (ORDER BY SUM(S.AMOUNT) DESC, MIN(S.REG_DATE)) AS RANKING,\r\n"
			+ "			MAX(S.REG_DATE) AS LAST_SUPPORT_DATE \r\n"
			+ "			FROM \r\n"
			+ "			 SUPPORT S \r\n"
			+ "			LEFT OUTER JOIN\r\n"
			+ "			  MEMBER M ON S.LOGIN_ID = M.LOGIN_ID \r\n"
			+ "			WHERE \r\n"
			+ "			S.LOGIN_ID IS NOT NULL\r\n"
			+ "			GROUP BY  \r\n"
			+ "			 S.LOGIN_ID, M.NAME";
	private static final String SELECTALL_DATE_ORDER_ADMIN="SELECT S.LOGIN_ID,S.AMOUNT, M.NAME,S.REG_DATE FROM SUPPORT S JOIN MEMBER M ON S.LOGIN_ID = M.LOGIN_ID ORDER BY S.REG_DATE ASC";
	
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
			}else if(sDTO.getSearchCondition().equals("후원순")) {
				pstmt = conn.prepareStatement(SELECTALL_RANKING_ADMIN);

				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					SupportDTO data = new SupportDTO();
					data.setTotal(rs.getInt("TOTAL"));
					data.setLoginId(rs.getString("LOGIN_ID"));
					data.setName(rs.getString("NAME"));
					data.setRanking(rs.getInt("RANKING"));
					data.setDate(rs.getDate("LAST_SUPPORT_DATE"));
					datas.add(data);
				}
				rs.close();
			}else if(sDTO.getSearchCondition().equals("최신순")) {
				pstmt = conn.prepareStatement(SELECTALL_DATE_ORDER_ADMIN);

				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					SupportDTO data = new SupportDTO();
					data.setAmount(rs.getInt("AMOUNT"));
					data.setLoginId(rs.getString("LOGIN_ID"));
					data.setName(rs.getString("NAME"));
					data.setDate(rs.getDate("REG_DATE"));
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
