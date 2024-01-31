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

	private static final String SELECTALL = "SELECT LOGIN_ID, SUGGESTION, TITLE FROM SUGGESTION";


	private static final String INSERT = "INSERT INTO SUGGESTION (SUGID, LOGIN_ID,SUGGESTION,TITLE) VALUES((SELECT NVL(MAX(SUGID),0) + 1 FROM SUGGESTION),?,?,?)";



	public ArrayList<SuggestionDTO> selectAll(SuggestionDTO sDTO) {

		conn = JDBCUtil.connect();
		ArrayList<SuggestionDTO> datas = new ArrayList<SuggestionDTO>();
		try {
			pstmt = conn.prepareStatement(SELECTALL);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				SuggestionDTO dto = new SuggestionDTO();
				dto.setTitle(rs.getString("TITLE"));
				dto.setLoginId(rs.getString("LOGIN_ID"));
				dto.setSuggestion(rs.getString("SUGGESTION"));
				datas.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return datas;

	}

	public SuggestionDTO selectOne(SuggestionDTO sDTO) {
		return null;
	}

	public boolean insert(SuggestionDTO sDTO) {
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(INSERT);
			pstmt.setString(1, sDTO.getLoginId());
			pstmt.setString(2, sDTO.getSuggestion());
			pstmt.setString(3,sDTO.getTitle());
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
		
		return false;
	}

}
