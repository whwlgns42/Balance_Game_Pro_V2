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

	private final String SELECTALL = "SELECT Q.TITLE, Q.ANSWER_A, Q.ANSWER_B , Q.WRITER FROM SAVE S JOIN QUESTIONS Q ON S.QID = Q.QID WHERE S.LOGIN_ID = ?";

	private static final String INSERT = "INSERT INTO SAVE (SID, QID,LOGIN_ID) \r\n"
			+ "VALUES((SELECT NVL(MAX(SID),0) + 1 FROM SAVE),?,?)";

	private static final String DELETE_QID_LID = "DELETE FROM SAVE WHERE QID=? AND LOGIN_ID=?";

	private static final String DELETE_SID = "DELETE FROM SAVE WHERE SID=?";

	public ArrayList<SaveDTO> selectAll(SaveDTO sDTO) {

		// 전은주
		conn = JDBCUtil.connect();
		ArrayList<SaveDTO> datas = new ArrayList<SaveDTO>();
		try {
			pstmt = conn.prepareStatement(SELECTALL);
			pstmt.setString(1, sDTO.getLoginId());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
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
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(INSERT);
			pstmt.setInt(1, sDTO.getqId());
			pstmt.setString(2, sDTO.getLoginId());
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

	public boolean update(SaveDTO sDTO) {
		return false;
	}

	public boolean delete(SaveDTO sDTO) {
		conn = JDBCUtil.connect();
		try {
			if (sDTO.getSearchCondition().equals("qm찜삭제")) {
				// 박현구
				pstmt=conn.prepareStatement(DELETE_QID_LID);
				pstmt.setInt(1, sDTO.getqId());
				pstmt.setString(2, sDTO.getLoginId());
				int rs=pstmt.executeUpdate();
				if(rs<=0) {
					return false;
				}
				
			} else if (sDTO.getSearchCondition().equals("s찜삭제")) {
				// 박현구
				pstmt=conn.prepareStatement(DELETE_SID);
				pstmt.setInt(1, sDTO.getsId());
				int rs=pstmt.executeUpdate();
				if(rs<=0) {
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

}
