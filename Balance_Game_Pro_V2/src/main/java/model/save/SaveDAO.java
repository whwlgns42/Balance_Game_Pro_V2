package model.save;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Util.JDBCUtil;

public class SaveDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	
	private final String SELECTALL="SELECT Q.TITLE, Q.ANSWER_A, Q.ANSWER_B , Q.WRITER FROM SAVE S JOIN QUESTIONS Q ON S.QID = Q.QID WHERE S.LOGIN_ID = ?";
	
	public ArrayList<SaveDTO> selectAll(SaveDTO sDTO) {

		//전은주
		conn = JDBCUtil.connect();
		ArrayList<SaveDTO> datas = new ArrayList<SaveDTO>();
		try {
			pstmt = conn.prepareStatement(SELECTALL);
			pstmt.setString(1, sDTO.getLoginId());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				SaveDTO dto = new SaveDTO();
				dto.setSaveTitle(rs.getString("TITLE"));
				datas.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return datas;

	}

	public SaveDTO selectOne(SaveDTO sDTO) {

		return null;
	}

	public boolean insert(SaveDTO sDTO) {
		// 찜하기
		return false;
	}

	public boolean update(SaveDTO sDTO) {
		return false;
	}

	public boolean delete(SaveDTO sDTO) {
		if (sDTO.getSearchCondition().equals("qm찜삭제")) {
			// 박현구
		} else if (sDTO.getSearchCondition().equals("s찜삭제")) {
			// 박현구
		}

		return false;
	}

}
