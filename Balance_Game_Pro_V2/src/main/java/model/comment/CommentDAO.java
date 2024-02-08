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

	//질문에 대한 댓글들을 모두 가져오는 SQL
	//댓글 출력시 댓글 pk, 질문 pk, 로그인 아이디, 댓글과 로그인아이디로 등급, 이름 찾아서 가져와 댓글을 먼저 입력한 순으로 가져옴 
	private static final String SELECTALL_Q="SELECT C.CID,C.QID,NVL(C.LOGIN_ID,'탈퇴한 사용자') AS LOGIN_ID,M.GRADE,C.CONTENT, NVL(M.NAME,'탈퇴한 사용자') AS NAME\r\n"
			+ "			FROM COMMENTS C\r\n"
			+ "			LEFT OUTER JOIN MEMBER M ON C.LOGIN_ID =M.LOGIN_ID\r\n"
			+ "			WHERE C.QID=? ORDER BY C.CID ASC";
	
	//특정유저가 입력한 댓글들을 가져오는 SQL 
	private static final String SELECTALL_M = "SELECT C.CID,C.QID,C.LOGIN_ID,C.CONTENT,M.NAME\r\n"
			+ "FROM COMMENTS C\r\n" + "LEFT OUTER JOIN MEMBER M ON C.LOGIN_ID =M.LOGIN_ID\r\n" + "WHERE C.LOGIN_ID=?";
	
	//댓글이 입력되었을때 질문 pk, 로그인아이디,댓글을 저장하는 SQL
	private static final String INSERT = "INSERT INTO COMMENTS(CID,QID,LOGIN_ID,CONTENT)\r\n"
			+ "VALUES ((SELECT NVL(MAX(CID),0) + 1 FROM COMMENTS),?,?,?)";

	// 회원탈퇴시 'Comment'를 null 값으로 변경하는 SQL
	private static final String CM_UPDATE = "UPDATE COMMENTS SET LOGIN_ID = NULL WHERE LOGIN_ID = ?";
	
	//댓글을 삭제하는 SQL
	private static final String DELETE = "DELETE FROM COMMENTS WHERE CID = ?";

	// 댓글 전체 출력하기
	public ArrayList<CommentDTO> selectAll(CommentDTO cDTO) {

		ArrayList<CommentDTO> datas = new ArrayList<CommentDTO>();

		conn = JDBCUtil.connect();
		try {
			if (cDTO.getSearchCondition().equals("질문댓글조회")) {// 질문에 대한 댓글들을 리스트에 저장한다
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
					data.setGrade(rs.getString("GRADE"));
					datas.add(data);
				}

				rs.close();
			} else if (cDTO.getSearchCondition().equals("유저댓글조회")) {// 유저에 대한 댓글들을 리스트에 저장한다
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

	private CommentDTO selectOne(CommentDTO cDTO) {
		return null;
	}

	// 댓글 추가하기
	public boolean insert(CommentDTO cDTO) {
		conn = JDBCUtil.connect();
		try {
			if (cDTO.getSearchCondition().equals("댓글생성")) {//댓글 생성시 질문,로그인아이디,댓글을 저장한다
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
			if (cDTO.getSearchCondition().equals("댓글수정")) {//유저 탈퇴시 댓글에 있는 로그인 아이디를 null로 바꿈
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

	public boolean delete(CommentDTO cDTO) {//댓글 삭제 발생시 댓글 pk로 댓글을 삭제한다
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