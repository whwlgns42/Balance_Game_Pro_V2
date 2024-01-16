package model.support;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Util.JDBCUtil;

public class SupportDAO {
	private Connection conn;
	private PreparedStatement pstmt;

	private static final String SUPPPORT_AMOUNT = "INSERT INTO SUPPORT(SUID, LOGIN_ID, AMOUNT) VALUES((SELECT NVL(MAX(SUID),0) + 1 FROM SUPPORT), ?, ?) ";

	public ArrayList<SupportDTO> selectAll(SupportDTO sDTO) {

		if (sDTO.getSearchCondition().equals("후원목록")) {
			// 모델
		}

		return null;

	}

	public SupportDTO selectOne(SupportDTO sDTO) {

		return null;
	}

	public boolean insert(SupportDTO sDTO) {
		if (sDTO.getSearchCondition().equals("후원")) {
			System.out.println("후원dao도착");
			// 모델
			conn = JDBCUtil.connect();
			try {
				pstmt = conn.prepareStatement(SUPPPORT_AMOUNT);
				pstmt.setString(1, sDTO.getLoginId());
				pstmt.setInt(2, sDTO.getAmount());
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
		}
		return true;
	}

	public boolean update(SupportDTO sDTO) {
		return false;
	}

	public boolean delete(SupportDTO sDTO) {
		return false;
	}
}
