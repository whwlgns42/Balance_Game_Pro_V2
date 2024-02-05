package model.suggestion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.util.JDBCUtil;

public class SuggestionDAO {

	private Connection conn;
	private PreparedStatement pstmt;

	private static final String SELECTALL = "SELECT SUGID, NVL(LOGIN_ID,'탈퇴한 사용자') AS LOGIN_ID, TITLE FROM SUGGESTION ORDER BY REG_DATE DESC";

	private static final String SELECTONE = "SELECT SUGID, NVL(LOGIN_ID,'탈퇴한 사용자') AS LOGIN_ID, TITLE,SUGGESTION FROM SUGGESTION WHERE SUGID=?";

	private static final String INSERT = "INSERT INTO SUGGESTION (SUGID, LOGIN_ID,SUGGESTION,TITLE) VALUES((SELECT NVL(MAX(SUGID),0) + 1 FROM SUGGESTION),?,?,?)";

	private static final String DELETE = "DELETE FROM SUGGESTION WHERE SUGID=?";

	public ArrayList<SuggestionDTO> selectAll(SuggestionDTO sDTO) {

		conn = JDBCUtil.connect();
		ArrayList<SuggestionDTO> datas = new ArrayList<SuggestionDTO>();
		try {

			if (sDTO.getSearchCondition().equals("전체조회")) {
				pstmt = conn.prepareStatement(SELECTALL);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					SuggestionDTO data = new SuggestionDTO();
					data.setSugId(rs.getInt("SUGID"));
					data.setTitle(rs.getString("TITLE"));
					data.setLoginId(rs.getString("LOGIN_ID"));
					datas.add(data);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return datas;

	}

	public SuggestionDTO selectOne(SuggestionDTO sDTO) {

		conn = JDBCUtil.connect();
		SuggestionDTO data = null;
		try {
			pstmt = conn.prepareStatement(SELECTONE);
			pstmt.setInt(1, sDTO.getSugId());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				data = new SuggestionDTO();
				data.setSugId(rs.getInt("SUGID"));
				data.setTitle(rs.getString("TITLE"));
				data.setLoginId(rs.getString("LOGIN_ID"));
				data.setSuggestion(rs.getString("SUGGESTION"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return data;
	}

	public boolean insert(SuggestionDTO sDTO) {
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(INSERT);
			pstmt.setString(1, sDTO.getLoginId());
			pstmt.setString(2, sDTO.getSuggestion());
			pstmt.setString(3, sDTO.getTitle());
			int rs = pstmt.executeUpdate();
			if (rs <= 0) {
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

	public boolean update(SuggestionDTO sDTO) {
		return false;
	}

	public boolean delete(SuggestionDTO sDTO) {

		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(DELETE);
			pstmt.setInt(1, sDTO.getSugId());
			int rs = pstmt.executeUpdate();
			if (rs <= 0) {
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
