package model.comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.util.JDBCUtil;

public class CommentDAO {
	private Connection conn;
	private PreparedStatement pstmt;

	
	private static final String SELECTALL_Q="SELECT C.CID,C.QID,NVL(C.LOGIN_ID,'탈퇴한 사용자') AS LOGIN_ID,M.GRADE,C.CONTENT, NVL(M.NAME,'탈퇴한 사용자') AS NAME,S.RANKING\r\n"
			+ "FROM COMMENTS C\r\n"
			+ "LEFT OUTER JOIN MEMBER M ON C.LOGIN_ID =M.LOGIN_ID\r\n"
			+ "LEFT OUTER JOIN (\r\n"
			+ "SELECT  LOGIN_ID,  RANK() OVER (ORDER BY TOTAL_AMOUNT DESC) AS RANKING \r\n"
			+ "FROM ( SELECT M.LOGIN_ID, NVL(SUM(AMOUNT), 0)  AS TOTAL_AMOUNT \r\n"
			+ "FROM  MEMBER M \r\n"
			+ "LEFT OUTER JOIN SUPPORT S \r\n"
			+ "ON S.LOGIN_ID =M.LOGIN_ID \r\n"
			+ "GROUP BY M.LOGIN_ID) ) S ON S.LOGIN_ID=M.LOGIN_ID\r\n"
			+ "WHERE C.QID=? ORDER BY C.CID ASC";
	
	
	private static final String SELECTALL_M = "SELECT C.CID,C.QID,C.LOGIN_ID,C.CONTENT,M.NAME\r\n"
			+ "FROM COMMENTS C\r\n" + "LEFT OUTER JOIN MEMBER M ON C.LOGIN_ID =M.LOGIN_ID\r\n" + "WHERE C.LOGIN_ID=?";
	private static final String INSERT = "INSERT INTO COMMENTS(CID,QID,LOGIN_ID,CONTENT)\r\n"
			+ "VALUES ((SELECT NVL(MAX(CID),0) + 1 FROM COMMENTS),?,?,?)";

	// 회원탈퇴시 'Comment'를 null 값으로 변경
	private static final String CM_UPDATE = "UPDATE COMMENTS SET LOGIN_ID = NULL WHERE LOGIN_ID = ?";
	
	private static final String DELETE = "DELETE FROM COMMENTS WHERE CID = ?";

	// 댓글 전체 출력하기
	public ArrayList<CommentDTO> selectAll(CommentDTO cDTO) {

		ArrayList<CommentDTO> datas = new ArrayList<CommentDTO>();

		conn = JDBCUtil.connect();
		try {
			if (cDTO.getSearchCondition().equals("질문댓글조회")) {// 질문에 대한
				// 모델
				pstmt = conn.prepareStatement(SELECTALL_Q);
				pstmt.setInt(1, cDTO.getqId());
				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					//System.out.println("[로그] 질문 댓글조회");
					CommentDTO data = new CommentDTO();
					data.setcId(rs.getInt("CID"));
					data.setqId(rs.getInt("QID"));
					data.setLoginId(rs.getString("LOGIN_ID"));
					data.setContent(rs.getString("CONTENT"));
					data.setMemberName(rs.getString("NAME"));
					data.setRanking(rs.getInt("RANKING"));
					data.setGrade(rs.getString("GRADE"));
					datas.add(data);
				}

				rs.close();
			} else if (cDTO.getSearchCondition().equals("유저댓글조회")) {// 유저에 대한
				// 모델
				pstmt = conn.prepareStatement(SELECTALL_M);
				pstmt.setString(1, cDTO.getLoginId());
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					CommentDTO data = new CommentDTO();
					data.setcId(rs.getInt("CID"));
					data.setqId(rs.getInt("QID"));
					data.setLoginId(rs.getString("LOGIN_ID"));
					data.setContent(rs.getString("CONTENT"));
					data.setMemberName(rs.getString("NAME"));
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

	public CommentDTO selectOne(CommentDTO cDTO) {
		return null;
	}

	// 댓글 추가하기
	public boolean insert(CommentDTO cDTO) {
//INSERT INTO COMMENTS(CID,QID,LOGIN_ID,CONTENT) VALUES ((SELECT NVL(MAX(CID),0) + 1 FROM COMMENTS),?,?,?)
		conn = JDBCUtil.connect();
		try {
			if (cDTO.getSearchCondition().equals("댓글생성")) {
				// 모델
				pstmt = conn.prepareStatement(INSERT);
				pstmt.setInt(1, cDTO.getqId());
				pstmt.setString(2, cDTO.getLoginId());
				pstmt.setString(3, cDTO.getContent());

			}
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

	public boolean update(CommentDTO cDTO) {

		conn = JDBCUtil.connect();

		try {
			if (cDTO.getSearchCondition().equals("댓글수정")) {
				// 손성용
			} else if (cDTO.getSearchCondition().equals("comment_null")) {
				pstmt = conn.prepareStatement(CM_UPDATE);
				pstmt.setString(1, cDTO.getLoginId());
				int rs = pstmt.executeUpdate();
				if (rs <= 0) {
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

	public boolean delete(CommentDTO cDTO) {
		// 댓글 삭제
		conn = JDBCUtil.connect();
		
		try {
			pstmt = conn.prepareStatement(DELETE);
			pstmt.setInt(1, cDTO.getcId());
			int result = pstmt.executeUpdate();
			if(result <=0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return true;
	}
}