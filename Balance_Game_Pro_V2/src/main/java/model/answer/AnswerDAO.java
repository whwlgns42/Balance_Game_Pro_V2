package model.answer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import model.util.JDBCUtil;

public class AnswerDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	
	//답변 저장시 질문 pk ,로그인ID ,답변 저장하는 SQL
	private static final String INSERT="INSERT INTO ANSWERS (AID,QID,LOGIN_ID,ANSWER) VALUES ((SELECT NVL(MAX(AID),0) + 1 FROM ANSWERS),?,?,?)";

	// 회원탈퇴시 'Answers'을 null 값으로 변경하는 SQL
	private static final String AS_UPDATE = "UPDATE ANSWERS SET LOGIN_ID = NULL WHERE LOGIN_ID = ?";

	private ArrayList<AnswerDTO> selectAll(AnswerDTO aDTO) { 
		return null;
	}

	private AnswerDTO selectOne(AnswerDTO aDTO) { 
		return null;
	}

	public boolean insert(AnswerDTO aDTO) { // TODO INSERT : 문제를 풀때 유저의 정보와 문제번호, 문제의 답변을 저장
		conn=JDBCUtil.connect();
		try {
			if (aDTO.getSearchCondition().equals("답변저장")) {
				// 박현구
				pstmt=conn.prepareStatement(INSERT);
				pstmt.setInt(1, aDTO.getqId());
				pstmt.setString(2, aDTO.getLoginId());
				pstmt.setString(3, aDTO.getAnswer());
				int rs=pstmt.executeUpdate();
				if(rs<=0) {
					return false;
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return false;
	}

	public boolean update(AnswerDTO aDTO) { // 업데이트 (이용자가 풀었던 문제값 null로 변환)
		
		conn = JDBCUtil.connect();
		
		try {			
			// 손성용
			if(aDTO.getSearchCondition().equals("answer_null")) {
				pstmt=conn.prepareStatement(AS_UPDATE);
				pstmt.setString(1, aDTO.getLoginId());
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

	// 댓글 삭제하기 TODO 추후 구현 예정
	private boolean delete(AnswerDTO aDTO) {
		return false;
	}

}