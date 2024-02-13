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

	//후원 등록 SQL
	private static final String SUPPPORT_INSERT = "INSERT INTO SUPPORT(SUID, LOGIN_ID, AMOUNT) VALUES((SELECT NVL(MAX(SUID),0) + 1 FROM SUPPORT), ?, ?) ";

	//유저 페이지 후원 랭킹 SQL
	//각각의 유저가 후원한 금액의 총금액으로 순위를 매겨 가져온다
	
	//(1)
	//RANK() 함수를 이용해 총 가격을 큰순으로 정렬하고 같은가격이면 날짜순으로 정렬하여 가져온다
	private static final String SELECTALL_RANKING = "SELECT \r\n"
			+ "    S.LOGIN_ID,\r\n"
			+ "    SUM(S.AMOUNT) AS TOTAL,\r\n"
			+ "    NVL(M.NAME,'탈퇴한 사용자') AS NAME,\r\n"
			+ "    RANK() OVER (ORDER BY SUM(S.AMOUNT) DESC, MIN(S.REG_DATE)) AS RANKING \r\n"
			+ "FROM \r\n"
			+ "    SUPPORT S \r\n"
			+ "LEFT OUTER JOIN \r\n"
			+ "    MEMBER M ON S.LOGIN_ID = M.LOGIN_ID \r\n"
			+ "WHERE \r\n"
			+ "    S.LOGIN_ID IS NOT NULL\r\n"
			+ "GROUP BY  \r\n"
			+ "    S.LOGIN_ID, M.NAME";
	
	//어드민 페이지 후원 랭킹 SQL
	//(1) + 가장 최신 후원일자
	//(2)
	//MAX()함수를 이용하여 가장 최신 후원 날짜를 가져온다 
	private static final String SELECTALL_RANKING_ADMIN="SELECT \r\n"
			+ "			 S.LOGIN_ID,\r\n"
			+ "			SUM(S.AMOUNT) AS TOTAL,\r\n"
			+ "			NVL(M.NAME,'탈퇴한 사용자') AS NAME\r\n"
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
	
	//최근 후원 순으로 정렬 SQL
	private static final String SELECTALL_DATE_ORDER_ADMIN="SELECT NVL(S.LOGIN_ID,'탈퇴한 사용자') AS LOGIN_ID,S.AMOUNT,NVL(M.NAME,'탈퇴한 사용자') AS NAME,S.REG_DATE FROM SUPPORT S JOIN MEMBER M ON S.LOGIN_ID = M.LOGIN_ID ORDER BY S.REG_DATE DESC";
	
	// 회원탈퇴시 'Support'을 null 값으로 변경 SQL
	private static final String SP_UPDATE = "UPDATE SUPPORT SET LOGIN_ID = NULL WHERE LOGIN_ID = ?";
	
	//총 후원 금액 SQL
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

		//System.out.println("후원dao도착");
		// 모델
		conn = JDBCUtil.connect();
		try {
			if (sDTO.getSearchCondition().equals("후원")) {
				pstmt = conn.prepareStatement(SUPPPORT_INSERT);
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
