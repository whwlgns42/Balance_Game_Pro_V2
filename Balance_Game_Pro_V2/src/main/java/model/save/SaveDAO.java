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

	//유저의 찜목록 SQL 
	private static final String SELECTALL = "SELECT Q.QID, S.SID, Q.TITLE, Q.LOGIN_ID FROM SAVE S JOIN QUESTIONS Q ON S.QID = Q.QID WHERE S.LOGIN_ID = ?";
	//찜유무를 판단 SQL
	//쿼리문 실행시 NULL이라면 찜이 안되었있는것
	//1 이라면 찜이되었는 것을 뜻한다
	//찜에 대한 유무만을 판단하는 것이기 때문에
	//가독성을 위해 1로 설정했다
	private static final String SELECTONE="SELECT 1 FROM SAVE WHERE LOGIN_ID=? AND QID=?";

	//찜 생성 SQL
	private static final String INSERT = "INSERT INTO SAVE (SID, QID,LOGIN_ID) VALUES((SELECT NVL(MAX(SID),0) + 1 FROM SAVE),?,?)";
	
	//찜 삭제 (질문PK, 로그인아이디) SQL
	private static final String DELETE_QID_LID = "DELETE FROM SAVE WHERE QID=? AND LOGIN_ID=?";

	//찜 삭제 (찜 PK) SQL
	private static final String DELETE_SID = "DELETE FROM SAVE WHERE SID=?";

	// 회원탈퇴시 'SAVE'를 null 값으로 변경
	private static final String SV_UPDATE = "UPDATE SAVE SET LOGIN_ID = NULL WHERE LOGIN_ID = ?";
	
	public ArrayList<SaveDTO> selectAll(SaveDTO sDTO) {
		//유저의 찜 전체 조회
		// 전은주
		conn = JDBCUtil.connect();
		ArrayList<SaveDTO> datas = new ArrayList<SaveDTO>();
		try {
			pstmt = conn.prepareStatement(SELECTALL);
			pstmt.setString(1, sDTO.getLoginId());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				SaveDTO dto = new SaveDTO();
				dto.setqId(rs.getInt("QID"));
				dto.setsId(rs.getInt("SID"));
				dto.setSaveTitle(rs.getString("TITLE"));
				dto.setSaveWriter(rs.getString("LOGIN_ID"));
				datas.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return datas;

	}

	public SaveDTO selectOne(SaveDTO sDTO) {
		//찜 유무 판단
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
		//유저 탈퇴시 찜의 로그인 아이디 NULL로 변경
		conn = JDBCUtil.connect();
		try {
			// 손성용
			if (sDTO.getSearchCondition().equals("save_null")) {
				pstmt = conn.prepareStatement(SV_UPDATE);
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

	public boolean delete(SaveDTO sDTO) {
		conn = JDBCUtil.connect();
		try {
			if (sDTO.getSearchCondition().equals("qm찜삭제")) {//질문 PK와, 로그인 아이디로 찜 삭제
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
				
			} else if (sDTO.getSearchCondition().equals("s찜삭제")) {//찜 PK로 찜 삭제 
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
