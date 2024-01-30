package model.save;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import model.util.JDBCUtil;

public class SaveDAO {

	private Connection conn;
	private PreparedStatement pstmt;

	private static final String SELECTALL = "SELECT S.SID, Q.TITLE, Q.WRITER FROM SAVE S JOIN QUESTIONS Q ON S.QID = Q.QID WHERE S.LOGIN_ID = ?";

	private static final String SELECTONE="SELECT 1 FROM SAVE WHERE LOGIN_ID=? AND QID=?";

	private static final String INSERT = "INSERT INTO SAVE (SID, QID,LOGIN_ID) VALUES((SELECT NVL(MAX(SID),0) + 1 FROM SAVE),?,?)";

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
				dto.setsId(rs.getInt("SID"));
				dto.setSaveTitle(rs.getString("TITLE"));
				dto.setSaveWriter(rs.getString("WRITER"));
				datas.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return datas;

	}

	public SaveDTO selectOne(SaveDTO sDTO) {
		conn = JDBCUtil.connect();
		SaveDTO data=null;
		try {
			pstmt = conn.prepareStatement(SELECTONE);
			pstmt.setString(1, sDTO.getLoginId());
			pstmt.setInt(2, sDTO.getqId());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				data = new SaveDTO();
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return data;
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
				System.out.println(sDTO.getqId());
				System.out.println(sDTO.getLoginId());
				
				pstmt=conn.prepareStatement(DELETE_QID_LID);
				pstmt.setInt(1, sDTO.getqId());
				pstmt.setString(2, sDTO.getLoginId());
				int rs=pstmt.executeUpdate();
				if(rs<=0) {
					System.out.println("[실패]");
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
